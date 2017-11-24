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

	private JFrame frame;
	
	private int defaultWidth;

	private static final long serialVersionUID = 501298079830563846L;

	public Window(int width, int height, String title, GUImain guiMain) {
		defaultWidth = width;
		
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(guiMain);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);
		frame.setVisible(true);
		guiMain.start();
	}

	// GETTERS AND SETTERS
	
	public int getDefaultWidth() {
		return defaultWidth;
	}

	public JFrame getFrame() {
		return frame;
	}

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
