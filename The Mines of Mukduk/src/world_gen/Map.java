package world_gen;

import world_gen.Tile;

public class Map {
	private Tile[][] grid;
	private int gridSize;

	public Map(int mapSize) {
		grid = new Tile[mapSize][mapSize];
		gridSize = mapSize;
		reset();
	}
	public Map() {
		grid = new Tile[10][10];
		gridSize = 10;
		reset();
	}

	// Resets the grid
	private void reset() {

		// Initialize every tile
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j] = new Tile();
			}
		}

		// Make every tile a floor
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j].setFloor(true);
			}
		}
		
		// Place walls around the outside of the grid
		for (int i = 0; i < gridSize; i++) {
			grid[0][i].setWall(true); 			// top row
			grid[gridSize-1][i].setWall(true); 	// bottom row
			grid[i][0].setWall(true); 			// left column
			grid[i][gridSize-1].setWall(true); 	// right column
		}
		
		// Place one Door on the edge of the grid
		
	}

	// Prints out the grid
	public void printGrid() {
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (grid[i][j].isWall())
					System.out.print("\tW" + i + "," + j);
				else if (grid[i][j].isFloor())
					System.out.print("\tF" + i + "," + j);
				else if (grid[i][j].isDoor())
					System.out.print("\tD" + i + "," + j);
				else if (grid[i][j].isUnexplored())
					System.out.print("\tU" + i + "," + j);
				else if (grid[i][j].isTreasure())
					System.out.print("\tT" + i + "," + j);
				else if (grid[i][j].isMonster())
					System.out.print("\tM" + i + "," + j);
				else 
					System.out.print("\tX" + i + "," + j);
			}
			System.out.println();
		}
		System.out.println();
	}
}
