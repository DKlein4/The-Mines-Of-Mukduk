package world_gen;

import java.util.Random;

import entities.Entity;
import entities.EntityHandler;
import entities.ID;
import entities.Monster;
import entities.Player;
import game_stages.Combat;
import main.Handler;
import utils.Utils;

import java.lang.Math;

import world_gen.Tile;

/**
 * This is the grid that houses all of the tiles. Basically a level with random
 * generation.
 * 
 * @author Dustin
 */
public class Map {

	private final int gridSize = 30;

	private Tile[][] grid;
	private int numRooms;
	private int levelNum;

	private Random rand;
	private Tables table;
	private EntityHandler entityHandler;

	private Handler handler;

	public Map(Handler handler, EntityHandler entityHandler) {
		grid = new Tile[gridSize][gridSize];
		numRooms = 35;
		levelNum = 0;

		this.entityHandler = entityHandler;
		rand = new Random();
		table = new Tables();

		this.handler = handler;

		reset();
	}

	// GETTERS AND SETTERS

	public int getGridSize() {
		return gridSize;
	}

	public Tile getTile(int r, int c) {
		return grid[r][c];
	}

	public int getLevelNum() {
		return levelNum;
	}

	// STATE CHECKERS

	// Returns true if the coordinates are within the grid
	public boolean onGrid(int r, int c) {
		return (0 <= r) && (r <= gridSize - 1) && (0 <= c) && (c <= gridSize - 1);
	}

	// Returns true if the coordinate is a valid tile to move to
	public boolean isValidMove(int r, int c) {
		return !grid[r][c].isSolid();
	}

	// Checks if tile is treasure, rolls loot, then sets it back to floor
	public void isTreasure(int r, int c) {
		if (grid[r][c].isTreasure()) {
			table.lootRoll();
			grid[r][c].setFloor(true);
		}
	}

	// Checks if tile is ladder, resets the map if it is
	public void isLadder(int r, int c) {
		if (grid[r][c].isLadder()) {
			reset();
		}
	}

	// Checks if tile is spooky monster, starts combat if it is
	public void isMonster(int r, int c) {
		if (grid[r][c].isMonster()) {
			
			int mRow, mCol;
			mRow = mCol = 0;
			
			Player player = null;
			Monster monster = null;
			for (int i = 0; i < entityHandler.getEntities().size(); i++) {
				Entity tempPlayer = entityHandler.getEntities().get(i);
				if (tempPlayer.getId() == ID.Player) {
					mRow = tempPlayer.getRow();
					mCol = tempPlayer.getCol();
					
					player = (Player)tempPlayer;
				}
			}
			
			for (int i = 0; i < entityHandler.getEntities().size(); i++) {
				Entity tempMonster = entityHandler.getEntities().get(i);
				if (tempMonster.getId() == ID.Monster && mRow == tempMonster.getRow() && mCol == tempMonster.getCol()) {
					monster = (Monster)tempMonster;
				}
			}

			new Combat(handler, player, monster);
			grid[r][c].setFloor(true);
		}
	}

	// RESEST AND HELPER FUNCTIONS

