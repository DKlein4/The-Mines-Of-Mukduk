package items;

/**
 * @author Dustin; An item is something that a player can obtain with different
 *         attributes. Is stored in the inventory.
 */
public class Item {

	protected int ID;
	protected String name;
	protected int count;

	public Item(String name, int ID) {
		this.name = name;
		this.ID = ID;
		count = 1;
	}

	
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
