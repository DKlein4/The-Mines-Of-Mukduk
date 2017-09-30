package entities;

import java.util.Random;

/**
 * @author Brett; This is the class that holds all the switch-case tables for
 *         random rolls
 */
public class Tables {
	Random rand = new Random();

	public void lootRoll() {
		int randRoll = rand.nextInt((100-1) + 1) + 1;		
		if (randRoll <= 30) {
			armorRoll();
			System.out.print(randRoll);
			System.out.println(": Armor");
		}
		else if (randRoll > 30 && randRoll <= 45) {
			weaponRoll();
			System.out.print(randRoll);
			System.out.println(": Weapon");
		}
		else if (randRoll > 45 && randRoll <= 55) {
			potionRoll();
			System.out.print(randRoll);
			System.out.println(": Potion");
		}
		else if (randRoll > 55 && randRoll <= 60) {
			scrollRoll();
			System.out.print(randRoll);
			System.out.println(": Scroll");
		}
		else if (randRoll > 60 && randRoll <= 80) {
			foodRoll();
			System.out.print(randRoll);
			System.out.println(": Food");
		}
		else if (randRoll > 80 && randRoll < 100) {
			goldRoll();
			System.out.print(randRoll);
			System.out.println(": Gold");
		}
		else if (randRoll == 100) {
			System.out.print(randRoll);
			System.out.println(": Two Items!");
			System.out.print("Item One: ");
			lootRoll();
			System.out.print("Item Two: ");
			lootRoll();
		}
	}

	public void armorRoll() {
		//Boots
		//Legs
		//Chest
		//Helmet
		//Ring
		//Cape
	}

	public void weaponRoll() {
		
	}

	public void potionRoll() {
		//Potion of Nourishment - completely fills hunger
		//Potion of Health - Restores HP; might have Lesser, Normal, Greater
		//
	}

	public void scrollRoll() {
		//Scroll of Food - generates 3-5 food items
		//Scroll of Healing - Restored HP
		//Scroll of Elemental Damage
			//Fire
			//Lighting
			//Cold
		//
	}

	public void foodRoll() {
		//Various food items that heal various small amounts
		//Must eat or starve to death
	}

	public void goldRoll() {

	}

	public void trapRoll() {

	}

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
}
