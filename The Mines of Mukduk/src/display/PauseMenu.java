package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import display.gui_states.GUIstate;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

public class PauseMenu {

	private Handler handler;

	private boolean[] keyDown;
	private static boolean active;

	// Measurements
	private int x, y, width, height;

	private int buttonX;
	private int buttonY;
	private int buttonHeight;
	
	// Button crap
	private UIManager uiManager;
	private UIImageButton exitButton;

	public PauseMenu(Handler handler) {
		this.handler = handler;

		active = false;
		keyDown = handler.getKeyInput().getKeyDown();

		uiManager = new UIManager(handler);

		// Play button
		exitButton = new UIImageButton("EXIT TO MENU", buttonX, buttonY, 100, buttonHeight, Assets.button,
				new ClickListener() {
					@Override
					public void onClick() {
						toggleActive();
						GUIstate.setState(handler.getGuiMain().menuState);
						handler.getMouseInput().setUIManager(null);
					}
				});
		uiManager.addObject(exitButton);
	}

	public void tick() {
		// Escape toggles the menu
		if (keyDown[7]) {
			if (handler.getWorld().getInventory().isActive())
				return;

			keyDown[7] = false;
			toggleActive();
		}

		if (active) {
			x = GUImain.WIDTH / 3;
			y = GUImain.HEIGHT / 12;
			width = GUImain.WIDTH / 3;
			height = GUImain.HEIGHT * 9 / 12;

			buttonX = x + (width / 8);
			buttonY = y + (height / 4);
			buttonHeight = height * 85 / 1000;
			
			handler.getMouseInput().setUIManager(uiManager);
			uiManager.tick();
			exitButton.updatePostition(buttonX, buttonY, buttonHeight);
		}
	}

	public void render(Graphics g) {
		if (active) {
			// Background
			g.setColor(Color.BLACK);
			g.fillRect(x, y, width, height);

			Text.drawStringFrom(g, "PAUSED", x + (width / 2), y + GUImain.HEIGHT / 20, true, Color.white, new Font("MonoSpaced", Font.BOLD, GUImain.HEIGHT / 16));
			
			uiManager.render(g);
		}
	}

	public boolean isActive() {
		return active;
	}

	public void toggleActive() {
		active = !active;
		handler.getMouseInput().setUIManager(null);
	}
}
