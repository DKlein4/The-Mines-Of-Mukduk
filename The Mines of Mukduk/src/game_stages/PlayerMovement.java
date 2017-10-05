package game_stages;

import entities.Entity;

/**
 * @author Dustin; Performs the correct actions depending on which way the
 *         player wants to move
 */
public class PlayerMovement {

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
