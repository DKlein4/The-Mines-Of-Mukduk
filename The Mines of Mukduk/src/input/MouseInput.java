package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ui.UIManager;

/**
 * This class handles the mouse input.
 * 
 * @author Dustin
 */
public class MouseInput implements MouseListener, MouseMotionListener {

	private UIManager uiManager;

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
		
		if(uiManager != null)
			uiManager.onMouseRelease(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if (uiManager != null)
			uiManager.onMouseMove(e);
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

	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

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
