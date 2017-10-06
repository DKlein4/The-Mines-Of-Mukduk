package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import input.KeyInput;

public class GUImain extends Canvas implements Runnable {

	private static final long serialVersionUID = 501298079830563846L;

	public static final int WIDTH = 900;
	public static final int HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;

	private Window window;
	private Game game;
	private Menu menu;

	public GUIstate guiState;

	public GUImain() {

		guiState = GUIstate.Menu;

		menu = new Menu(this);
		game = new Game();

		this.addKeyListener(new KeyInput());
		this.addMouseListener(menu);

		window = new Window(WIDTH, HEIGHT, "The Mines of Mukduk - Level 1", this);
	}

	private void tick() {
		// Tick the right screen depending on the state
		if (guiState == GUIstate.Menu)
			menu.tick();
		else if (guiState == GUIstate.Game)
			game.tick();

		// Update the title of the window
		window.setTitle("The Mines of Mukduk - Level " + game.getMap().getLevelNum());
	}

	private void render() {
		// Setup crap
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// Draw the background
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Render the right screen depending on the state
		if (guiState == GUIstate.Menu)
			menu.render(g);
		else if (guiState == GUIstate.Game)
			game.render(g);

		g.dispose();
		bs.show();
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		// int frames = 0;
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
			// frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				// frames = 0;
			}
		}
		stop();
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
}
