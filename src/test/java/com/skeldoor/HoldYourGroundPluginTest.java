package com.skeldoor;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HoldYourGroundPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HoldYourGroundPlugin.class);
		RuneLite.main(args);
	}
}