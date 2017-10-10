package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import input.MouseInput;
import main.Handler;

public class Menu{
	
	private GUImain guiMain;
	private MouseInput mouseInput;

	public Menu(Handler handler, MouseInput mouseInput){
		this.guiMain = handler.getGuiMain();
		this.mouseInput = mouseInput;
	}
	
	public void tick(){
		if(mouseInput.isLeftPressed()){
			// Play button
			if(mouseInput.mouseOver(325, 225, 250, 70)){
				guiMain.guiState = GUIstate.Game;
			}
		}
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
