package game_stages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import display.GUImain;
import display.MessageNotifier;
import display.gui_states.CombatState;
import display.gui_states.GUIstate;
import entities.Monster;
import entities.Player;
import gfx.Assets;
import gfx.Text;
import input.KeyInput;
import main.Handler;

/**
 * Handles the combat between player and enemies.
 * 
 * @author Brett and Dustin
 */
public class Combat {
	private Player player;
	private Monster monster;

	private GUIstate combatState;

	private int selected;
	private int monsterInit, playerInit;

	private KeyInput keyInput;
	private boolean[] keyDown;
	private MessageNotifier messenger;
	private Handler handler;

	private enum CombatStage {
		playerTurn, monsterTurn, over;
	}

	private CombatStage currentStage;

	public Combat(Handler handler, Player player, Monster monster) {
		this.handler = handler;
		this.player = player;
		this.monster = monster;

		combatState = new CombatState(handler);

		selected = 1;

		keyInput = handler.getKeyInput();
		keyDown = keyInput.getKeyDown();
		messenger = handler.getGuiMain().getMessageNotifier();

		start();
	}

	// Initialization crap
	public void start() {
		messenger.showMessage("Combat has started!");

		GUIstate.setState(combatState);
		((CombatState) combatState).setCombat(this);

		monsterInit = monster.initiativeRoll();
		playerInit = player.initiativeRoll();

		monster.updateArmorClass();
		player.updateArmorClass();

		if (monsterInit > playerInit) {
			messenger.showMessage("The monster was quicker!");
			currentStage = CombatStage.monsterTurn;
		} else if (monsterInit <= playerInit) {
			messenger.showMessage("You were quicker!");
			currentStage = CombatStage.playerTurn;
		}
	}

	public void tick() {
		if (!messenger.isActive())
			playRound();

		if (currentStage == CombatStage.over)
			end();
	}

	public void render(Graphics g) {
		if (currentStage == CombatStage.playerTurn && !messenger.isActive()) {
			// Action choices
			g.setColor(Color.red);
			for (int i = 1; i <= 3; i++) {
				g.fillRect((GUImain.WIDTH / 3) + (GUImain.WIDTH/8) * i - (GUImain.WIDTH / 32), GUImain.WIDTH / 16, GUImain.WIDTH / 16, GUImain.WIDTH / 16);
			}
	
			// Selector
			g.setColor(Color.YELLOW);
			g.fillRoundRect((GUImain.WIDTH / 3) + (GUImain.WIDTH/8) * selected - (GUImain.WIDTH / 60), GUImain.WIDTH / 8, GUImain.WIDTH / 30, GUImain.WIDTH / 30, 25, 25);
		}
			
		// HUD
		g.drawImage(Assets.combatHUD, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);
		// Health
		Text.drawStringFrom(g, player.getHealth() + "", GUImain.WIDTH * 217 / 1000, GUImain.HEIGHT * 52 / 1000, true, Color.DARK_GRAY, new Font("MonoSpaced", Font.BOLD, GUImain.HEIGHT / 24));
		// Armor class
		Text.drawStringFrom(g, player.getArmorClass() + "", GUImain.WIDTH * 81 / 1000, GUImain.HEIGHT / 15, true, Color.DARK_GRAY, new Font("MonoSpaced", Font.BOLD, GUImain.HEIGHT / 30));
	}
	
	// Plays one monster turn and one player turn
	public void playRound() {
		if (currentStage != CombatStage.over) {
			if (monsterInit > playerInit) {
				if (currentStage == CombatStage.monsterTurn)
					monsterTurn();
				if (currentStage == CombatStage.playerTurn)
					playerTurn();
			} else if (monsterInit <= playerInit) {
				if (currentStage == CombatStage.playerTurn)
					playerTurn();
				if (currentStage == CombatStage.monsterTurn)
					monsterTurn();
			}
		}
	}

	public void playerTurn() {
		if (messenger.isActive())
			return;

		// A, move the selector down
		if (keyDown[3]) {
			if (selected > 1)
				selected--;
			keyDown[3] = false; // Prevents spam of the key
		}

		// D, move the selector to the left
		if (keyDown[2]) {
			if (selected < 3)
				selected++;
			keyDown[2] = false; // Prevents spam of the key
		}

		// Enter, do selected action
		if (keyDown[8]) {
			keyDown[8] = false; // Prevents spam of the key
			System.out.println();
			// Attack
			if (selected == 1) {
				// Player rolling on whether its attack hit or not
				if (monster.attackCheck(player.attackRoll())) {
					messenger.showMessage("You hit Goby the Goblin!");
					monster.setHealth(monster.getHealth() - 3);
				} else {
					messenger.showMessage("You missed, dumbass!");
				}

				System.out.println("Player attacked");
			}
			// Use item
			else if (selected == 2) {
				System.out.println("Player Used Item");
			}
			// Flee
			else if (selected == 3) {
				System.out.println("Player Fled");
				messenger.showMessage("You pussed out!");
				currentStage = CombatStage.over;
			}

			resolve();
			// Player turn is over so change it to monster turn
			if (currentStage != CombatStage.over)
				currentStage = CombatStage.monsterTurn;
		}
	}

	public void monsterTurn() {
		// Monster rolling on whether its attack hit or not
		if (player.attackCheck(monster.attackRoll())) {
			messenger.showMessage("The monster hits you!");
			player.setHealth(player.getHealth() - 2);
		} else {
			messenger.showMessage("The monster misses!");
		}

		resolve();
		// Monster turn has ended so switch to player turn
		if (currentStage != CombatStage.over)
			currentStage = CombatStage.playerTurn;
	}

	// Checks to see if combat should finish
	public void resolve() {
		System.out.println(currentStage);
		System.out.println("Player Health: " + player.getHealth());
		System.out.println("Monster Health: " + monster.getHealth());

		if (monster.getHealth() <= 0 || player.getHealth() <= 0) {
			messenger.showMessage("You defeated Goby the Goblin!");
			currentStage = CombatStage.over;
		}
	}

	// Ran when combat is over
	public void end() {
		// Wait until all messages are gone then return to map
		if (!messenger.isActive())
			GUIstate.setState(handler.getGuiMain().gameState);
	}
}