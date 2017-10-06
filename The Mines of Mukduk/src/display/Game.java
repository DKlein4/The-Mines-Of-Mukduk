package display;


import java.awt.Color;
import java.awt.Graphics;

import world_gen.Map;
import Main.main;
import entities.EntityHandler;
import entities.ID;
import entities.Player;
import items.Inventory;

/**
 * @author Dustin; This is the main class that creates the GUI and manages the
 *         actions on the GUI
 */
public class Game {

	private int gridSize = main.gridSize; // Number of tiles on the map
	private int tileSizeX; // The size of the tile in the x direction
	private int tileSizeY; // The size of the tile in the y direction
	private int gridOffsetX; // The offset of the grid in the x direction for
								// formating
	private int gridOffsetY; // The offset of the grid in the y direction for
								// formating

	private EntityHandler entityHandler;
	private Inventory inventory;
	private Map map;

	public Game() {
		tileSizeX = (GUImain.WIDTH / main.gridSize) - 1;
		tileSizeY = (GUImain.HEIGHT / main.gridSize) - 1;

		gridOffsetX = tileSizeX * 3 / 4;
		gridOffsetY = tileSizeY;

		this.map = new Map(gridSize);
		this.entityHandler = new EntityHandler();
		this.inventory = new Inventory();

		genPlayer();
	}

	public void tick() {
		entityHandler.tick();
		inventory.tick();
	}

	public void render(Graphics g) {
		renderGrid(g);
		EntityHandler.render(g);
		inventory.render(g);
	}
	
	
	// GETTERS AND SETTERS
	
	
	public Map getMap(){
		return map;
	}

	
	// HELPER FUNCTIONS

	
	// Creates a player entity and places it on the map
	private void genPlayer() {
		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (map.getGrid(r, c).isSpawn() == true) {
					EntityHandler.addEntity(new Player(r, c, ID.Player, entityHandler, map));

					r = c = gridSize; // Break out of the loop
				}
			}
		}
	}

	// Renders the elements of the map onto the screen
	private void renderGrid(Graphics g) {
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				g.setColor(Color.white);
				if (map.getGrid(r, c).isWall()) {
					g.setColor(Color.black);
					g.drawString("▓", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else if (map.getGrid(r, c).isFloor())
					g.drawString(".", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				else if (map.getGrid(r, c).isSpawn()) {
					g.drawString(".", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else if (map.getGrid(r, c).isLadder()) {
					g.setColor(Color.green);
					g.drawString("▼", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else if (map.getGrid(r, c).isUnexplored())
					g.drawString("U", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				else if (map.getGrid(r, c).isTreasure()) {
					g.setColor(Color.yellow);
					g.drawString("₧", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else if (map.getGrid(r, c).isMonster()) {
					g.setColor(Color.red);
					g.drawString("§", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else
					g.drawString("X", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
			}
		}
	}
}
