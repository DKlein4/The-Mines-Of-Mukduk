package game_stages;

import display.MessageNotifier;
import display.gui_states.CombatState;
import display.gui_states.GUIstate;
import entities.Monster;
import entities.Player;
import input.KeyInput;
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

	private KeyInput keyInput;
	private MessageNotifier messenger;

	public Combat(Handler handler, Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
		this.handler = handler;

		combat = true;
		combatState = new CombatState(handler);

		keyInput = handler.getKeyInput();
		messenger = handler.getGuiMain().getMessageNotifier();

		start();
	}

	public void start() {
		messenger.showMessage("Combat has started!");

		// GUIstate.setState(combatState);

		monsterInit = monster.initiativeRoll();
		playerInit = player.initiativeRoll();

		monster.updateArmorClass();
		player.updateArmorClass();

		run();
	}

	public void run() {
		if (monsterInit > playerInit) {
			messenger.showMessage("The monster was quicker!");
			while (combat) {
				monsterTurn();
				playerTurn();
			}
		} else if (monsterInit <= playerInit) {
			messenger.showMessage("You were quicker!");
			while (combat) {
				playerTurn();
				monsterTurn();
			}
		}
	}

	public void end() {
	}

	public void playerTurn() {
		if (monster.attackCheck(player.attackRoll())) {
			messenger.showMessage("You hit Goby the Goblin!");
			monster.setHealth(monster.getHealth() - 2);
		} else {
			messenger.showMessage("You missed, dumbass!");
		}
		resolve();
	}

	public void monsterTurn() {
		if (player.attackCheck(monster.attackRoll())) {
			messenger.showMessage("The monster hits you!");
			player.setHealth(player.getHealth() - 2);
		} else {
			messenger.showMessage("The monster misses!");
		}
		resolve();
	}

	public void resolve() {
		System.out.println("Player Health: " + player.getHealth());
		System.out.println("Monster Health: " + monster.getHealth());
		if (monster.getHealth() <= 0) {
			combat = false;

		}
		if (player.getHealth() <= 0) {
			combat = false;
		}
	}
}