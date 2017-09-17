package game_stages;
import entities.Monster;
import entities.Player;

public class Combat {
	public static void combatRound() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~Battle has started!~");
		System.out.println("~Rolling Initiative!~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		
		//Generates new enemy object
		Monster monster = new Monster();
		monster.genHealth();
		monster.genInitiative();
		
		//Generates player stats
		int playerInitiative = Player.genInitiative();
		
		boolean notDead = true;
		while (notDead == true) {
			//Player goes first
			if (checkInitiative(monster.getInitiative(), playerInitiative) == true){
				playerTurn();
				monsterTurn();
			}
			//Monster goes first
			else if (checkInitiative(monster.getInitiative(), playerInitiative) == false){
				monsterTurn();
				playerTurn();
			}
		}
	}
	
	public static boolean checkInitiative(int monsterInit, int playerInit) {
		//Rolls initiative out of 20, highest goes first.
		if (monsterInit > playerInit) {
			return false;
		}
		else if (monsterInit < playerInit) {
			return true;
		}
		//In case of tie, player goes first
		else {
			return true;
		}
	}

	public static void monsterTurn() {
		//What the monster does on it's turn
	}
	
	public static void playerTurn() {
		//What the player can do on his turn
		//1: Attack
		//2: Use item
		//3: Try to run
	}
}
