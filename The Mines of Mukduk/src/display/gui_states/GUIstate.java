package display.gui_states;

import java.awt.Graphics;

import main.Handler;

public abstract class GUIstate {

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