package display.gui_states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import input.MouseInput;
import main.Handler;

/**
 * This is the state of the GUI when the tutorial is active.
 * 
 * @author Dustin
 */
public class TutorialState extends GUIstate {

	private MouseInput mouseInput;

	public TutorialState(Handler handler, MouseInput mouseInput) {
		super(handler);
		this.mouseInput = mouseInput;
	}

	public void tick() {
		if (mouseInput.isLeftPressed()) {
			// Back button
			if (mouseInput.mouseOver(325, 500, 250, 70)) {
				GUIstate.setState(handler.getGuiMain().menuState);
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("Use WASD to move around and crap.", 240, 125);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		
		// Back button
		g.drawRect(325, 500, 250, 70);
		g.drawString("BACK", 385, 550);
	}
}
