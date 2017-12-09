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
		counter = 0;
	}

	public void tick() {
		int randRoll = rand.nextInt(5) + 1;
		switch (randRoll) {
		case 1:
			noise = Assets.noiseOne;
		case 2:
			noise = Assets.noiseTwo;
		case 3:
			noise = Assets.noiseThree;
		case 4:
			noise = Assets.noiseFour;
		case 5:
			noise = Assets.noiseFive;
	}
	}

	public void render(Graphics g) {
		g.drawImage(noise, 0, 0, GUImain.WIDTH, GUImain.HEIGHT, null);
	}
	}
