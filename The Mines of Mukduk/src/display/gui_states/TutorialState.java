package display.gui_states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import display.GUImain;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

/**
 * This is the state of the GUI when the tutorial is active.
 * 
 * @author Dustin
 */
public class TutorialState extends GUIstate {
	
	private UIManager uiManager;
	
	private int xEdgeSpacing, yEdgeSpacing, buttonHeight;
	private UIImageButton backButton;

	public TutorialState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager(handler);
		handler.getMouseInput().setUIManager(uiManager);
		
		xEdgeSpacing = GUImain.WIDTH / 20;
		yEdgeSpacing = GUImain.HEIGHT / 20;
		buttonHeight = GUImain.HEIGHT / 20;
		
		// Back button
		backButton = new UIImageButton("BACK", xEdgeSpacing, yEdgeSpacing, 100, buttonHeight, Assets.button, new ClickListener() {
			@Override
			public void onClick() {
				GUIstate.setState(handler.getGuiMain().menuState);
			}
		});
		uiManager.addObject(backButton);
	}

	public void tick() {
		uiManager.tick();
		
		// Update the spacing variables if the screen has changed size
		xEdgeSpacing = GUImain.WIDTH / 20;
		yEdgeSpacing = GUImain.HEIGHT / 20;
		buttonHeight = GUImain.HEIGHT / 20;
		
		// Update the location and size of the buttons if the screen has changed size
		backButton.updatePostition(xEdgeSpacing, yEdgeSpacing, buttonHeight);
	}

	public void render(Graphics g) {
		handler.getMouseInput().setUIManager(uiManager);
		
		// Make a black background
		g.setColor(Color.black);
		g.fillRect(0, 0, GUImain.WIDTH, GUImain.HEIGHT);
		
		// Display the text on screen
		Font font = new Font("MonoSpaced", Font.PLAIN, GUImain.HEIGHT / 14);
		Text.drawStringFrom(g, "Use WASD to move around and crap.", GUImain.WIDTH / 2, GUImain.HEIGHT / 3, true, Color.white, font);
		
		uiManager.render(g);
	}
}
