package game_stages;

import display.gui_states.CombatState;
import display.gui_states.GUIstate;
import entities.Monster;
import entities.Player;
import main.Handler;

/**
 * Handles the combat between player and enemies.
 * 
 * @author Brett
 */
public class Combat {
	private Player player;
	private Monster monster;
	private Handler handler;
	private boolean combat = false;

	private GUIstate combatState;
	
	public Combat(Handler handler, Player player, Monster monster) {
		combat = true;
		this.player = player;
		this.monster = monster;
		this.handler = handler;
		

		start();
	}
	
	public void start() {
		int monsterInit = monster.initiativeRoll();
		int playerInit = player.initiativeRoll();
		
		if (monsterInit > playerInit) {
			while (combat) {
				mTurn();
			}
		}
		
		else if (monsterInit <= playerInit) {
			while (combat) {
				pTurn();
			}
		}

	}
	
	public void pTurn() {
		playerTurn();
		monsterTurn();
	}
	
	public void mTurn() {
		monsterTurn();
		playerTurn();
	}
	
	public void playerTurn() {
		resolve();
	}
	
	public void monsterTurn() {
		resolve();
	}
	
	public void resolve() {
		if (monster.getHealth() <= 0) {
			combat = false;
		}
		if (player.getHealth() <= 0) {
			combat = false;
		}
		combat = false;
	}
}
