package items;

import java.awt.Graphics;
import java.util.ArrayList;

public class Inventory {
	
	public static ArrayList<Item> inventoryItems;
	private boolean active;
	
	public Inventory(){
		inventoryItems = new ArrayList<Item>();
		active = false;
	}
	
	public void tick(){

	}
	
	public void render(Graphics g){
		
	}
	
	public void addItem(Item item){
		for(Item i : inventoryItems){
			if(i.getID() == item.getID()){
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	
	public boolean isActive(){
		return active;
	}
}
