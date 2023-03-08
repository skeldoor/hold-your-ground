package com.skeldoor;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.HotkeyListener;
import net.runelite.client.util.Text;

import java.awt.*;
import java.util.Objects;

@Slf4j
@PluginDescriptor(
	name = "Hold Your Ground"
)
public class HoldYourGroundPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private HoldYourGroundConfig config;

	@Inject
	private HoldYourGroundOverlay holdYourGroundOverlay;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ChatMessageManager chatMessageManager;

	String attackOption = "Attack";
	String magicOption = "Cast";
	String talkMenuOption = "Talk-to";
	String tradeMenuOption =  "Trade";
	String pickpocketMenuOption =  "Pickpocket";
	String useMenuOption = "Use";
	String walkHereMenuOption = "Walk here";

	boolean overrideHoldMove = false;

	@Inject
	private KeyManager keyManager;

	private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.holdMovingHotkey())
	{
		@Override
		public void hotkeyPressed()
		{
			overrideHoldMove = true;
		}

		@Override
		public void hotkeyReleased()
		{
			overrideHoldMove = false;
		}
	};

	@Override
	protected void startUp()
	{
		keyManager.registerKeyListener(hotkeyListener);
		overlayManager.add(holdYourGroundOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		keyManager.unregisterKeyListener(hotkeyListener);
		overlayManager.remove(holdYourGroundOverlay);
	}

	@Subscribe
	private void onMenuOptionClicked(MenuOptionClicked event) {
		String target = event.getMenuTarget();
		String menuOption = event.getMenuOption();

		if (Objects.equals(menuOption, walkHereMenuOption)){
			if (overrideHoldMove){
				sendHighlightedChatMessage("Player movement is allowed as hotkey is held");
			} else if (config.holdMoving()){
				event.getMenuEntry().setTarget(ColorUtil.prependColorTag(Text.removeTags(target),Color.black));
				event.getMenuEntry().setDeprioritized(true);
				event.consume();
				sendHighlightedChatMessage("Player movement is disabled");
			}
		}

		int identifier = event.getMenuEntry().getIdentifier();
		final NPC[] cachedNPCs = client.getCachedNPCs();
		final NPC npc = cachedNPCs[identifier];

		if (npc == null) return;
		if (npc.getName() == null) return;

		System.out.println(event.getMenuOption());
		System.out.println(target);


		WorldArea start = client.getLocalPlayer().getWorldArea();
		boolean hasLineOfSight = HoldYourGroundUtils.hasLineOfSight(client, start, npc.getLocalLocation());
		boolean isWithinRange = HoldYourGroundUtils.isWithinRange(client, start, npc.getLocalLocation(), config.maxTileRange());

		// If we don't have line of sight or aren't within configured range, consume the click to stop it happening
		if (config.holdAttacks()     && Objects.equals(menuOption, attackOption) ||
			config.holdSpells()      && Objects.equals(menuOption, magicOption) ||
			config.holdTalkTo()      && Objects.equals(menuOption, talkMenuOption) ||
			config.holdTrades()      && Objects.equals(menuOption, tradeMenuOption) ||
			config.holdPickpockets() && Objects.equals(menuOption, pickpocketMenuOption) ||
			config.holdUses()        && Objects.equals(menuOption, useMenuOption)
		){
			if (!hasLineOfSight) {
				event.getMenuEntry().setTarget(ColorUtil.prependColorTag(Text.removeTags(target),Color.black));
				event.getMenuEntry().setDeprioritized(true);
				event.consume();
				sendHighlightedChatMessage("No line of sight to your target to " + menuOption);
			} else if (!isWithinRange) {
				event.getMenuEntry().setTarget(ColorUtil.prependColorTag(Text.removeTags(target),Color.black));
				event.getMenuEntry().setDeprioritized(true);
				event.consume();
				sendHighlightedChatMessage("Your target is too far to " + menuOption);
			} else if (config.requireStationary() && npc.getPoseAnimation() != npc.getIdlePoseAnimation()){
				event.getMenuEntry().setTarget(ColorUtil.prependColorTag(Text.removeTags(target),Color.black));
				event.getMenuEntry().setDeprioritized(true);
				event.consume();
				sendHighlightedChatMessage("Your target is moving");
			}
		}
	}

	private void sendHighlightedChatMessage(String message) {
		if (!config.chatMessages()) return;
		ChatMessageBuilder msg = new ChatMessageBuilder()
				.append(ChatColorType.HIGHLIGHT)
				.append(message);

		chatMessageManager.queue(QueuedMessage.builder()
				.type(ChatMessageType.CONSOLE)
				.runeLiteFormattedMessage(msg.build())
				.build());
	}

	@Provides
	HoldYourGroundConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HoldYourGroundConfig.class);
	}
}
