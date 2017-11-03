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

	private MessageNotifier messenger;
	private Random rand;
	private Inventory inventory;

	public Tables(Handler handler) {
		messenger = handler.getGuiMain().getMessageNotifier();
		inventory = new Inventory();
		rand = new Random();
	}

	// Randomly generates a piece of loot and adds it to the players inventory
	public void lootRoll() {
		int randRoll = rand.nextInt((100 - 1) + 1) + 1;
		if (randRoll <= 30) {
			armorRoll();
			inventory.addItem(new Item("Armor", 0));
			messenger.showMessage("You picked up ARMOR");
		} else if (randRoll > 30 && randRoll <= 45) {
			weaponRoll();
			inventory.addItem(new Item("Weapon", 1));
			messenger.showMessage("You picked up a WEAPON");
		} else if (randRoll > 45 && randRoll <= 55) {
			potionRoll();
			inventory.addItem(new Item("Potion", 2));
			messenger.showMessage("You picked up a POTION");
		} else if (randRoll > 55 && randRoll <= 60) {
			scrollRoll();
			inventory.addItem(new Item("Scroll", 3));
			messenger.showMessage("You picked up a SCROLL");
		} else if (randRoll > 60 && randRoll <= 80) {
			foodRoll();
			inventory.addItem(new Item("Food", 4));
			messenger.showMessage("You picked up FOOD");
		} else if (randRoll > 80 && randRoll < 100) {
			goldRoll();
			inventory.addItem(new Item("Gold", 5));
			messenger.showMessage("You picked up GOLD");
		} else if (randRoll == 100) {
			messenger.showMessage("Wow! These treasures are glued together!");
			lootRoll();
			lootRoll();
		}
	}

	// Generates a random number to decide whether an encounter will occur
	public void encounterRoll() {
		int randRoll = rand.nextInt((100 - 1) + 1) + 1;
		if (randRoll <= 5) {
			messenger.showMessage("Treasure Encounter");
			//lootRoll();
		} else if (randRoll > 5 && randRoll <= 10) {
			messenger.showMessage("Event Encounter");

		} else if (randRoll > 10 && randRoll <= 12) {
			messenger.showMessage("OH SHIT, IT'S A TRAP");

		} else if (randRoll > 12 && randRoll <= 13) {
			messenger.showMessage("Combat...?");

		} else if (randRoll > 13 && randRoll <= 80) {

		} else if (randRoll > 80 && randRoll < 100) {

		} else if (randRoll == 100) {
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
