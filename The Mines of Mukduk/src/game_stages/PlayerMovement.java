package game_stages;
import world_gen.Map;

public class PlayerMovement {
	
	private int[] pos;
	private int mapSize;
	
	public PlayerMovement(){
		pos = new int[] {0,0};
		
	}
	
	// Moves the player's position one tile up if it is a valid tile
	public boolean stepUp(Map m){
		if(m.onGrid(pos[0]+1, pos[1])){
			pos[0]++;
			return true;
		}else
			return false;
	}
}
