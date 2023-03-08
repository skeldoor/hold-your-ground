package com.skeldoor;

import net.runelite.api.Client;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;

public class HoldYourGroundUtils {

    public static boolean hasLineOfSight(Client client, WorldArea start, LocalPoint targetLocalPoint)
    {
        WorldPoint targetWorldPoint = WorldPoint.fromLocal(client, targetLocalPoint);
        return start.hasLineOfSightTo(client, targetWorldPoint);
    }

    public static boolean isWithinRange(Client client, WorldArea start, LocalPoint targetLocalPoint, int range){
        WorldPoint targetWorldPoint = WorldPoint.fromLocal(client, targetLocalPoint);

        int xDiff = Math.abs(start.getX() - targetWorldPoint.getX());
        int yDiff = Math.abs(start.getY() - targetWorldPoint.getY());

        // If range is 1 we must be EXACTLY 1 tile away as melee/pickpockets will move our character to an adjacent tile
        // Range calculations in runescape work on a square, so you can shoot further on diagonals
        if (range == 1){
            return (xDiff == range && yDiff == 0 ) || (xDiff == 0 && yDiff == range );
        } else  {
            return xDiff <= range && yDiff <= range;
        }
    }
}
