package entities;

import java.util.Random;

/**
 * @author Brett; This is the class that holds all the switch-case tables for random rolls
 */
public class Tables {
Random rand = new Random();

	public void lootRoll() {
		//armorRoll();
		//weaponRoll();
		//potionRoll();
		//scrollRoll();
		//goldRoll();
	}
	
	public void trapRoll() {
		
	}
	
	public void encounterRoll() {
		switch(rand.nextInt((100-1) + 1) + 1) {
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
