package gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static final int width = 32, height = 32;
	
	public static BufferedImage dirt, wall;
	public static BufferedImage player, monster;

	
	public static void init() {
		dirt = ImageLoader.loadImage("src/Resources/Dirt.png");
		wall = ImageLoader.loadImage("src/REsources/Wall.png");
		
		player = ImageLoader.loadImage("src/Resources/Dwarf.png");
		monster = ImageLoader.loadImage("src/Resources/Goblin.png");
	}
}
