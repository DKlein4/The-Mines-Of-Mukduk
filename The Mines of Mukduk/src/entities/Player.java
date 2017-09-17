package entities;
import java.util.Random;

public class Player {
static Random rand = new Random();
int playerInitiative;
int playerHP;

	public static int genInitiative() {
		return rand.nextInt((20 - 1) + 1) + 1;
	}

	public int genHealth() {
		return rand.nextInt((20 - 1) + 1) + 1;
	}

}
