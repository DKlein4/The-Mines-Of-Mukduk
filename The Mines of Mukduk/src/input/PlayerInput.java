package input;


import entities.Player;
import items.Inventory;
import main.Handler;

/**
 * @author Dustin; Performs the correct actions depending on which way the
 *         player wants to move
 */
public class PlayerInput {
	
	private Handler handler;
	private KeyInput keyInput;
	private Player player;
	private Inventory inventory;
	
	private boolean[] keyDown;
	private boolean init;
	
	public PlayerInput(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		init = false;
	}
	
	public void tick(){
		// Get these things only once
		if(!init){
			keyInput = handler.getKeyInput();
			keyDown = keyInput.getKeyDown();
			inventory = handler.getWorld().getInventory();
			
			init = true;
		}
		
		// E pressed
		if(keyDown[4]){
			inventory.toggleActive();
			keyDown[4] = false;
		}
		
		// Break if the inventory is active
		if(inventory.isActive())
			return;

		// W pressed
		if(keyDown[0]){
			moveUp();
			keyDown[0] = false;
		}
		// S pressed
		if(keyDown[1]){
			moveDown();
			keyDown[1] = false;
		}
		// D pressed
		if(keyDown[2]){
			moveRight();
			keyDown[2] = false;
		}
		// A pressed
		if(keyDown[3]){
			moveLeft();
			keyDown[3] = false;
		}
	}

	public void moveUp() {
		if (player.getMap().isValidMove(player.getRow() - 1, player.getCol())) {
			player.setRow(player.getRow() - 1);
			player.collision();
		}
	}

	public void moveDown() {
		if (player.getMap().isValidMove(player.getRow() + 1, player.getCol())) {
			player.setRow(player.getRow() + 1);
			player.collision();
		}
	}

	public void moveRight() {
		if (player.getMap().isValidMove(player.getRow(), player.getCol() + 1)) {
			player.setCol(player.getCol() + 1);
			player.collision();
		}
	}

	public void moveLeft() {
		if (player.getMap().isValidMove(player.getRow(), player.getCol() - 1)) {
			player.setCol(player.getCol() - 1);
			player.collision();
		}
	}
}
