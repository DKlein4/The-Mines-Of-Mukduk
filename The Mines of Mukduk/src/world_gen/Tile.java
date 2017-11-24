package world_gen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;

/**
 * One space on the map that has different attributes.
 * 
 * @author Dustin
 */
public class Tile {

	public static final int TILEWIDTH = Assets.height;
	public static final int TILEHEIGHT = Assets.width;

	// Different states a tile can be
	private boolean isWall, isFloor, isLadder, isUnexplored, isTreasure, isMonster, isSpawn;

	public Tile() {
		clearTile();
	}

	public void render(Graphics g, int x, int y) {
		//TILEWIDTH = GUImain.
		
		if (isWall()) {
			g.drawImage(Assets.wall, x, y, TILEWIDTH, TILEHEIGHT, null);
		} else if (isFloor()) {
			g.drawImage(Assets.dirt, x, y, TILEWIDTH, TILEHEIGHT, null);
		} else if (isSpawn()) {
			g.drawImage(Assets.dirt, x, y, TILEWIDTH, TILEHEIGHT, null);
		} else if (isLadder()) {
			g.drawImage(Assets.dirt, x, y, TILEWIDTH, TILEHEIGHT, null);
			Text.drawStringFrom(g, "▼", x + (TILEWIDTH / 2), y + (TILEHEIGHT / 2), true, Color.green, new Font("TimesRoman", Font.PLAIN, TILEWIDTH / 2));
		} else if (isTreasure()) {
			g.drawImage(Assets.dirt, x, y, TILEWIDTH, TILEHEIGHT, null);
			Text.drawStringFrom(g, "₧", x + (TILEWIDTH / 2), y + (TILEHEIGHT / 2), true, Color.green, new Font("TimesRoman", Font.PLAIN, TILEWIDTH / 2));
		} else if (isMonster()) {
			g.drawImage(Assets.dirt, x, y, TILEWIDTH, TILEHEIGHT, null);
		} else
			g.drawImage(Assets.wall, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	private void clearTile() {
		isWall = isFloor = isLadder = isUnexplored = isTreasure = isMonster = isSpawn = false;
	}

	// GETTERS AND SETTERS

	public boolean isWall() {
		return isWall;
	}

	public boolean isSpawn() {
		return isSpawn;
	}

	public void setSpawn(boolean isSpawn) {
		clearTile();
		this.isSpawn = isSpawn;
	}

	public void setWall(boolean isWall) {
		clearTile();
		this.isWall = isWall;
	}

	public boolean isFloor() {
		return isFloor;
	}

	public void setFloor(boolean isFloor) {
		clearTile();
		this.isFloor = isFloor;
	}

	public boolean isLadder() {
		return isLadder;
	}

	public void setLadder(boolean isLadder) {
		clearTile();
		this.isLadder = isLadder;
	}

	public boolean isUnexplored() {
		return isUnexplored;
	}

	public void setUnexplored(boolean isUnexplored) {
		clearTile();
		this.isUnexplored = isUnexplored;
	}

	public boolean isTreasure() {
		return isTreasure;
	}

	public void setTreasure(boolean isTreasure) {
		clearTile();
		this.isTreasure = isTreasure;
	}

	public boolean isMonster() {
		return isMonster;
	}

	public void setMonster(boolean isMonster) {
		clearTile();
		this.isMonster = isMonster;
	}

	public boolean isSolid() {
		return isWall();
	}
}
