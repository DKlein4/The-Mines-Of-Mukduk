package display.gui_states;

import java.awt.Color;
import java.awt.Graphics;

import display.GUImain;
import display.MessageNotifier;
import main.Handler;
import world_gen.World;

public class CombatState extends GUIstate {

	private MessageNotifier messenger;
	
	public CombatState(Handler handler) {
		super(handler);
		messenger = handler.getGuiMain().getMessageNotifier();

	}
	
	public void tick() {
		messenger.tick();
	}

	public void render(Graphics g) {
		//g.setColor(Color.PINK);
		//g.fillRect(0, 0, GUImain.WIDTH, GUImain.HEIGHT);
		System.out.println("Combat is rendering");
		messenger.render(g);
	}
}
