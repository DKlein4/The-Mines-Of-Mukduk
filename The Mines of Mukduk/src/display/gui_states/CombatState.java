package display.gui_states;

import java.awt.Color;
import java.awt.Graphics;

import display.GUImain;
import main.Handler;

public class CombatState extends GUIstate {

	public CombatState(Handler handler) {
		super(handler);
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, GUImain.WIDTH, GUImain.HEIGHT);
		// System.out.println("Combat is rendering");
	}
}
