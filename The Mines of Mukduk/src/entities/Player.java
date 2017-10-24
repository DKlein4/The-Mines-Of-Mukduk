package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gfx.Assets;
import gfx.GameCamera;
import input.PlayerInput;
import items.Inventory;
import main.Handler;
import world_gen.Map;

/**
 * @author Dustin; The entity that the user plays as.
 */
public class Player extends Entity {

	private Inventory inventory;
	private PlayerInput playerInput;
	
	private GameCamera gameCamera;

	public Player(int row, int col, ID id, Handler handler, Map map) {
		super(row, col, ID.Player, map);

		gameCamera = handler.getGuiMain().getGameCamera();
		
		inventory = new Inventory();
		playerInput = new PlayerInput(handler, this);
	}

	public void tick() {
		// Update the position of the player
		xPos = (col * tileWidth) + gridOffsetX;
		yPos = (row * tileHeight) + gridOffsetY;
		
		gameCamera.centerOnEntity(this);
		
		inventory.tick();
		playerInput.tick();
	}

	public void render(Graphics g) {
		// Draws the player on the GUI. Right now its just a sexy Dorf
		g.drawImage(Assets.player, (int) (xPos - gameCamera.getxOffset()), (int) (yPos - gameCamera.getyOffset()), width, height, null);

		inventory.render(g);
	}

	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, tileWidth, tileHeight);
	}
}
