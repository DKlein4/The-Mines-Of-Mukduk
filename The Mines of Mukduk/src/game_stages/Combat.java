package game_stages;

import java.awt.Color;
import java.awt.Graphics;

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
	private boolean combatActive;

	private GUIstate combatState;

	private int selected;
	private int monsterInit, playerInit;
	private Handler handler;

	private KeyInput keyInput;
	private boolean[] keyDown;
	private MessageNotifier messenger;
	
	private enum CombatStage {
		playerTurn, monsterTurn, over;
	}

	public Combat(Handler handler, Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
		this.handler = handler;

		combatActive = true;
		combatState = new CombatState(handler);

		selected = 1;
		
		keyInput = handler.getKeyInput();
		keyDown = keyInput.getKeyDown();
		messenger = handler.getGuiMain().getMessageNotifier();

		start();
	}

	public void start() {
		messenger.showMessage("Combat has started!");

		GUIstate.setState(combatState);
		((CombatState) combatState).setCombat(this);

		monsterInit = monster.initiativeRoll();
		playerInit = player.initiativeRoll();

		monster.updateArmorClass();
		player.updateArmorClass();

		if (monsterInit > playerInit) 
			messenger.showMessage("The monster was quicker!");
		else if (monsterInit <= playerInit) 
			messenger.showMessage("You were quicker!");
		
	}

	public void tick() {
		if (monsterInit > playerInit) {
			if (combatActive) 
				monsterTurn();
			if (combatActive)
				playerTurn();
		} else if (monsterInit <= playerInit) {
			if (combatActive)
				playerTurn();
			if (combatActive)
				monsterTurn();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		
		for(int i = 1; i <= 3; i++) {
			g.fillRect(50 * i, 50, 50, 50);
		}
		
		g.setColor(Color.YELLOW);
		
		g.fillRect(50 * selected, 105, 30, 30);
	}

	public void end() {
		
	}

	public void playerTurn() {
		// A, move the selector down
		if (keyDown[3]) {
			if (selected > 1)
				selected--;
			keyDown[3] = false;
		}
		// D, move the selector to the left
		if (keyDown[2]) {
			if (selected < 3)
				selected++;
			keyDown[2] = false;
		}
		// Enter, do action
		if (keyDown[5]) {
			if (monster.attackCheck(player.attackRoll())) {
				messenger.showMessage("You hit Goby the Goblin!");
				monster.setHealth(monster.getHealth() - 2);
			} else {
				messenger.showMessage("You missed, dumbass!");
			}
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
			combatActive = false;

		}
		if (player.getHealth() <= 0) {
			combatActive = false;
		}
	}
}