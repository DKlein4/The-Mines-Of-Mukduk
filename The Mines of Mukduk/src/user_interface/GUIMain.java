package user_interface;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import world_gen.Map;
import Main.main;

public class GUIMain extends Canvas implements Runnable {

	private static final long serialVersionUID = 501298079830563846L;

	public static final int WIDTH = 900, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;

	private int gridSize = main.gridSize;
	private int tileSizeX;
	private int tileSizeY;
	
	private Map map;

	public GUIMain(Map map) {
		new Window(WIDTH, HEIGHT, "The Mines of Mukduk", this);
		this.map = map;
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

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
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
	}

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

		// Add in the lines to make a grid
		tileSizeX = WIDTH / gridSize;
		tileSizeY = HEIGHT / gridSize;
		
		g.setColor(Color.white);
		// Add the elements of the map to the gui
		for (int c = 0; c < gridSize; c++) {
			for (int r = 0; r < gridSize; r++) {
				if (map.getGrid(r, c).isWall())
					g.drawString("#", c*tileSizeX + (tileSizeX/2), r*tileSizeY + (tileSizeY/2));
				else if (map.getGrid(r, c).isFloor())
					g.drawString(".", c*tileSizeX + (tileSizeX/2), r*tileSizeY + (tileSizeY/2));
				else if (map.getGrid(r, c).isDoor())
					g.drawString("D", c*tileSizeX + (tileSizeX/2), r*tileSizeY + (tileSizeY/2));
				else if (map.getGrid(r, c).isUnexplored())
					g.drawString("U", c*tileSizeX + (tileSizeX/2), r*tileSizeY + (tileSizeY/2));
				else if (map.getGrid(r, c).isTreasure())
					g.drawString("T", c*tileSizeX + (tileSizeX/2), r*tileSizeY + (tileSizeY/2));
				else if (map.getGrid(r, c).isMonster())
					g.drawString("M", c*tileSizeX + (tileSizeX/2), r*tileSizeY + (tileSizeY/2));
				else
					g.drawString("X", c*tileSizeX + (tileSizeX/2), r*tileSizeY + (tileSizeY/2));
			}
		}

		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var < min)
			return var = min;
		else
			return var;
	}

}
