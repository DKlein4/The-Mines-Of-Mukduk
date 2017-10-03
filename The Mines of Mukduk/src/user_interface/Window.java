package user_interface;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * @author Dustin; Creates a new JFrame with the correct parameters
 */
public class Window extends Canvas {
	
	JFrame frame;

	private static final long serialVersionUID = 501298079830563846L;

	public Window(int width, int height, String title, GUIMain guiMain) {
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(guiMain);
		frame.setVisible(true);
		guiMain.start();
	}
	
	public void setTitle(String title){
		frame.setTitle(title);
	}
}
