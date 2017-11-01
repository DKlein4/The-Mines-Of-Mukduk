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
	private boolean combat;

	private GUIstate combatState;
	
	private int monsterInit, playerInit;
	private Handler handler;

	public Combat(Handler handler, Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
		this.handler = handler;

		combat = true;
		combatState = new CombatState(handler);

		start();
	}

	public void start() {
		GUIstate.setState(combatState);
		
		monsterInit = monster.initiativeRoll();
		playerInit = player.initiativeRoll();

		run();
	}
	
	public void run() {
		if (monsterInit > playerInit) {
			while (combat) {
				monsterTurn();
				playerTurn();
			}
		}
		else if (monsterInit <= playerInit) {
			while (combat) {
				playerTurn();
				monsterTurn();
			}
		}
		
		end();
	}
	
	public void end() {
		System.out.println("Combat has ended");
		// GUIstate.setState(handler.getGuiMain().gameState);
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
		
		// Temporarily end combat immediately
		combat = false;
	}
}
