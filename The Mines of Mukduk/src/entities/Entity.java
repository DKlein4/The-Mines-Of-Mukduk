package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	protected int row, col;
	protected ID id;
	
	public Entity(int r, int c, ID id){
		this.row = r;
		this.col = c;
		this.id = id;
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
    
    
}
