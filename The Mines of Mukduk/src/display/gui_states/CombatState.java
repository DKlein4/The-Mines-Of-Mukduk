package display.gui_states;

import java.awt.Color;
import java.awt.Graphics;

import display.GUImain;
import display.MessageNotifier;
import game_stages.Combat;
import main.Handler;
import world_gen.World;

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
		g.setColor(Color.black);
		g.fillRect(0, 0, GUImain.WIDTH, GUImain.HEIGHT);
		//System.out.println("Combat is rendering");
		
		combat.render(g);
		messenger.render(g);
	}
	
	public void setCombat(Combat combat) {
		this.combat = combat;
	}
}
