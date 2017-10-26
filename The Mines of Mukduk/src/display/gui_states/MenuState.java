package display.gui_states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import display.GUImain;
import gfx.Assets;
import input.MouseInput;
import main.Handler;

/**
 * This is the state of the GUI when the menu is active.
 * 
 * @author Dustin
 */
public class MenuState extends GUIstate {

	private MouseInput mouseInput;

	public MenuState(Handler handler, MouseInput mouseInput) {
		super(handler);
		this.mouseInput = mouseInput;
	}

	public void tick() {
		if (mouseInput.isLeftPressed()) {
			// Play button
			if (mouseInput.mouseOver(60, 56, 130, 50)) {
				GUIstate.setState(handler.getGuiMain().gameState);
			}
			// Tutorial button
			if (mouseInput.mouseOver(60, 132, 250, 50)) {
				GUIstate.setState(handler.getGuiMain().tutorialState);
			}
			// Quit button
			if (mouseInput.mouseOver(60, 208, 120, 50)) {
				System.exit(1);
			}
		}
	}

	public void render(Graphics g) {
		// Background
		g.drawImage(Assets.menuBackground, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);
		
		g.setColor(Color.WHITE);

		g.setFont(new Font("ComicSans", Font.PLAIN, 50));

		// Play button
		//g.drawRect(60, 56, 130, 50);
		g.drawString("PLAY", 60, 100);

		// Tutorial button
		//g.drawRect(60, 132, 250, 50);
		g.drawString("TUTORIAL", 60, 175);

		// Quit button
		//g.drawRect(60, 208, 120, 50);
		g.drawString("QUIT", 60, 250);
	}
}
