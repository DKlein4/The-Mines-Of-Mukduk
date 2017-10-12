package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import world_gen.Map;

public class Monster extends Entity {

	private File pathToSprite;
	private Image playerSprite;

	public Monster(int row, int col, ID id, Map map) {
		super(row, col, ID.Monster, map);

		// Load in the sprite for the Monster
		try {
			pathToSprite = new File("src/Resources/Goblin.png");
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
		g.drawImage(playerSprite, xPos, yPos, tileSizeY, tileSizeY, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, tileSizeX, tileSizeY);
	}
}
