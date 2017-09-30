package world_gen;

import user_interface.GUIMain;

/**
 * @author Dustin; Holds and manages multiple levels or maps.
 */
public class World {
	
	public Map level1;
	
	public World(){
		level1 = new Map(30);
	}
}
