package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.main;
import user_interface.GUIMain;

public abstract class Entity {
	protected int row, col;
	protected int tileSizeX, tileSizeY;
	protected int gridOffsetX, gridOffsetY;
	protected int xPos, yPos;
	protected ID id;

	public Entity(int r, int c, ID id) {
		this.row = r;
		this.col = c;
		this.id = id;

		tileSizeX = (GUIMain.WIDTH / main.gridSize) - 1;
		tileSizeY = (GUIMain.HEIGHT / main.gridSize) - 1;

		gridOffsetX = (tileSizeX * 3 / 4) * 3 / 4;
		gridOffsetY = tileSizeY / 4;

		xPos = (col * tileSizeX) + gridOffsetX;
		yPos = (row * tileSizeY) + gridOffsetY;
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
}
