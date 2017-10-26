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
 * WARNING All WHO ENTER: The button formatting is so jank don't even try to
 * look at it.
 * 
 * @author Dustin
 */
public class MenuState extends GUIstate {

	private MouseInput mouseInput;

	private int xSpacing;
	private int ySpacing;
	private int fontSize;

	public MenuState(Handler handler, MouseInput mouseInput) {
		super(handler);
		this.mouseInput = mouseInput;

	}

	public void tick() {
		xSpacing = GUImain.WIDTH / 15;
		ySpacing = GUImain.HEIGHT / 10;
		fontSize = GUImain.HEIGHT / 14;

		if (mouseInput.isLeftPressed()) {
			// Play button
			if (mouseInput.mouseOver(xSpacing, (2 * ySpacing) - fontSize + (ySpacing / 6), (int) (fontSize * 2.7), fontSize - (ySpacing / 6))) {
				GUIstate.setState(handler.getGuiMain().gameState);
			}
			// Tutorial button
			if (mouseInput.mouseOver(xSpacing, (3 * ySpacing) - fontSize + (ySpacing / 6), (int) (fontSize * 4.9), fontSize - (ySpacing / 6))) {
				GUIstate.setState(handler.getGuiMain().tutorialState);
			}
			// Quit button
			if (mouseInput.mouseOver(xSpacing, (4 * ySpacing) - fontSize + (ySpacing/6), (int) (fontSize * 2.4), fontSize - (ySpacing/6))) {
				System.exit(1);
			}
		}
	}

	public void render(Graphics g) {
		// Background
		g.drawImage(Assets.menuBackground, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);

		xSpacing = GUImain.WIDTH / 15;
		ySpacing = GUImain.HEIGHT / 10;
		fontSize = GUImain.HEIGHT / 14;

		g.setColor(Color.WHITE);
		g.setFont(new Font("ComicSans", Font.PLAIN, fontSize));

		// Play button
		g.drawRect(xSpacing, (2 * ySpacing) - fontSize + (ySpacing / 6), (int) (fontSize * 2.7), fontSize - (ySpacing / 6));
		g.drawString("PLAY", xSpacing, 2 * ySpacing);

		// Tutorial button
		g.drawRect(xSpacing, (3 * ySpacing) - fontSize + (ySpacing / 6), (int) (fontSize * 4.9), fontSize - (ySpacing / 6));
		g.drawString("TUTORIAL", xSpacing, 3 * ySpacing);

		// Quit button
		g.drawRect(xSpacing, (4 * ySpacing) - fontSize + (ySpacing/6), (int) (fontSize * 2.4), fontSize - (ySpacing/6));
		g.drawString("QUIT", xSpacing, 4 * ySpacing);
	}
}
