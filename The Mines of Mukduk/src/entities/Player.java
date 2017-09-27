package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Main.main;
import user_interface.GUIMain;
import user_interface.Handler;

public class Player extends Entity {
	Random rand = new Random();
	Handler handler;

	public Player(int row, int col, ID id, Handler handler) {
		super(row, col, ID.Player);
		this.handler = handler;
	}

	public void tick() {
		xPos = (col * tileSizeX) + gridOffsetX;
		yPos = (row * tileSizeY) + gridOffsetY;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(xPos, yPos, tileSizeY, tileSizeY);
		System.out.println("(" + row + "," + col + ")");
	}

	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, tileSizeX, tileSizeY);
	}
}
