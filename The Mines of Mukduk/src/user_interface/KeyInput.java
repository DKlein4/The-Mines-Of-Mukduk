package user_interface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entities.Entity;
import entities.ID;

/**
 * @author Dustin; This class handles the keyboard input
 */
public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[4];

	public KeyInput(Handler handler) {
		this.handler = handler;
		keyDown[0] = false; // W
		keyDown[1] = false; // S
		keyDown[2] = false; // D
		keyDown[3] = false; // A
	}

	// When a key is pressed loop through all of the entities and check if any
	// actions should be performed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.entity.size(); i++) {
			Entity tempObject = handler.entity.get(i);

			// If there is a player entity and one of these keys are pressed
			// move the player
			if (tempObject.getId() == ID.Player) {
				// When W is pressed move the player one row up
				if (key == KeyEvent.VK_W) {
					if (tempObject.isValidMove(tempObject.getRow() - 1, tempObject.getCol())) {
						tempObject.setRow(tempObject.getRow() - 1);
						entityCollision(tempObject);
						keyDown[0] = true;
					}
				}
				// When S is pressed move the player one row down
				if (key == KeyEvent.VK_S) {
					if (tempObject.isValidMove(tempObject.getRow() + 1, tempObject.getCol())) {
						tempObject.setRow(tempObject.getRow() + 1);
						entityCollision(tempObject);
						keyDown[1] = true;
					}
				}
				// When D is pressed move the player one col right
				if (key == KeyEvent.VK_D) {
					if (tempObject.isValidMove(tempObject.getRow(), tempObject.getCol() + 1)) {
						tempObject.setCol(tempObject.getCol() + 1);
						entityCollision(tempObject);
						keyDown[2] = true;
					}
				}
				// When A is pressed move the player one col left
				if (key == KeyEvent.VK_A) {
					if (tempObject.isValidMove(tempObject.getRow(), tempObject.getCol() - 1)) {
						tempObject.setCol(tempObject.getCol() - 1);
						entityCollision(tempObject);
						keyDown[3] = true;
					}
				}
			}
		}
		// Exit if escape is pressed
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	public void entityCollision(Entity tempObject) {
		tempObject.isTreasure(tempObject.getRow(), tempObject.getCol());
		tempObject.isLadder(tempObject.getRow(), tempObject.getCol());
		tempObject.isMonster(tempObject.getRow(), tempObject.getCol());
	}

	// When a key is released loop through all of the entities and check if any
	// actions should be performed
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.entity.size(); i++) {
			Entity tempObject = handler.entity.get(i);

			// If there is a player entity and one of these keys are released
			// update the keyDown array
			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W)
					keyDown[0] = false;
				if (key == KeyEvent.VK_S)
					keyDown[1] = false;
				if (key == KeyEvent.VK_D)
					keyDown[2] = false;
				if (key == KeyEvent.VK_A)
					keyDown[3] = false;
			}
		}
	}
}