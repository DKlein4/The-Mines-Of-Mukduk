package world_gen;

import java.awt.Graphics;

import entities.EntityHandler;
import entities.ID;
import entities.Monster;
import entities.Player;
import gfx.Assets;
import items.Inventory;
import main.Handler;

/**
 * Holds the map and other aspects of the game.
 * 
 * @author Dustin
 */
public class World {

	private int gridSize;

	private Map map;

	private Handler handler;
	private EntityHandler entityHandler;
	private Inventory inventory;

	public World(Handler handler) {
		this.handler = handler;

		entityHandler = new EntityHandler();
		map = new Map(handler, entityHandler);
		inventory = new Inventory();

		gridSize = map.getGridSize();

		genPlayer();
		genMonsters();
	}

	public void tick() {
		entityHandler.tick();
		inventory.tick();
		handler.getGuiMain().getWindow().setTitle("The Mines of Mukduk - Level " + map.getLevelNum() + " - Steps: " + entityHandler.getPlayer().getSteps());
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
				if (map.getTile(r, c).isSpawn()) {
					entityHandler.setPlayer(new Player(r, c, ID.Player, handler, map));
					return;
				}
			}
		}
	}

	// Places monster entities on every tile that has that attribute
	private void genMonsters() {
		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (map.getTile(r, c).isMonster()) {
					entityHandler.addEntity(new Monster(r, c, ID.Monster, handler, map));
				}
			}
		}
	}

	// Renders the elements of the map onto the screen
	private void renderMap(Graphics g) {
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				int cameraOffsetX = (int) handler.getGuiMain().getGameCamera().getxOffset();
				int cameraOffsetY = (int) handler.getGuiMain().getGameCamera().getyOffset();

				int x = c * Tile.TILEWIDTH - cameraOffsetX;
				int y = r * Tile.TILEHEIGHT - cameraOffsetY;
				map.getTile(r, c).render(g, x, y);
			}
		}
	}

	// GETTERS AND SETTERS

	public EntityHandler getEntityHandler() {
		return entityHandler;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Map getMap() {
		return map;
	}
}
