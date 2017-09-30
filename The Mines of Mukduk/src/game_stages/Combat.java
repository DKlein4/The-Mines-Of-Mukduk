package game_stages;

import entities.Monster;
import entities.Player;
import user_interface.UI;

public class Combat {
	UI userInterface = new UI();
	
	public void combatRound() {
		userInterface.battleStart();

		// Generates new enemy object
//		Monster monster = new Monster();

		// Player object
//		Player player = new Player();

		// Generates player stats
//		int playerInitiative = player.getPlayerInitiative();

		boolean notDead = true;
		while (notDead == true) {
			// Player goes first
//			if (checkInitiative(monster.getInitiative(), playerInitiative) == true) {
//				playerTurn();
//				monsterTurn();
//			}
			// Monster goes first
//			else if (checkInitiative(monster.getInitiative(), playerInitiative) == false) {
//				monsterTurn();
//				playerTurn();
//			}
		}
	}

	public boolean checkInitiative(int monsterInit, int playerInit) {
		// Rolls initiative out of 20, highest goes first.
		if (monsterInit > playerInit) {
			return false;
		} else if (monsterInit < playerInit) {
			return true;
		}
		// In case of tie, player goes first
		else {
			return true;
		}
	}

	public void monsterTurn() {
		// What the monster does on it's turn
	}

	public void playerTurn() {
		// What the player can do on his turn
		// 1: Attack
		// 2: Use item
		// 3: Try to run
	}
}
