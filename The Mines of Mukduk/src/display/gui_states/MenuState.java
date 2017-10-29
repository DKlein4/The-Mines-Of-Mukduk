package display.gui_states;

import java.awt.FontMetrics;
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

	private int xEdgeSpacing, yEdgeSpacing, fontSize;
	private int yButtonSpacing;

	public MenuState(Handler handler) {
		super(handler);

		uiManager = new UIManager(handler);
		handler.getMouseInput().setUIManager(uiManager);

		xEdgeSpacing = GUImain.WIDTH / 15;
		yEdgeSpacing = GUImain.HEIGHT / 12;
		yButtonSpacing = GUImain.HEIGHT / 8;
		fontSize = GUImain.HEIGHT / 12;

		// Play button
		uiManager.addObject(new UIImageButton("PLAY", xEdgeSpacing, yEdgeSpacing, 100, fontSize, Assets.button, new ClickListener() {
					@Override
					public void onClick() {
						GUIstate.setState(handler.getGuiMain().gameState);
					}
				}));
		
		// Tutorial button
		uiManager.addObject(new UIImageButton("TUTORIAL", xEdgeSpacing, yEdgeSpacing + yButtonSpacing, 100, fontSize, Assets.button, new ClickListener() {
			@Override
			public void onClick() {
				GUIstate.setState(handler.getGuiMain().tutorialState);
			}
		}));
		
		// Quit button
		uiManager.addObject(new UIImageButton("QUIT", xEdgeSpacing, yEdgeSpacing + yButtonSpacing * 2, 100, fontSize, Assets.button, new ClickListener() {
			@Override
			public void onClick() {
				System.exit(1);
			}
		}));
	}

	public void tick() {
		uiManager.tick();
	}

	public void render(Graphics g) {
		// Background
		g.drawImage(Assets.menuBackground, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);
		
		uiManager.render(g);
	}
}
