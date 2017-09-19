package entities;

import java.util.Random;

public class Monster {
	Random rand = new Random();
	private int monsterInitiative;
	private int monsterHP;

	public Monster() {
		monsterInitiative = rand.nextInt((20 - 1) + 1) + 1;
		monsterHP = rand.nextInt((20 - 1) + 1) + 1;
	}

	public void setInitiative(int initiative) {
		monsterInitiative = initiative;
	}

	public int getInitiative() {
		return monsterInitiative;
	}

	public void setHealth(int hp) {
		monsterHP = hp;
	}

	public int getHealth() {
		return monsterHP;
	}
}
