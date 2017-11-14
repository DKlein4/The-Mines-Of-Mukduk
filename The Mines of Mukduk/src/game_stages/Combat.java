package game_stages;

import java.awt.Graphics;

import display.MessageNotifier;
import display.gui_states.CombatState;
import display.gui_states.GUIstate;
import entities.Monster;
import entities.Player;
import gfx.Assets;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

/**
 * Handles the combat between player and enemies.
 * 
 * @author Brett
 */
public class Combat {
	private Player player;
	private Monster monster;

	private GUIstate combatState;

	private int monsterInit, playerInit;
	private Handler handler;

	private MessageNotifier messenger;

	private UIManager uiManager;
	private UIImageButton attackButton;

	private enum CombatStage {
		playerTurn, monsterTurn, over;
	}

	private CombatStage currentStage;

	public Combat(Handler handler, Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
		this.handler = handler;

		combatState = new CombatState(handler);

		messenger = handler.getGuiMain().getMessageNotifier();

		uiManager = new UIManager(handler);
		handler.getMouseInput().setUIManager(uiManager);

		attackButton = new UIImageButton("ATTACK", 100, 100, 100, 30, Assets.button, new ClickListener() {
			@Override
			public void onClick() {
				if (!messenger.isActive() && currentStage == CombatStage.playerTurn) {
					if (monsterInit > playerInit) {
						if (currentStage != CombatStage.over)
							monsterTurn();
						if (currentStage != CombatStage.over)
							playerTurn();
					} else if (monsterInit <= playerInit) {
						if (currentStage != CombatStage.over)
							playerTurn();
						if (currentStage != CombatStage.over)
							monsterTurn();
					}
					
					if (currentStage != CombatStage.playerTurn)
						currentStage = CombatStage.playerTurn;
				}
			}
		});
		uiManager.addObject(attackButton);

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
		
		currentStage = CombatStage.playerTurn;

		if (monsterInit > playerInit) 
			messenger.showMessage("The monster was quicker!");
		else if (monsterInit <= playerInit)
			messenger.showMessage("You were quicker!");
	}

	public void tick() {
		if (!messenger.isActive())
			if (currentStage != CombatStage.over)
				uiManager.tick();
	}

	public void render(Graphics g) {
		if (!messenger.isActive())
			if (currentStage != CombatStage.over)
				uiManager.render(g);
	}

	public void end() {
		System.out.println("Combat has ended");
		GUIstate.setState(handler.getGuiMain().gameState);
	}

	public void playerTurn() {
		handler.getMouseInput().setUIManager(uiManager);
		currentStage = CombatStage.playerTurn;
		
		
		if (monster.attackCheck(player.attackRoll())) {
			messenger.showMessage("You hit Goby the Goblin!");
			monster.setHealth(monster.getHealth() - 2);
		} else {
			messenger.showMessage("You missed, dumbass!");
		}
		resolve();
	}

	public void monsterTurn() {
		handler.getMouseInput().setUIManager(null);
		currentStage = CombatStage.monsterTurn;
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
		System.out.println("Monster Health: " + monster.getHealth() + "\n");
		
		if (monster.getHealth() <= 0) {
			currentStage = CombatStage.over;
			end();
		}
		if (player.getHealth() <= 0) {
			currentStage = CombatStage.over;
			end();
		}
	}
}
