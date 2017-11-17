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

		selected = 0;

		keyInput = handler.getKeyInput();
		keyDown = keyInput.getKeyDown();
		messenger = handler.getGuiMain().getMessageNotifier();

		start();
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
			g.drawImage(Assets.combatButtons, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);
	
			// Selector
			g.drawImage(Assets.pointer, (GUImain.WIDTH * 420 / 1000) + (selected * GUImain.WIDTH * 168 / 1000), GUImain.HEIGHT * 110 / 1000, GUImain.WIDTH * 90 /1000, GUImain.HEIGHT * 26 / 1000, null);
		}
		
		// Monster health bar
		g.setColor(Color.WHITE);
		g.fillRoundRect(GUImain.WIDTH * 680 / 1000, GUImain.HEIGHT * 290 / 1000, (GUImain.WIDTH * 145 / 1000), GUImain.HEIGHT * 30 / 1000,  GUImain.HEIGHT * 10 / 1000,  GUImain.HEIGHT * 20 / 1000);
		g.setColor(Color.RED);
		g.fillRoundRect(GUImain.WIDTH * 680 / 1000, GUImain.HEIGHT * 290 / 1000, (int) ((GUImain.WIDTH * 145 / 1000) * ((double) monster.getHealth() / monster.getMaxHealth())), GUImain.HEIGHT * 30 / 1000,  GUImain.HEIGHT * 10 / 1000,  GUImain.HEIGHT * 20 / 1000);

		// HUD
		g.setColor(Color.BLACK);
		g.drawImage(Assets.combatHUD, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);
		// Health Number
		Text.drawStringFrom(g, player.getHealth() + "", GUImain.WIDTH * 217 / 1000, GUImain.HEIGHT * 52 / 1000, true, Color.DARK_GRAY, new Font("MonoSpaced", Font.BOLD, GUImain.HEIGHT / 24));
		// Armor class
		Text.drawStringFrom(g, player.getArmorClass() + "", GUImain.WIDTH * 81 / 1000, GUImain.HEIGHT / 15, true, Color.DARK_GRAY, new Font("MonoSpaced", Font.BOLD, GUImain.HEIGHT / 30));
		// Player health bar
		g.setColor(Color.RED);
		g.fillRoundRect(GUImain.WIDTH * 109 / 1000, GUImain.HEIGHT * 107 / 1000, (int) ((GUImain.WIDTH * 145 / 1000) * ((double) player.getHealth() / player.getMaxHealth())), GUImain.HEIGHT * 30 / 1000,  GUImain.HEIGHT * 10 / 1000,  GUImain.HEIGHT * 20 / 1000);
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
			if (selected > 0)
				selected--;
			keyDown[3] = false; // Prevents spam of the key
		}

		// D, move the selector to the left
		if (keyDown[2]) {
			if (selected < 2)
				selected++;
			keyDown[2] = false; // Prevents spam of the key
		}

		// Enter, do selected action
		if (keyDown[8]) {
			keyDown[8] = false; // Prevents spam of the key
			System.out.println("F pressed");
			// Attack
			if (selected == 0) {
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
			else if (selected == 1) {
				messenger.showMessage("You used an item!");
				System.out.println("Player Used Item");
			}
			// Flee
			else if (selected == 2) {
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

		if (player.getHealth() <= 0) {
			messenger.showMessage("You have been defeated by Goby the Goblin!");
			currentStage = CombatStage.over;
		} else if (monster.getHealth() <= 0) {
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