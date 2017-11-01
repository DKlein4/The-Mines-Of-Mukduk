package display.gui_states;

import java.awt.Graphics;

import world_gen.World;
import main.Handler;

/**
 * This is the state of the GUI when the game is active.
 * 
 * @author Dustin
 */
public class GameState extends GUIstate {

	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler);
		handler.setWorld(world);
	}

	public void tick() {
		handler.getMouseInput().setUIManager(null);
		world.tick();
	}

	public void render(Graphics g) {
		world.render(g);
	}
}
