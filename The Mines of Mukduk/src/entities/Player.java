package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import gfx.Assets;
import input.PlayerInput;
import items.Inventory;
import main.Handler;
import world_gen.Map;

/**
 * The entity that the user plays as.
 * 
 * @author Dustin
 */
public class Player extends Entity {

	private Inventory inventory;
	private PlayerInput playerInput;
	private int steps;
	
	public Player(int row, int col, ID id, Handler handler, Map map) {
		super(row, col, ID.Player, handler, map);
		
		inventory = new Inventory();
		playerInput = new PlayerInput(handler, this);
		
		health = 30;
		
		steps = 0;
	}

	public void tick() {
		// Update the position of the player
		xPos = (col * tileWidth) + gridOffsetX;
		yPos = (row * tileHeight) + gridOffsetY;
		
		gameCamera.centerOnEntity(this);
		
		inventory.tick();
		playerInput.tick();
	}

	public void render(Graphics g) {
		// Draws the player on the GUI. Right now its just a sexy Dorf
		g.drawImage(Assets.player, (int) (xPos - gameCamera.getxOffset()), (int) (yPos - gameCamera.getyOffset()), width, height, null);
		
		inventory.render(g);
	}

	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, width, height);
	}
	
	// Checks the entity's position to see if any actions need to be performed
	public void collision() {
		map.isTreasure(row, col);
		map.isLadder(row, col);
		map.isMonster(row, col);
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public void step() {
		steps++;
	}
	
	public int getSteps() {
		return steps;
	}
}
