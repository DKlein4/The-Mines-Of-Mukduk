package entities;

import java.util.Random;

public class Player {
	Random rand = new Random();
	private int playerInitiative;
	private int playerHP;

	public Player() {
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

}
