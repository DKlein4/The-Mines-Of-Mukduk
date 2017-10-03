package user_interface;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import world_gen.Map;
import world_gen.World;
import Main.main;
import entities.EntityHandler;
import entities.ID;
import entities.Player;
import items.Inventory;

/**
 * @author Dustin; This is the main class that creates the GUI and manages the
 *         actions on the GUI
 */
public class GUIMain extends Canvas implements Runnable {

	private static final long serialVersionUID = 501298079830563846L;

	public static final int WIDTH = 900; // Width of the screen
	public static final int HEIGHT = WIDTH / 12 * 9; // Height of the screen
	private Thread thread;
	private boolean running = false;

	private int gridSize = main.gridSize; // Number of tiles on the map
	private int tileSizeX; // The size of the tile in the x direction
	private int tileSizeY; // The size of the tile in the y direction
	private int gridOffsetX; // The offset of the grid in the x direction for
								// formating
	private int gridOffsetY; // The offset of the grid in the y direction for
								// formating
	
	private Window window;

	private EntityHandler entityHandler;
	
	private Inventory inventory;

	private Map map;

	public GUIMain(World world) {

		tileSizeX = (GUIMain.WIDTH / main.gridSize) - 1;
		tileSizeY = (GUIMain.HEIGHT / main.gridSize) - 1;

		gridOffsetX = tileSizeX * 3 / 4;
		gridOffsetY = tileSizeY;

		this.map = world.level1;

		this.entityHandler = new EntityHandler();
		
		this.inventory = new Inventory();

		this.addKeyListener(new KeyInput(entityHandler));

		// Creates the game screen
		window = new Window(WIDTH, HEIGHT, "The Mines of Mukduk - Level 1", this);

		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (map.getGrid(r, c).isSpawn() == true) {
					EntityHandler.addEntity(new Player(r, c, ID.Player, entityHandler, map));

					r = c = gridSize; // Break out of the loop
				}
			}
		}
	}

	// Ran whenever started
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// Ran whenever ended
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				 //System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		entityHandler.tick();
		inventory.tick();
		
		window.setTitle("The Mines of Mukduk - Level " + map.getLevelNum());
	}

	// Puts things on the screen
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// Draw the background
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Add the elements of the map to the GUI

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
					g.drawString("M", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
				}
				else
					g.drawString("X", c * tileSizeX + gridOffsetX, r * tileSizeY + gridOffsetY);
			}

		}

	
		// Render the handler, which in turn renders the entities
		EntityHandler.render(g);
		
		inventory.render(g);

		g.dispose();
		bs.show();
	}

	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var < min)
			return var = min;
		else
			return var;
	}
}
