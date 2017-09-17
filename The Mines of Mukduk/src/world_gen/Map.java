package world_gen;
import world_gen.Tile;

public class Map {
	private Tile[][] grid;
	private int gridSize;
	
	public Map(int mapSize){
		grid = new Tile[mapSize][mapSize];
		gridSize = mapSize;
		reset();
	}
	public Map(){
		grid = new Tile[10][10];
		gridSize = 10;
		reset();
	}
	
	// Resets the grid
	void reset(){
		// Make every tile 
		
		//Place walls around the outside of the grid
		for(int i = 0; i < gridSize; i++){
			grid[0][i].setWall(true);			//top row 
			grid[gridSize][i].setWall(true);	//bottom row 
			grid[i][0].setWall(true);			//left column
			grid[i][gridSize].setWall(true); 	//right column
		}
	}
}
