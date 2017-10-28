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
		dirt = ImageLoader.loadImage("src/Resources/Dirt.png");
		wall = ImageLoader.loadImage("src/REsources/Wall.png");

		player = ImageLoader.loadImage("src/Resources/Dwarf.png");
		monster = ImageLoader.loadImage("src/Resources/Goblin.png");
		
		menuBackground = ImageLoader.loadImage("src/Resources/Main Menu Background.png");
		
		button[0] = ImageLoader.loadImage("src/Resources/Button.png");
		button[1] = ImageLoader.loadImage("src/Resources/Button-Hovered.png");
	}
}
