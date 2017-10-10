package game_stages;


import entities.Entity;
import entities.Player;
import input.KeyInput;
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
	
	public PlayerInput(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
	}
	
	public void tick(){
		keyInput = handler.getKeyInput();
		keyDown = keyInput.getKeyDown();
		
		inventory = handler.getWorld().getInventory();
		
		if(keyDown[4])
			inventory.toggleActive();
		
		if(inventory.isActive())
			return;

		if(keyDown[0])
			moveUp(player);
		if(keyDown[1])
			moveDown(player);
		if(keyDown[2])
			moveRight(player);
		if(keyDown[3])
			moveLeft(player);
	}

	public void moveUp(Entity entity) {
		if (entity.getMap().isValidMove(entity.getRow() - 1, entity.getCol())) {
			entity.setRow(entity.getRow() - 1);
			entity.collision();
		}
	}

	public void moveDown(Entity entity) {
		if (entity.getMap().isValidMove(entity.getRow() + 1, entity.getCol())) {
			entity.setRow(entity.getRow() + 1);
			entity.collision();
		}
	}

	public void moveRight(Entity entity) {
		if (entity.getMap().isValidMove(entity.getRow(), entity.getCol() + 1)) {
			entity.setCol(entity.getCol() + 1);
			entity.collision();
		}
	}

	public void moveLeft(Entity entity) {
		if (entity.getMap().isValidMove(entity.getRow(), entity.getCol() - 1)) {
			entity.setCol(entity.getCol() - 1);
			entity.collision();
		}
	}
}
