package items;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import user_interface.GUIMain;

public class Inventory {

	public static ArrayList<Item> inventoryItems;
	private static boolean active;

	public Inventory() {
		inventoryItems = new ArrayList<Item>();
		active = false;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (active) {
			g.setColor(Color.BLACK);
			g.fillRect(50, 50, GUIMain.WIDTH - 100, GUIMain.HEIGHT - 130);

			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 30));
			g.drawString("Item", 250, 100);
			g.drawString("Count", 550, 100);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			for (int i = 1; i <= inventoryItems.size(); i++) {
				g.drawString(inventoryItems.get(i - 1).getName(), 250, 100 + 50 * i);
				g.drawString(Integer.toString(inventoryItems.get(i - 1).getCount()), 550, 100 + 50 * i);
			}
		}

	}

	public void addItem(Item item) {
		for (Item i : inventoryItems) {
			if (i.getID() == item.getID()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}

	public boolean isActive() {
		return active;
	}

	public void toggleActive() {
		active = !active;
	}
}
