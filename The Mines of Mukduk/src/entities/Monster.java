package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import user_interface.Handler;
import world_gen.Map;

public class Monster extends Entity {
	Random rand = new Random();
	private int monsterInitiative;
	private int monsterHP;

	public Monster(int row, int col, ID id, Handler handler, Map map) {
		super(0, 0, ID.Monster, map);
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
