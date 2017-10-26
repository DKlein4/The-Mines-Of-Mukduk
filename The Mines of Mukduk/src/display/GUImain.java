package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.gui_states.GUIstate;
import display.gui_states.GameState;
import display.gui_states.MenuState;
import display.gui_states.TutorialState;
import gfx.Assets;
import gfx.GameCamera;
import input.KeyInput;
import input.MouseInput;
import main.Handler;

/**
 * This is the main class that creates the GUI and manages the actions on the
 * GUI.
 * 
 * @author Dustin
 */
public class GUImain extends Canvas implements Runnable {

	private static final long serialVersionUID = 501298079830563846L;

	// Window stuff
	private Window window;
	public static int WIDTH = 1080;
	public static int HEIGHT = WIDTH / 16 * 9;

	// Running stuff
	private Thread thread;
	private boolean running = false;

	// GUI states
	public GUIstate gameState;
	public GUIstate menuState;
	public GUIstate tutorialState;

	// Input
	private KeyInput keyInput;
	private MouseInput mouseInput;

	// Other stuff
	private Handler handler;
	private GameCamera gameCamera;

	public GUImain() {
		Assets.init();

		handler = new Handler(this);

		gameCamera = new GameCamera(handler, 0, 0);

		mouseInput = new MouseInput();
		keyInput = new KeyInput();
		this.addKeyListener(keyInput);
		this.addMouseListener(mouseInput);

		gameState = new GameState(handler);
		menuState = new MenuState(handler, mouseInput);
		tutorialState = new TutorialState(handler, mouseInput);
		GUIstate.setState(menuState);

		window = new Window(WIDTH, HEIGHT, "The Mines of Mukduk", this);
		
		WIDTH = window.getWidth();
		HEIGHT = window.getHeight();
	}

	private void tick() {
		// Tick the right screen depending on the state
		if (GUIstate.getState() != null)
			GUIstate.getState().tick();
	}

	private void render() {
		WIDTH = window.getWidth();
		HEIGHT = window.getHeight();
		
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
		if (GUIstate.getState() != null)
			GUIstate.getState().render(g);

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

	// GETTERS AND SETTERS

	public KeyInput getKeyInput() {
		return keyInput;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public MouseInput getMouseInput() {
		return mouseInput;
	}

	public Window getWindow() {
		return window;
	}
}
