package display.gui_states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import input.MouseInput;
import main.Handler;

public class MenuState extends GUIstate {

	private MouseInput mouseInput;

	public MenuState(Handler handler, MouseInput mouseInput) {
		super(handler);
		this.mouseInput = mouseInput;
	}

	public void tick() {
		if (mouseInput.isLeftPressed()) {
			// Play button
			if (mouseInput.mouseOver(325, 225, 250, 70)) {
				GUIstate.setState(handler.getGuiMain().gameState);
			}
			// Tutorial button
			if (mouseInput.mouseOver(315, 325, 270, 70)) {
				GUIstate.setState(handler.getGuiMain().tutorialState);
			}
			// Quit button
			if (mouseInput.mouseOver(325, 425, 250, 70)) {
				System.exit(1);
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 100));
		g.drawString("Menu", 320, 125);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));

		// Play button
		g.drawRect(325, 225, 250, 70);
		g.drawString("PLAY", 385, 275);

		// Tutorial button
		g.drawRect(315, 325, 270, 70);
		g.drawString("TUTORIAL", 325, 375);

		// Quit button
		g.drawRect(325, 425, 250, 70);
		g.drawString("QUIT", 385, 475);
	}
}
