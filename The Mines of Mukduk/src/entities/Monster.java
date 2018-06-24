package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import gfx.Assets;
import main.Handler;
import world_gen.Map;
import world_gen.Tile;

/**
 * This entity is the main enemy on the map.
 * 
 * @author Dustin
 */
public class Monster extends Entity {

	public Monster(int row, int col, ID id, Handler handler, Map map) {
		super(row, col, ID.Monster, handler, map);
	}

	public void tick() {
		tileWidth = Tile.TILE_WIDTH;
		tileHeight = Tile.TILE_HEIGHT;

		width = tileWidth;
		height = tileHeight;

		// Update the position of the monster
		xPos = (col * tileWidth) + gridOffsetX;
		yPos = (row * tileHeight) + gridOffsetY;
	}

	public void render(Graphics g) {
		g.drawImage(Assets.monster, (int) (xPos - gameCamera.getxOffset()), (int) (yPos - gameCamera.getyOffset()),
				width, height, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, width, height);
	}
}
