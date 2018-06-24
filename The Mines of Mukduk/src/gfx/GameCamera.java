package gfx;

import display.GUImain;
import entities.Entity;
import main.Handler;
import world_gen.Tile;

/**
 * This "moves" the screen centered around the player.
 * 
 * @author Dustin
 */
public class GameCamera {

	private Handler handler;

	private int xOffset, yOffset;
	private int tileWidth, tileHeight;
	private int screenWidth, screenHeight;
	private int gridSize;

	public GameCamera(Handler handler, int xOffset, int yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;

		tileWidth = Tile.TILE_WIDTH;
		tileHeight = Tile.TILE_HEIGHT;
		screenWidth = GUImain.WIDTH;
		screenHeight = GUImain.HEIGHT;

	}

	public void checkBlankSpace() {
		tileWidth = Tile.TILE_WIDTH;
		tileHeight = Tile.TILE_HEIGHT;
		screenWidth = GUImain.WIDTH;
		screenHeight = GUImain.HEIGHT;

		gridSize = handler.getWorld().getMap().getGridSize();

		// Doesn't allow the camera to go off the screen to the left
		if (xOffset < 0) {
			xOffset = 0;
		}
		// Doesn't allow the camera to go off the screen to the right
		else if (xOffset > gridSize * tileWidth - screenWidth) {
			xOffset = gridSize * tileWidth - screenWidth;
		}

		// Doesn't allow the camera to go off the screen to the top
		if (yOffset < 0) {
			yOffset = 0;
		}
		// Doesn't allow the camera to go off the screen to the bottom
		else if (yOffset > gridSize * tileHeight - screenHeight) {
			yOffset = gridSize * tileHeight - screenHeight;
		}
	}

	public void centerOnEntity(Entity e) {
		tileWidth = Tile.TILE_WIDTH;
		tileHeight = Tile.TILE_HEIGHT;
		screenWidth = GUImain.WIDTH;
		screenHeight = GUImain.HEIGHT;

		xOffset = e.getXPos() - GUImain.WIDTH / 2 + e.getWidth() / 2;
		yOffset = e.getYPos() - GUImain.HEIGHT / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}

	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
}
