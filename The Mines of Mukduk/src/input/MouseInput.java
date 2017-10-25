package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class handles the mouse input.
 * 
 * @author Dustin
 */
public class MouseInput implements MouseListener, MouseMotionListener {

	private boolean leftPressed;
	private int mouseX, mouseY;

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
			mouseX = e.getX();
			mouseY = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean mouseOver(int x, int y, int width, int height) {
		if (mouseX > x && mouseX < x + width)
			if (mouseY > y && mouseY < y + height)
				return true;
			else
				return false;
		else
			return false;
	}

	// GETTERS AND SETTERS

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
}
