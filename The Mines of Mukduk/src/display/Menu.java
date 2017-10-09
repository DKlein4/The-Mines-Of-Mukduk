package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Handler;

public class Menu extends MouseAdapter{
	
	private GUImain guiMain;

	public Menu(Handler handler){
		this.guiMain = handler.getGuiMain();
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(mouseOver(mx, my, 325, 225, 250, 70)){
			guiMain.guiState = GUIstate.Game;
		}		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width)
			if(my > y && my < y + height)
				return true;
			else
				return false;
		else 
			return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 100));
		g.drawString("Menu", 320, 125);
		
		// Play button
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g.drawRect(325, 225, 250, 70);
		g.drawString("PLAY", 385, 275);
		
	}
}
