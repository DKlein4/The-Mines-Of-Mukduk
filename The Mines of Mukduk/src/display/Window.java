package display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Creates a new JFrame.
 * 
 * @author Dustin
 */
public class Window extends Canvas {

	JFrame frame;

	private static final long serialVersionUID = 501298079830563846L;

	public Window(int width, int height, String title, GUImain guiMain) {
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.add(guiMain);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);
		frame.setVisible(true);
		guiMain.start();
	}

	// GETTERS AND SETTERS

	public void setTitle(String title) {
		frame.setTitle(title);
	}
	
	public int getWidth() {
		return frame.getWidth();
	}
	
	public int getHeight() {
		return frame.getHeight();
	}
}
