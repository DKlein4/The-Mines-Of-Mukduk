package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.main;
import game_stages.Combat;
import user_interface.GUIMain;
import world_gen.Map;
import world_gen.Tables;

public abstract class Entity {
	protected int row, col; // Positions on the grid
	protected int tileSizeX, tileSizeY; // The sizes of an individual tile
	protected int gridOffsetX, gridOffsetY; // Offsets for formatting. Make the
											// entity line up with the grid
	protected int xPos, yPos; // Positions on the grid
	protected ID id; // Type of entity

	protected Map map;
	protected Combat combat;

	public Entity(int r, int c, ID id, Map map) {
		this.row = r;
		this.col = c;
		this.id = id;

		tileSizeX = (GUIMain.WIDTH / main.gridSize) - 1;
		tileSizeY = (GUIMain.HEIGHT / main.gridSize) - 1;

		// These numbers are the way that they are and IDK why
		gridOffsetX = (tileSizeX * 3 / 4) * 3 / 4;
		gridOffsetY = tileSizeY / 4;

		xPos = (col * tileSizeX) + gridOffsetX;
		yPos = (row * tileSizeY) + gridOffsetY;
	}
	
	public void collision(){
		map.isTreasure(row, col);
		map.isLadder(row, col);
		map.isMonster(row, col);
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

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
	
	public Map getMap(){
		return map;
	}
}