	public void reset() {

		// Initialize every tile and set it to a wall
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				grid[r][c] = new Tile();
				grid[r][c].setWall(true);
			}
		}

		genInteriorRooms();
		boundGen();
		parcelMap();
		placeSpawnPoint();
		placeLadder();
		placePlayer();
		placeMonsters();

		levelNum++;
	}

	// MAP GENERATION

	private void parcelMap() {
		String file = Utils.loadFileAsString("res/Test Map.txt");
		String[] tokens = file.split("\\s+");
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				if (tokens[(r + c * gridSize)].equals("O")) {
					parcelGen(r, c);
				}
			}
		}
	}

	private void parcelGen(int r, int c) {
		String file = null;
		int parcelRoll = rand.nextInt((2 - 1) + 1) + 1;
		if (parcelRoll == 1) {
			file = Utils.loadFileAsString("res/Parcel1.txt");
		}

		else if (parcelRoll == 2) {
			file = Utils.loadFileAsString("res/Parcel2.txt");
		}

		String[] tokens = file.split("\\s+");
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (tokens[(x + y * 9)].equals("X")) {
					grid[y + c][x + r].setWall(true);
				}
				if (tokens[(x + y * 9)].equals(".")) {
					grid[y + c][x + r].setFloor(true);
				}
				if (tokens[(x + y * 9)].equals("T")) {
					grid[y + c][x + r].setTreasure(true);
				}
			}
		}
	}

	// Checks for one Wall Tile blockages and clears them
	@SuppressWarnings("unused")
	private void clearBlockages() {
		for (int c = 1; c < gridSize - 1; c++) {
			for (int r = 1; r < gridSize - 1; r++) {
				if (grid[r][c].isWall() && grid[r + 1][c].isWall() && grid[r - 1][c].isWall()
						&& grid[r][c + 1].isFloor() && grid[r][c - 1].isFloor()) {
					grid[r][c].setFloor(true);
				}
				if (grid[r][c].isWall() && grid[r][c + 1].isWall() && grid[r][c - 1].isWall()
						&& grid[r + 1][c].isFloor() && grid[r - 1][c].isFloor()) {
					grid[r][c].setFloor(true);
				}
			}
		}
	}

	// Finds the appropriate place for the player to spawn
	private void placeSpawnPoint() {
		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (grid[r][c].isFloor()) {
					// Creates a room starting at the spawn point
					for (int xs = r; xs < r + 4; xs++) {
						for (int ys = c; ys < c + 4; ys++) {
							if (xs < (gridSize - 1)) {
								grid[xs][ys].setFloor(true);
							}
						}
					}
					grid[r][c].setSpawn(true);

					r = c = gridSize; // Break out of the loop
				}
			}
		}
	}

	// Place the exit ladder on the map
	private void placeLadder() {
		for (int c = gridSize - 1; c >= 0; c--) {
			for (int r = gridSize - 1; r >= 0; r--) {
				if (grid[r][c].isFloor()) {
					// Creates a room starting at the ladder
					for (int xl = r - 4; xl < r; xl++) {
						for (int yl = c - 4; yl < c; yl++) {
							if (xl >= 0) {
								grid[xl + 1][yl + 1].setFloor(true);
							}
						}
					}
					grid[r][c].setLadder(true);

					r = c = 0; // Break out of the loop
				}
			}
		}
	}

	// Places the player sprite on the map
	private void placePlayer() {
		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (grid[r][c].isSpawn()) {
					for (int i = 0; i < entityHandler.getEntities().size(); i++) {
						Entity tempObject = entityHandler.getEntities().get(i);
						if (tempObject.getId() == ID.Player) {
							tempObject.setRow(r);
							tempObject.setCol(c);
						}
					}
				}
			}
		}
	}

	// Moves all of the monsters to their new locations on the map
	private void placeMonsters() {
		entityHandler.removeMonsters();

		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (grid[r][c].isMonster()) {
					entityHandler.addEntity(new Monster(r, c, ID.Monster, handler, this));
				}
			}
		}
	}

	// Place walls around the outside of the grid
	private void boundGen() {
		for (int i = 0; i < gridSize; i++) {
			grid[0][i].setWall(true); // top row
			grid[gridSize - 1][i].setWall(true); // bottom row
			grid[i][0].setWall(true); // left column
			grid[i][gridSize - 1].setWall(true); // right column
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
		int minRoomSize = 2;

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

		// Random Walls for Randomness
		for (int a = 0; a < 10; a++) {
			xt = rand.nextInt(w) + xo;
			yt = rand.nextInt(h) + yo;
			grid[yt][xt].setWall(true);
		}

		// Generate Treasure Tile in room
		xt = rand.nextInt(w) + xo;
		yt = rand.nextInt(h) + yo;
		grid[yt][xt].setTreasure(true);

		// Generate Monster in room
		xt = rand.nextInt(w) + xo;
		yt = rand.nextInt(h) + yo;
		grid[yt][xt].setMonster(true);

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
