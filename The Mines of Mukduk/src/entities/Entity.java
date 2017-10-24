package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import display.GUImain;
import gfx.Assets;
import world_gen.Map;

/**
 * @author Dustin; This is the father class for any entities. Contains the
 *         position, type, and other describing info of the entity.
 */
public abstract class Entity {
	
	protected int row, col; // Positions on the grid
	protected int tileSizeX, tileSizeY; // The sizes of an individual tile
	protected int gridOffsetX, gridOffsetY; // Offsets for formatting. Make the
											// entity line up with the grid
	protected int xPos, yPos; // Positions on the grid
	protected ID id; // Type of entity

	protected Map map;

	public Entity(int r, int c, ID id, Map map) {
		this.row = r;
		this.col = c;
		this.id = id;
		this.map = map;

		tileSizeX = Assets.width;
		tileSizeY = Assets.height;

		// These numbers are the way that they are and IDK why
		gridOffsetX = 0;
		gridOffsetY = - tileSizeY / 8;

		xPos = (col * tileSizeX) + gridOffsetX;
		yPos = (row * tileSizeY) + gridOffsetY;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	// Checks the entity's position to see if any actions need to be performed
	public void collision() {
		map.isTreasure(row, col);
		map.isLadder(row, col);
		map.isMonster(row, col);
	}

	// GETTERS AND SETTERS

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public Map getMap() {
		return map;
	}
}
