package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class handles the keyboard input.
 * 
 * @author Dustin
 */
public class KeyInput extends KeyAdapter {

	private boolean[] keyDown = new boolean[5];

	public KeyInput() {
		keyDown[0] = false; // W
		keyDown[1] = false; // S
		keyDown[2] = false; // D
		keyDown[3] = false; // A
		keyDown[4] = false; // E
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W)
			keyDown[0] = true;
		if (key == KeyEvent.VK_S)
			keyDown[1] = true;
		if (key == KeyEvent.VK_D)
			keyDown[2] = true;
		if (key == KeyEvent.VK_A)
			keyDown[3] = true;
		if (key == KeyEvent.VK_E)
			keyDown[4] = true;

		// Exit if escape is pressed
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W)
			keyDown[0] = false;
		if (key == KeyEvent.VK_S)
			keyDown[1] = false;
		if (key == KeyEvent.VK_D)
			keyDown[2] = false;
		if (key == KeyEvent.VK_A)
			keyDown[3] = false;
		if (key == KeyEvent.VK_E)
			keyDown[4] = false;
	}

	public boolean[] getKeyDown() {
		return keyDown;
	}
}