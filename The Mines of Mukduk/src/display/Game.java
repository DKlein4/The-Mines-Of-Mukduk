package display;


import java.awt.Graphics;

import world_gen.World;
import main.Handler;

/**
 * @author Dustin; This is the state of the GUI when the game is active
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
