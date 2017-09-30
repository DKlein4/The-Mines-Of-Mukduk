package world_gen;

import java.util.Random;

import entities.Entity;
import entities.ID;
import entities.Player;
import user_interface.Handler;

import java.lang.Math;

import world_gen.Tile;

/**
 * @author Dustin; This is the grid that houses all of the tiles. Basically a
 *         level with random generation
 */
public class Map {
	private Random rand = new Random();
	private Tile[][] grid;
	private int gridSize;
	private int numRooms;
	private Handler handler;

	public Map(int gridSize) {
		this.grid = new Tile[gridSize][gridSize];
		this.gridSize = gridSize;
		this.numRooms = 35;
		reset();
	}

	public Map() {
		this.grid = new Tile[10][10];
		this.gridSize = 10;
		this.numRooms = 15;
		reset();
	}

	// Returns true if the coordinates are within the grid
	public boolean onGrid(int r, int c) {
		return (0 <= r) && (r <= gridSize - 1) && (0 <= c) && (c <= gridSize - 1);
	}

	// returns gridSize
	public int getGridSize() {
		return gridSize;
	}

	public Tile getGrid(int r, int c) {
		return grid[r][c];
	}

	// Resets the grid
	public void reset() {

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

		int spawnX;
		int spawnY;
		spawnX = spawnY = 0;
		// Place the spawn point
		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (grid[r][c].isFloor() == true) {
					grid[r][c].setSpawn(true);
					r = spawnX;
					c = spawnY;
					r = c = gridSize; // Break out of the loop
				}
			}
		}

		// Place the exit ladder
		for (int c = gridSize - 1; c >= 0; c--) {
			for (int r = gridSize - 1; r >= 0; r--) {
				if (grid[r][c].isFloor() == true) {
					grid[r][c].setLadder(true);// PLACEHOLDER change monster to
												// player
					r = c = 0; // Break out of the loop
				}
			}
		}

		// Places the player sprite
		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (grid[r][c].isSpawn() == true) {
					for (int i = 0; i < Handler.entity.size(); i++) {
						Entity tempObject = Handler.entity.get(i);
						if (tempObject.getId() == ID.Player) {
							tempObject.setRow(r);
							tempObject.setCol(c);
						}
					}
				}
			}
		}
	}

	// Generates multiple rooms and connects them with corridors
	private void genInteriorRooms() {
		// Creates numRooms amount of rooms
		// Since this loop creates 2 rooms we only need to go to numRooms/2
		for (int i = 0; i < numRooms / 2; i++) {

			// Generate two random rooms
			// room array = {width, height, x position of origin, y position of
			// origin, x position of corner opposite of origin, y position of
			// corner opposite of origin}
			int[] room1 = genRoom();
			int[] room2 = genRoom();

			// Find the centers of each room
			int x1Center = (room1[2] + room1[4]) / 2; // First room's center x
			int x2Center = (room2[2] + room2[4]) / 2; // Second room's center x
			int y1Center = (room1[3] + room1[5]) / 2; // First room's center y
			int y2Center = (room2[3] + room2[5]) / 2; // Second room's center x

			// Use those values as the coordinates for the corridors to make
			// them match up to the center of the rooms.
			genXCorridor(x1Center, x2Center, y2Center);
			genYCorridor(y1Center, y2Center, x1Center);
		}
	}

	// Generates one room and returns the room attributes in an array
	private int[] genRoom() {
		// w - width of the rect
		// h - height of the rect
		// xo - x coordinate of the origin of the rect
		// yo - y coordinate of the origin of the rect
		// xc - x coordinate of the corner opposite of the origin
		// yc - y coordinate of the corner opposite of the origin

		// xt - x coordinate of treasure
		// yt - y coordinate of treasure
		int w, h, xo, yo, xc, yc, xt, yt;

		int maxRoomSize = 4;
		int minRoomSize = 1;

		// Generate random width and height between the restrictions
		w = rand.nextInt(maxRoomSize - minRoomSize + 1) + minRoomSize;
		h = rand.nextInt(maxRoomSize - minRoomSize + 1) + minRoomSize;

		// Generate the coordinates where the map will be placed on the grid
		xo = rand.nextInt((gridSize - 1) - w + 1);
		yo = rand.nextInt((gridSize - 1) - h + 1);

		xc = xo + w;
		yc = yo + h;

		// Go to every tile within the room coordinates and set it to floor
		for (int i = yo; i < yo + h; i++) {
			for (int j = xo; j < xo + w; j++) {
				grid[i][j].setFloor(true);
			}
		}

		// Generate Treasure Tile in room
		xt = rand.nextInt(w) + xo;
		yt = rand.nextInt(h) + yo;
		grid[yt][xt].setTreasure(true);

		// return the values as an array
		return new int[] { w, h, xo, yo, xc, yc };
	}

	// Generates a line of floor tiles from the given two x coordinates at the
	// given y coordinate
	private void genXCorridor(int x1, int x2, int y) {
		int lower = Math.min(x1, x2);
		int higher = Math.max(x1, x2);

		for (int i = lower; i <= higher; i++) {
			grid[y][i].setFloor(true);
		}
	}

	// Generates a line of floor tiles from the given two y coordinates at the
	// given x coordinate
	private void genYCorridor(int y1, int y2, int x) {
		int lower = Math.min(y1, y2);
		int higher = Math.max(y1, y2);

		for (int i = lower; i <= higher; i++) {
			grid[i][x].setFloor(true);
		}
	}
}
