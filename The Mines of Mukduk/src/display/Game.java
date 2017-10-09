package display;


import java.awt.Color;
import java.awt.Graphics;

import world_gen.Map;
import world_gen.World;
import entities.EntityHandler;
import entities.ID;
import entities.Player;
import items.Inventory;
import main.Handler;
import main.main;

/**
 * @author Dustin; This is the main class that creates the GUI and manages the
 *         actions on the GUI
 */
public class Game {

	private World world;
	
	public Game(Handler handler) {
		world = new World(handler);
		handler.setWorld(world);
	}

	public void tick() {
		world.tick();
	}

	public void render(Graphics g) {
		world.render(g);
	}
}
