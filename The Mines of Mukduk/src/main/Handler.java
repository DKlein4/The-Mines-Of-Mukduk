package main;

import display.GUImain;
import display.Game;
import input.KeyInput;
import world_gen.World;

public class Handler {
	
	private GUImain guiMain;
	private World world;
	
	public Handler(GUImain guiMain){
		this.guiMain = guiMain;
	}
	
	
	// GETTERS AND SETTERS

	
	public KeyInput getKeyInput(){
		return guiMain.getKeyInput();
	}
	
	public World getWorld(){
		return world;
	}
	
	public void setWorld(World world){
		this.world = world;
	}
	
	public GUImain getGuiMain() {
		return guiMain;
	}

	public void setGuiMain(GUImain guiMain) {
		this.guiMain = guiMain;
	}	
}
