package display.gui_states;

import java.awt.Graphics;

import display.GUImain;
import gfx.Assets;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

/**
 * This is the state of the GUI when the menu is active.
 * 
 * WARNING All WHO ENTER: The button formatting is so jank don't even try to
 * look at it.
 * 
 * @author Dustin
 */
public class MenuState extends GUIstate {

	private UIManager uiManager;

	private int xEdgeSpacing, yEdgeSpacing, buttonHeight;
	private int yButtonSpacing;

	private UIImageButton playButton, tutorialButton, quitButton;

	public MenuState(Handler handler) {
		super(handler);

		uiManager = new UIManager(handler);

		// Play button
		playButton = new UIImageButton("PLAY", xEdgeSpacing, yEdgeSpacing, 100, buttonHeight, Assets.button, new ClickListener() {
					@Override
					public void onClick() {
						GUIstate.setState(handler.getGuiMain().gameState);
						handler.getMouseInput().setUIManager(null);
					}
				});
		uiManager.addObject(playButton);

		// Tutorial button
		tutorialButton = new UIImageButton("TUTORIAL", xEdgeSpacing, yEdgeSpacing + yButtonSpacing, 100, buttonHeight, Assets.button, new ClickListener() {
					@Override
					public void onClick() {
						GUIstate.setState(handler.getGuiMain().tutorialState);
						handler.getMouseInput().setUIManager(null);
					}
				});
		uiManager.addObject(tutorialButton);

		// Quit button
		quitButton = new UIImageButton("QUIT", xEdgeSpacing, yEdgeSpacing + yButtonSpacing * 2, 100, buttonHeight, Assets.button, new ClickListener() {
					@Override
					public void onClick() {
						System.exit(1);
					}
				});
		uiManager.addObject(quitButton);
	}

	public void tick() {
		handler.getMouseInput().setUIManager(uiManager);
		uiManager.tick();
		
		// Update the spacing variables if the screen has changed size
		xEdgeSpacing = GUImain.WIDTH / 15;
		yEdgeSpacing = GUImain.HEIGHT / 12;
		yButtonSpacing = GUImain.HEIGHT / 8;
		buttonHeight = GUImain.HEIGHT / 12;
		
		// Update the location and size of the buttons if the screen has changed size
		playButton.updatePostition(xEdgeSpacing, yEdgeSpacing, buttonHeight);
		tutorialButton.updatePostition(xEdgeSpacing, yEdgeSpacing + yButtonSpacing, buttonHeight);
		quitButton.updatePostition(xEdgeSpacing, yEdgeSpacing + yButtonSpacing * 2, buttonHeight);
	}

	public void render(Graphics g) {
		// Background
		g.drawImage(Assets.menuBackground, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);

		uiManager.render(g);
	}
}
