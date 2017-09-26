package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends Entity {
	Random rand = new Random();
	private int playerInitiative;
	private int playerHP;

	public Player() {
		super(0, 0, ID.Player);
		playerInitiative = rand.nextInt((20 - 1) + 1) + 1;
		playerHP = rand.nextInt((20 - 1) + 1) + 1;
	}

	public int getPlayerInitiative() {
		return playerInitiative;
	}

	public void setPlayerInitiative(int playerInitiative) {
		this.playerInitiative = playerInitiative;
	}

	public int getPlayerHP() {
		return playerHP;
	}

	public void setPlayerHP(int playerHP) {
		this.playerHP = playerHP;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
