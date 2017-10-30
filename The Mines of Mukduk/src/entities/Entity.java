package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import gfx.GameCamera;
import main.Handler;
import world_gen.Map;
import world_gen.Tile;

/**
 * This is the father class for any entities. Contains the position, type, and
 * other describing info of the entity.
 * 
 * @author Dustin
 */
public abstract class Entity {

	protected int width; // width of the entity itself
	protected int height; // height of the entity itself

	protected int row, col; // Positions on the grid
	protected int tileWidth, tileHeight; // The sizes of an individual tile
	protected int gridOffsetX, gridOffsetY; // Offsets for formatting. Make the
											// entity line up with the grid
	protected int xPos, yPos; // Positions on the grid
	protected ID id; // Type of entity
	
	protected int health;
	protected int dexterity;
	protected int strength;

	protected GameCamera gameCamera;
	protected Map map;
	
	private Random rand;

	public Entity(int r, int c, ID id, Handler handler, Map map) {
		this.row = r;
		this.col = c;
		this.id = id;
		this.map = map;
		
		health = 20;
		dexterity = 10;
		strength = 10;

		gameCamera = handler.getGuiMain().getGameCamera();

		tileWidth = Tile.TILEWIDTH;
		tileHeight = Tile.TILEHEIGHT;

		width = Tile.TILEWIDTH;
		height = Tile.TILEHEIGHT;

		gridOffsetX = 0;
		gridOffsetY = -tileHeight / 8;

		xPos = (col * tileWidth) + gridOffsetX;
		yPos = (row * tileHeight) + gridOffsetY;
		
		rand = new Random();
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();
	
	public int initiativeRoll() {
		return (rand.nextInt(20) + 1 + (this.dexterity - 10) / 2);
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

}
