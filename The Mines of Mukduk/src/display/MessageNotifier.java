package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import gfx.Assets;
import gfx.Text;
import input.KeyInput;
import main.Handler;

public class MessageNotifier {

	private boolean[] keyDown;

	private static boolean active;

	private static Queue<String> messageList;

	public MessageNotifier(Handler handler, KeyInput keyInput) {
		keyDown = keyInput.getKeyDown();
		messageList = new LinkedList<String>();
	}

	public void tick() {
		if (!messageList.isEmpty()) {
			// Exit if enter, space, or escape is pressed
			if (keyDown[5] || keyDown[6] || keyDown[7]) {
				messageList.remove();
				keyDown[5] = keyDown[6] = keyDown[7] = false;
			}
		} else {
			active = false;
		}
	}

	public void render(Graphics g) {
		if (!messageList.isEmpty()) {
			// Draw the background of the notification
			int x = (GUImain.WIDTH / 15) - (GUImain.WIDTH / 90);
			int y = GUImain.HEIGHT * 5 / 7;
			int width = GUImain.WIDTH * 13 / 15;
			int height = GUImain.HEIGHT / 5;
			g.drawImage(Assets.alert, x, y, width, height, null);

			// Display the message
			Text.drawStringFrom(g, messageList.peek(), x + width / 2, y + height / 3, true, Color.WHITE,
					new Font("MonoSpaced", Font.BOLD, height / 4));

			// Display the tip
			Text.drawStringFrom(g, "Press ENTER or SPACEBAR to continue", x + width / 2, y + height * 5 / 6, true,
					Color.WHITE, new Font("MonoSpaced", Font.PLAIN, height / 6));
		}
	}

	public void showMessage(String message) {
		messageList.add(message);
		active = true;
	}

	// GETTERS AND SETTERS

	public void toggleActive() {
		active = !active;
	}

	public boolean isActive() {
		return active;
	}
}
