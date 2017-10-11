package world_gen;

import java.awt.Color;
import java.awt.Graphics;

import display.GUImain;
import entities.EntityHandler;
import entities.ID;
import entities.Player;
import items.Inventory;
import main.Handler;

public class World {

	private int gridSize;

	private Map map;

	private Handler handler;
	private EntityHandler entityHandler;
	private Inventory inventory;

	public World(Handler handler) {		
		this.handler = handler;
		
		entityHandler = new EntityHandler();
		map = new Map(entityHandler);
		inventory = new Inventory();
		
		gridSize = map.getGridSize();
		genPlayer();
	}

	public void tick() {
		entityHandler.tick();
		inventory.tick();
		handler.getGuiMain().getWindow().setTitle("The Mines of Mukduk - Level " + map.getLevelNum());
	}

	public void render(Graphics g) {
		renderMap(g);
		entityHandler.render(g);
		inventory.render(g);
	}

	// Creates a player entity and places it on the map
	private void genPlayer() {
		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (map.getTile(r, c).isSpawn() == true) {
					entityHandler.addEntity(new Player(r, c, ID.Player, handler, map));

					r = c = gridSize; // Break out of the loop
				}
			}
		}
	}

	// Renders the elements of the map onto the screen
	private void renderMap(Graphics g) {
		int tileSizeX; // The size of the tile in the x direction
		int tileSizeY; // The size of the tile in the y direction
		int gridOffsetX; // The offset of the grid in the x direction for
							// formating
		int gridOffsetY; // The offset of the grid in the y direction for
							// formating

		tileSizeX = (GUImain.WIDTH / gridSize) - 1;
		tileSizeY = (GUImain.HEIGHT / gridSize) - 1;

		gridOffsetX = tileSizeX * 3 / 4;
		gridOffsetY = tileSizeY;

		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				g.setColor(Color.white);
				if (map.getTile(r, c).isWall()) {
					g.setColor(Color.black);
					g.drawString("▓", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else if (map.getTile(r, c).isFloor())
					g.drawString(".", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				else if (map.getTile(r, c).isSpawn()) {
					g.drawString(".", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else if (map.getTile(r, c).isLadder()) {
					g.setColor(Color.green);
					g.drawString("▼", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else if (map.getTile(r, c).isUnexplored())
					g.drawString("U", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				else if (map.getTile(r, c).isTreasure()) {
					g.setColor(Color.yellow);
					g.drawString("₧", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else if (map.getTile(r, c).isMonster()) {
					g.setColor(Color.red);
					g.drawString("§", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				} else
					g.drawString("X", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
			}
		}
	}
	
	
	// GETTERS AND SETTERS
	
	
	public EntityHandler getEntityHandler(){
		return entityHandler;
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	public Map getMap(){
		return map;
	}
}
