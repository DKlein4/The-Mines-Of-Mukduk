package world_gen;

import java.util.Random;

import world_gen.Tile;

public class Map {
	private Tile[][] grid;
	private int gridSize;
	private Random rand = new Random();
	
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
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				grid[r][c] = new Tile();
			}
		}

		// Make every tile a floor
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				grid[r][c].setFloor(true);
			}
		}

		// Place walls around the outside of the grid
		for (int i = 0; i < gridSize; i++) {
			grid[0][i].setWall(true); // top row
			grid[gridSize - 1][i].setWall(true); // bottom row
			grid[i][0].setWall(true); // left column
			grid[i][gridSize - 1].setWall(true); // right column
		}

		// Place one Door on the edge of the grid
		int doorSide = rand.nextInt((3 - 0) - 1) + 0;
		int doorPosition = rand.nextInt(((gridSize - 2) - 2) + 1) + 2;
		if (doorSide == 0) {
			grid[0][doorPosition].setDoor(true); // top row
		}
		if (doorSide == 1) {
			grid[gridSize - 1][doorPosition].setDoor(true); // bottom row
		}
		if (doorSide == 2) {
			grid[doorPosition][0].setDoor(true); // left column
		}
		if (doorSide == 3) {
			grid[doorPosition][gridSize - 1].setDoor(true); // right column
		}

	}

	// Returns true if the coordinates are within the grid
	public boolean onGrid(int r, int c) {
		return (0 <= r) && (r <= gridSize - 1) && (0 <= c) && (c <= gridSize - 1);
	}

	public int getGridSize() {
		return gridSize;
	}

	// Prints out the grid
	public void printGrid() {
		// for (int r = 0; r < gridSize; r++) {
		// for (int c = 0; c < gridSize; c++) {
		// if (grid[r][c].isWall())
		// System.out.print("\tW" + r + "," + c);
		// else if (grid[r][c].isFloor())
		// System.out.print("\tF" + r + "," + c);
		// else if (grid[r][c].isDoor())
		// System.out.print("\tD" + r + "," + c);
		// else if (grid[r][c].isUnexplored())
		// System.out.print("\tU" + r + "," + c);
		// else if (grid[r][c].isTreasure())
		// System.out.print("\tT" + r + "," + c);
		// else if (grid[r][c].isMonster())
		// System.out.print("\tM" + r + "," + c);
		// else
		// System.out.print("\tX" + r + "," + c);
		// }
		// System.out.println();
		// }
		// System.out.println();

		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				if (grid[r][c].isWall())
					System.out.print("\tW");
				else if (grid[r][c].isFloor())
					System.out.print("\tF");
				else if (grid[r][c].isDoor())
					System.out.print("\tD");
				else if (grid[r][c].isUnexplored())
					System.out.print("\tU");
				else if (grid[r][c].isTreasure())
					System.out.print("\tT");
				else if (grid[r][c].isMonster())
					System.out.print("\tM");
				else
					System.out.print("\tX");
			}
			System.out.println();
		}
		System.out.println();
	}
}
