package display.gui_states;

import java.awt.Graphics;

import display.MessageNotifier;
import display.PauseMenu;
import world_gen.World;
import main.Handler;

/**
 * This is the state of the GUI when the game is active.
 * 
 * @author Dustin
 */
public class GameState extends GUIstate {

	private PauseMenu pauseMenu;
	private MessageNotifier messenger;
	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler);
		handler.setWorld(world);
		
		pauseMenu = new PauseMenu(handler);
		messenger = handler.getGuiMain().getMessageNotifier();
	}

	public void tick() {
		world.tick();
		messenger.tick();
		pauseMenu.tick();
	}

	public void render(Graphics g) {
		world.render(g);
		messenger.render(g);
		pauseMenu.render(g);
	}
}
