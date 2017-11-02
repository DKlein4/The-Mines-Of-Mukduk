package world_gen;

import java.util.Random;

import display.MessageNotifier;
import items.Inventory;
import items.Item;
import main.Handler;

/**
 * This is the class that holds all the tables for random rolls
 * 
 * @author Brett
 */
public class Tables {
	
	private MessageNotifier messanger;
	private Random rand;
	private Inventory inventory;

	public Tables(Handler handler) {
		messanger = handler.getGuiMain().getMessageNotifier();
		inventory = new Inventory();
		rand = new Random();
	}

	// Randomly generates a piece of loot and adds it to the players inventory
	public void lootRoll() {
		int randRoll = rand.nextInt((100 - 1) + 1) + 1;
		if (randRoll <= 30) {
			armorRoll();
			inventory.addItem(new Item("Armor", 0));
			messanger.showMessage("You picked up ARMOR");
		} else if (randRoll > 30 && randRoll <= 45) {
			weaponRoll();
			inventory.addItem(new Item("Weapon", 1));
			messanger.showMessage("You picked up a WEAPON");
		} else if (randRoll > 45 && randRoll <= 55) {
			potionRoll();
			inventory.addItem(new Item("Potion", 2));
			messanger.showMessage("You picked up a POTION");
		} else if (randRoll > 55 && randRoll <= 60) {
			scrollRoll();
			inventory.addItem(new Item("Scroll", 3));
			messanger.showMessage("You picked up a SCROLL");
		} else if (randRoll > 60 && randRoll <= 80) {
			foodRoll();
			inventory.addItem(new Item("Food", 4));
			messanger.showMessage("You picked up FOOD");
		} else if (randRoll > 80 && randRoll < 100) {
			goldRoll();
			inventory.addItem(new Item("Gold", 5));
			messanger.showMessage("You picked up GOLD");
		} else if (randRoll == 100) {
			lootRoll();
			lootRoll();
		}
	}

	// Generates a random number to decide whether an encounter will occur
	public void encounterRoll() {
		switch (rand.nextInt((100 - 1) + 1) + 1) {
		// 1-10 creates a loot roll
		case 1:
		case 10:
			lootRoll();
			break;
		// 11-15 creates a trap roll
		case 11:
		case 15:
			trapRoll();
			break;
		}
	}

	// HELPER FUNCTIONS

	public void armorRoll() {
		// Boots
		// Legs
		// Chest
		// Helmet
		// Ring
		// Cape
	}

	public void weaponRoll() {

	}

	public void potionRoll() {
		// Potion of Nourishment - completely fills hunger
		// Potion of Health - Restores HP; might have Lesser, Normal, Greater
		//
	}

	public void scrollRoll() {
		// Scroll of Food - generates 3-5 food items
		// Scroll of Healing - Restored HP
		// Scroll of Elemental Damage
		// Fire
		// Lighting
		// Cold
		//
	}

	public void foodRoll() {
		// Various food items that heal various small amounts
		// Must eat or starve to death
	}

	public void goldRoll() {

	}

	public void trapRoll() {

	}
}
