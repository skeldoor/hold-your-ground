package com.skeldoor;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Hold Your Ground")
public interface HoldYourGroundConfig extends Config
{
	@ConfigItem(
		keyName = "maxTileRange",
		name = "Max Tile Range",
		description = "How far your interaction range extends. Conversations/Pickpocketing/Salamander = 1, Shortbow/Powered Staff = 7, Shortbow(def) = 9, Spells = 10"
	)
	default int maxTileRange()
	{
		return 1;
	}

	@ConfigItem(
			keyName = "holdAttacks",
			name = "Hold your attacks",
			description = "Whether your attacks will be held back"
	)
	default boolean holdAttacks()
	{
		return true;
	}

	@ConfigItem(
			keyName = "holdSpells",
			name = "Hold your spells",
			description = "Whether your spell casts will be held back"
	)
	default boolean holdSpells()
	{
		return true;
	}

	@ConfigItem(
			keyName = "holdTalkTo",
			name = "Hold your conversations",
			description = "Whether your conversations (Talk to) will be held back"
	)
	default boolean holdTalkTo()
	{
		return true;
	}

	@ConfigItem(
			keyName = "holdTrades",
			name = "Hold your trades",
			description = "Whether your trade will be held back"
	)
	default boolean holdTrades()
	{
		return true;
	}

	@ConfigItem(
			keyName = "holdPickpockets",
			name = "Hold your pickpockets",
			description = "Whether your pickpockets will be held back"
	)
	default boolean holdPickpockets()
	{
		return true;
	}

	@ConfigItem(
			keyName = "holdUses",
			name = "Hold your uses",
			description = "Whether your item uses (Use item on) will be held back"
	)
	default boolean holdUses()
	{
		return true;
	}

	@ConfigItem(
			keyName = "holdMoving",
			name = "Hold moving",
			description = "Whether your character movements will be held back"
	)
	default boolean holdMoving()
	{
		return true;
	}

	@ConfigItem(
			keyName = "requireStationary",
			name = "Require stationary",
			description = "Require that the NPC is not moving"
	)
	default boolean requireStationary()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showOverlay",
			name = "Show overlay",
			description = "Whether the npc overlay shows on their tile"
	)
	default boolean showOverlay()
	{
		return false;
	}

	@ConfigItem(
			keyName = "chatMessages",
			name = "Show chat messages",
			description = "Whether chat will be populated with the reason for a held click"
	)
	default boolean chatMessages()
	{
		return true;
	}

}
