package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import user_interface.Handler;
import world_gen.Map;

public class Player extends Entity {
	private File pathToSprite;
	private Image playerSprite;

	public Player(int row, int col, ID id, Handler handler, Map map) {
		super(row, col, ID.Player, map);
		this.map = map;

		try {
			pathToSprite = new File("src/Resources/Dwarf.png");
			playerSprite = ImageIO.read(pathToSprite);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		// Update the position of the player
		xPos = (col * tileSizeX) + gridOffsetX;
		yPos = (row * tileSizeY) + gridOffsetY;
	}

	public void render(Graphics g) {
		// Draws the player on the GUI. Right now its just a sexy Dorf
		g.drawImage(playerSprite, xPos, yPos, tileSizeY, tileSizeY, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, tileSizeX, tileSizeY);
	}
}
