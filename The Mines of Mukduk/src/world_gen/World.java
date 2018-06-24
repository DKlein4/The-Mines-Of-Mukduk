package world_gen;

import java.awt.Graphics;

import display.Noise;
import entities.EntityHandler;
import entities.ID;
import entities.Monster;
import entities.Player;
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
	private Noise noise;

	public World(Handler handler) {
		this.handler = handler;

		entityHandler = new EntityHandler();
		map = new Map(handler, entityHandler);
		inventory = new Inventory();
		noise = new Noise();

		gridSize = map.getGridSize();

		genPlayer();
		genMonsters();
	}

	public void tick() {
		tickMap();
		entityHandler.tick();
		inventory.tick();
		handler.getGuiMain().getWindow().setTitle("The Mines of Mukduk - Level " + map.getLevelNum() + " - Steps: " + entityHandler.getPlayer().getSteps());
		//noise.tick();
	}

	public void render(Graphics g) {
		renderMap(g);
		entityHandler.render(g);
		inventory.render(g);
		//noise.render(g);
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

	private void tickMap() {
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				map.getTile(r, c).tick();
			}
		}
	}

	// Renders the elements of the map onto the screen
	private void renderMap(Graphics g) {
		for (int r = 0; r < gridSize; r++) {
			for (int c = 0; c < gridSize; c++) {
				int cameraOffsetX = (int) handler.getGuiMain().getGameCamera().getxOffset();
				int cameraOffsetY = (int) handler.getGuiMain().getGameCamera().getyOffset();

				int x = c * Tile.TILE_WIDTH - cameraOffsetX;
				int y = r * Tile.TILE_HEIGHT - cameraOffsetY;
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
