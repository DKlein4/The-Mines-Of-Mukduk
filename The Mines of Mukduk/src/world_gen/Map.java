package world_gen;

import java.util.Random;
import java.lang.Math;

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
	
	// Returns true if the coordinates are within the grid
	public boolean onGrid(int r, int c) {
		return (0 <= r) && (r <= gridSize - 1) && (0 <= c) && (c <= gridSize - 1);
	}

	// Gets gridsize
	public int getGridSize() {
		return gridSize;
	}

	// Resets the grid
	private void reset() {

		// Initialize every tile
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				grid[r][c] = new Tile();
			}
		}

		// Make every tile a Wall
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				grid[r][c].setWall(true);
			}
		}

		// Generate the interior rooms
		genInteriorRooms();
		
		// Place walls around the outside of the grid
		for (int i = 0; i < gridSize; i++) {
			grid[0][i].setWall(true); // top row
			grid[gridSize - 1][i].setWall(true); // bottom row
			grid[i][0].setWall(true); // left column
			grid[i][gridSize - 1].setWall(true); // right column
		}

	}

	// Generates multiple rooms and connects them with corridors
	private void genInteriorRooms() {
		for (int i = 0; i < 15; i++) {
			// Generate two random rooms
			// room array = {width, height, x position of origin, y position of origin, x position of corner opposite of origin, y position of corner opposite of origin}
			int[] room1 = genRoom();
			int[] room2 = genRoom();

			// So it turns out width is up and down and height is left to right...oops
			System.out.println("Room 1: (" + room1[2] + "," + room1[3] + ") to (" + room1[4] + "," + room1[5] + ") w:"
					+ room1[0] + " h:" + room1[1]);
			System.out.println("Room 2: (" + room2[2] + "," + room2[3] + ") to (" + room2[4] + "," + room2[5] + ") w:"
					+ room2[0] + " h:" + room2[1]);

			// Create the corridors between them
			int x1Center = (room1[2] + room1[4]) / 2;
			int x2Center = (room2[2] + room2[4]) / 2;
			int y1Center = (room1[3] + room1[5]) / 2;
			int y2Center = (room2[3] + room2[5]) / 2;

			genXCorridor(x1Center, x2Center, y2Center);
			genYCorridor(y1Center, y2Center, x1Center);
		}
	}

	private int[] genRoom() {
		/*
		 * w - width of the rect 
		 * h - height of the rect 
		 * xo - x coordinate of the origin of the rect 
		 * yo - y coordinate of the origin of the rect 
		 * xc - x coordinate of the corner opposite of the origin 
		 * yc - y coordinate of the corner opposite of the origin
		 */
		int w, h, xo, yo, xc, yc;

		w = rand.nextInt(3 - 1 + 1) + 1;
		h = rand.nextInt(3 - 1 + 1) + 1;

		xo = rand.nextInt((gridSize - 1) - w + 1);
		yo = rand.nextInt((gridSize - 1) - h + 1);

		xc = xo + w;
		yc = yo + h;

		for (int i = xo; i < xo + w; i++) {
			for (int j = yo; j < yo + h; j++) {
				grid[i][j].setFloor(true);
			}
		}

		// Array to return values
		return new int[] { w, h, xo, yo, xc, yc };
	}

	// Generates a line of floor tiles from the given two x coordinates at the given y coordinate
	private void genXCorridor(int x1, int x2, int y) {
		int lower = Math.min(x1, x2);
		int higher = Math.max(x1, x2);

		for (int i = lower; i <= higher; i++) {
			grid[i][y].setFloor(true);
		}
	}
	// Generates a line of floor tiles from the given two y coordinates at the given x coordinate
	private void genYCorridor(int y1, int y2, int x) {
		int lower = Math.min(y1, y2);
		int higher = Math.max(y1, y2);

		for (int i = lower; i <= higher; i++) {
			grid[x][i].setFloor(true);
		}
	}
	
	// Prints out the grid. Purely for testing
	public void printGrid() {
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				if (grid[r][c].isWall())
					System.out.print("\t#");
				else if (grid[r][c].isFloor())
					System.out.print("\t.");
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
