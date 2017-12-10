package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import gfx.Assets;

public class Noise {
	private Random rand;
	private int counter;
	private BufferedImage noise;

	public Noise() {
		rand = new Random();
		counter = 10;
	}

	public void tick() {
		if (counter == 10) {
			int randRoll = rand.nextInt(3);
			noise = Assets.noise[randRoll];
			counter = 0;
		}
		counter++;
	}

	public void render(Graphics g) {
		g.drawImage(noise, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);
	}
}
