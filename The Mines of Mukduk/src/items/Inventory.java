package items;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import display.GUImain;
import gfx.Text;

/**
 * This holds an arraylist of items that acts as the inventory for the player.
 * 
 * @author Dustin
 */
public class Inventory {

	// Stores all the items in the inventory
	private static ArrayList<Item> inventoryItems;
	// Used to determine whether or not the inventory should be rendered
	private static boolean active;
	
	private Comparator<Item> comp = new Comparator<Item>(){
		@Override
		public int compare(Item a, Item b) {
			return a.getName().compareTo(b.getName());
		}
	};

	public Inventory() {
		inventoryItems = new ArrayList<Item>();
		active = false;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (active) {
			int yTitleSpacing = GUImain.HEIGHT/6;
			int yItemSpacing = GUImain.HEIGHT/12;
			int xSpacing = GUImain.WIDTH/3;
			
			// Create the background of the inventory screen
			g.setColor(Color.BLACK);
			g.fillRect(50, 50, GUImain.WIDTH - 100, GUImain.HEIGHT - 130);

			// Add the titles of the columns
			Font titleFont = new Font("TimesRoman", Font.BOLD, GUImain.HEIGHT/14);
			Text.drawStringFrom(g, "Item", xSpacing, yTitleSpacing, true, Color.WHITE, titleFont);
			Text.drawStringFrom(g, "Count", xSpacing * 2, yTitleSpacing, true, Color.WHITE, titleFont);
			
			// Display each item in the inventory	
			Font itemFont = new Font("TimesRoman", Font.PLAIN, GUImain.HEIGHT/20);
			for (int i = 1; i <= inventoryItems.size(); i++) {
				Text.drawStringFrom(g, inventoryItems.get(i - 1).getName(), xSpacing, yTitleSpacing + yItemSpacing * i, true, Color.white, itemFont);
				Text.drawStringFrom(g, Integer.toString(inventoryItems.get(i - 1).getCount()), xSpacing * 2, yTitleSpacing + yItemSpacing * i, true, Color.white, itemFont);
			}
		}
	}

	// Adds an item to the inventory
	public void addItem(Item item) {
		for (Item i : inventoryItems) {
			// If it is already in the inventory, increase the count
			if (i.getID() == item.getID()) {
				i.setCount(i.getCount() + item.getCount());
				inventoryItems.sort(comp);
				return;
			}
		}
		// If it is not in the inventory, add it
		inventoryItems.add(item);
		inventoryItems.sort(comp);
	}

	// GETTERS AND SETTERS

	public boolean isActive() {
		return active;
	}

	public void toggleActive() {
		active = !active;
	}
}
