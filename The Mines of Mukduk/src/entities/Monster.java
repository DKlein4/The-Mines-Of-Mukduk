package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gfx.Assets;
import gfx.GameCamera;
import main.Handler;
import world_gen.Map;

public class Monster extends Entity {
	
	private GameCamera gameCamera;

	public Monster(int row, int col, ID id, Handler handler, Map map) {
		super(row, col, ID.Monster, map);
		
		gameCamera = handler.getGuiMain().getGameCamera();
	}

	public void tick() {
		// Update the position of the player
		xPos = (col * tileWidth) + gridOffsetX;
		yPos = (row * tileHeight) + gridOffsetY;
	}

	public void render(Graphics g) {
		g.drawImage(Assets.monster,(int) (xPos - gameCamera.getxOffset()), (int) (yPos - gameCamera.getyOffset()), width, height, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, width, height);
	}
}
