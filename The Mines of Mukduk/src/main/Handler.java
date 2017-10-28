package main;

import display.GUImain;
import input.KeyInput;
import input.MouseInput;
import world_gen.World;

/**
 * Collection of some classes for ease of access.
 * 
 * @author Dustin
 */
public class Handler {

	private GUImain guiMain;
	private World world;

	public Handler(GUImain guiMain) {
		this.guiMain = guiMain;
	}

	// GETTERS AND SETTERS

	public KeyInput getKeyInput() {
		return guiMain.getKeyInput();
	}
	
	public MouseInput getMouseInput() {
		return guiMain.getMouseInput();
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public GUImain getGuiMain() {
		return guiMain;
	}

	public void setGuiMain(GUImain guiMain) {
		this.guiMain = guiMain;
	}
}
