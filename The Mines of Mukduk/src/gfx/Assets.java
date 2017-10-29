package gfx;

import java.awt.image.BufferedImage;

/**
 * This class loads in all of the assets so they can be accessed easily.
 * 
 * @author Dustin
 */
public class Assets {

	public static final int width = 96, height = 96;

	public static BufferedImage dirt, wall;
	public static BufferedImage player, monster;
	public static BufferedImage menuBackground;
	public static BufferedImage[] button = new BufferedImage[2];

	public static void init() {
		dirt = ImageLoader.loadImage("res/Dirt.png");
		wall = ImageLoader.loadImage("res/Wall.png");

		player = ImageLoader.loadImage("res/Dwarf.png");
		monster = ImageLoader.loadImage("res/Goblin.png");
		
		menuBackground = ImageLoader.loadImage("res/Main Menu Background.png");
		
		button[0] = ImageLoader.loadImage("res/Button.png");
		button[1] = ImageLoader.loadImage("res/Button-Hovered.png");
	}
}
