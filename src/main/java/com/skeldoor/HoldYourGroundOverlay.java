package com.skeldoor;

import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.Perspective;
import net.runelite.api.Tile;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.client.ui.overlay.*;

import javax.inject.Inject;
import java.awt.*;

public class HoldYourGroundOverlay extends Overlay {

    @Inject
    private Client client;

    @Inject
    private HoldYourGroundConfig config;

    @Inject
    private HoldYourGroundOverlay() {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setPriority(OverlayPriority.HIGHEST);
    }

    private void renderTileIfHasLineOfSight(Graphics2D graphics, WorldArea start, LocalPoint targetLocalPoint, Polygon polyToRender)
    {
        Color tileColour = Color.red;
        if (HoldYourGroundUtils.hasLineOfSight(client, start, targetLocalPoint) &&
            HoldYourGroundUtils.isWithinRange(client, start, targetLocalPoint, config.maxTileRange())) {
            tileColour = Color.green;
        }
        OverlayUtil.renderPolygon(graphics, polyToRender, tileColour);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!config.showOverlay()) return null;
        WorldArea start = client.getLocalPlayer().getWorldArea();
        Tile[][][] tiles = client.getScene().getTiles();
        int z = client.getPlane();
        for (int x = 0; x < Constants.SCENE_SIZE; ++x)
        {
            for (int y = 0; y < Constants.SCENE_SIZE; ++y) {
                Tile tile = tiles[z][x][y];
                final LocalPoint tileLocalLocation = tile.getLocalLocation();
                Polygon poly = Perspective.getCanvasTilePoly(client, tileLocalLocation);
                if (poly != null && poly.contains(client.getMouseCanvasPosition().getX(), client.getMouseCanvasPosition().getY()))
                {
                    renderTileIfHasLineOfSight(graphics, start, tileLocalLocation, poly);
                }
            }
        }
        return null;
    }
}
