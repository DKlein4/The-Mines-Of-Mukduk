package display.gui_states;

import java.awt.Color;
import java.awt.Graphics;

import display.GUImain;
import display.MessageNotifier;
import game_stages.Combat;
import gfx.Assets;
import main.Handler;

public class CombatState extends GUIstate {

	private MessageNotifier messenger;
	private Combat combat;
	
	public CombatState(Handler handler) {
		super(handler);
		messenger = handler.getGuiMain().getMessageNotifier();
		
	}
	
	public void tick() {
		combat.tick();
		messenger.tick();
	}

	public void render(Graphics g) {
		// Background
		g.drawImage(Assets.combatBackground, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);
		// Player figure
		g.drawImage(Assets.player, GUImain.WIDTH / 4 - ((GUImain.WIDTH / 3) / 2), GUImain.HEIGHT / 6, GUImain.WIDTH / 3, GUImain.HEIGHT * 5 / 8, null);
		// Monster figure
		g.drawImage(Assets.monster, GUImain.WIDTH * 3 / 4 - ((GUImain.WIDTH / 3) / 2), GUImain.HEIGHT / 6, GUImain.WIDTH / 3, GUImain.HEIGHT * 5 / 8, null);

		
		combat.render(g);
		messenger.render(g);
	}
	
	public void setCombat(Combat combat) {
		this.combat = combat;
	}
}
