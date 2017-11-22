package items;

import java.awt.Graphics;

import entities.EntityHandler;
import main.Handler;

/**
 * An item is something that a player can obtain with different attributes. Is
 * stored in the inventory.
 * 
 * @author Dustin
 */
public abstract class Item {
	
	@SuppressWarnings("unused")
	private Handler handler;

	protected int ID;
	protected String name;
	protected int count;
	
	protected EntityHandler entityHandler;

	public Item(Handler handler, String name, int ID) {
		this.handler = handler;
		this.name = name;
		this.ID = ID;
		
		count = 1;
		
		entityHandler = handler.getWorld().getEntityHandler();
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void use();

	// GETTERS AND SETTERS

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
