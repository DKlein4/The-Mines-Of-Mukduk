package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gfx.Text;

public class UIImageButton extends UIObject{
	
	private BufferedImage[] images;
	private ClickListener clicker;
	
	private String text;
	
	public UIImageButton(String text, double x, double y,int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		
		this.text = text;
		this.images = images;
		this.clicker = clicker;
	}

	public void tick() {}

	public void render(Graphics g) {
		if(hovering)
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		else
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
		
		renderText(g);
	}

	public void onClick() {
		clicker.onClick();
	}
	
	public void renderText(Graphics g) {
		Font buttonFont = new Font("MonoSpaced", Font.PLAIN, height);
		
		if(hovering)
			Text.drawStringFrom(g, text, (int) x + width/2, (int) y + height/2, true, Color.black, buttonFont);
		else
			Text.drawStringFrom(g, text, (int) x + width/2, (int) y + height/2, true, Color.white, buttonFont);

		FontMetrics fm = g.getFontMetrics(buttonFont);
		setWidth(fm.stringWidth(text));
	}
}
