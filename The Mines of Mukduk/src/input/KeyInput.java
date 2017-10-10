package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import display.GUImain;
import display.GUIstate;
import entities.Entity;
import entities.EntityHandler;
import entities.ID;
import game_stages.PlayerMovement;
import items.Inventory;
import main.Handler;

/**
 * @author Dustin; This class handles the keyboard input.
 */
public class KeyInput extends KeyAdapter {

	private boolean[] keyDown = new boolean[4];

	private GUImain guiMain;
	private EntityHandler entityHandler;
	private PlayerMovement playerMovement;
	private Inventory inventory;

	public KeyInput(Handler handler) {
		keyDown[0] = false; // W
		keyDown[1] = false; // S
		keyDown[2] = false; // D
		keyDown[3] = false; // A
		
		this.guiMain = handler.getGuiMain();
		this.entityHandler = handler.getWorld().getEntityHandler();
		playerMovement = new PlayerMovement();
		inventory = new Inventory();
	}

	// When a key is pressed loop through all of the entities and check if any
	// actions should be performed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// Only use theses actions when in the game state
		if (guiMain.guiState == GUIstate.Game) {
			// Toggle inventory screen if E is pressed
			if (key == KeyEvent.VK_E)
				inventory.toggleActive();
			// If there is a player entity and one of these keys are pressed
			// move the player
			for (int i = 0; i < entityHandler.getEntities().size(); i++) {
				Entity tempObject = entityHandler.getEntities().get(i);
				if (tempObject.getId() == ID.Player) {
					// When W is pressed move the player one row up
					if (key == KeyEvent.VK_W) {
						playerMovement.moveUp(tempObject);
						keyDown[0] = true;
					}
					// When S is pressed move the player one row down
					if (key == KeyEvent.VK_S) {
						playerMovement.moveDown(tempObject);
						keyDown[1] = true;
					}
					// When D is pressed move the player one col right
					if (key == KeyEvent.VK_D) {
						playerMovement.moveRight(tempObject);
						keyDown[2] = true;
					}
					// When A is pressed move the player one col left
					if (key == KeyEvent.VK_A) {
						playerMovement.moveLeft(tempObject);
						keyDown[3] = true;
					}
				}
			}
		}
		
		// Exit if escape is pressed
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	// When a key is released loop through all of the entities and check if any
	// actions should be performed
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < entityHandler.getEntities().size(); i++) {
			Entity tempObject = entityHandler.getEntities().get(i);
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