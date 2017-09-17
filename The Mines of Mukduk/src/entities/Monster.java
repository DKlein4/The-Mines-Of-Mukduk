package entities;
import java.util.Random;

public class Monster {
static Random rand = new Random();
int monsterInitiative;
int monsterHP;

	public int genInitiative() {
		return rand.nextInt((20 - 1) + 1) + 1;
	}
	
	public void setInitiative(int initiative) {
		monsterInitiative = initiative;
	}
	
	public int getInitiative() {
		return monsterInitiative;
	}
	
	public int genHealth() {
		return rand.nextInt((20 - 1) + 1) + 1;
	}
	
	public void setHealth(int hp) {
		monsterHP = hp;
	}
	
	public int getHealth() {
		return monsterHP;
	}
}
