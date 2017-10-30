package display.gui_states;

import java.awt.Graphics;

import main.Handler;

/**
 * A GUI state is what will be updated and rendered in the GUI.
 * 
 * @author Dustin
 */
public abstract class GUIstate {

	// STATIC SHIT

	private static GUIstate currentState = null;

	public static void setState(GUIstate state) {
		currentState = state;
	}

	public static GUIstate getState() {
		return currentState;
	}

	// CLASS

	protected Handler handler;

	public GUIstate(Handler handler) {
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

}