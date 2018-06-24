package world_gen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import display.GUImain;
import gfx.Assets;
import gfx.Text;

/**
 * One space on the map that has different attributes.
 *
 * @author Dustin
 */
public class Tile {

    public static int TILE_WIDTH;
    public static int TILE_HEIGHT;

    // Different states a tile can be
    private boolean isWall, isFloor, isLadder, isUnexplored, isTreasure, isMonster, isSpawn;

    public Tile() {
        clearTile();

        TILE_WIDTH = GUImain.WIDTH / 11;
        TILE_HEIGHT = TILE_WIDTH;
    }

    public void tick() {
        TILE_WIDTH = GUImain.WIDTH / 11;
        TILE_HEIGHT = TILE_WIDTH;
    }

    public void render(Graphics g, int x, int y) {
        if (isWall()) {
            g.drawImage(Assets.wall, x, y, TILE_WIDTH, TILE_HEIGHT, null);
        } else if (isFloor()) {
            g.drawImage(Assets.dirt, x, y, TILE_WIDTH, TILE_HEIGHT, null);
        } else if (isSpawn()) {
            g.drawImage(Assets.dirt, x, y, TILE_WIDTH, TILE_HEIGHT, null);
        } else if (isLadder()) {
            g.drawImage(Assets.dirt, x, y, TILE_WIDTH, TILE_HEIGHT, null);
            Text.drawStringFrom(g, "▼", x + (TILE_WIDTH / 2), y + (TILE_HEIGHT / 2), true, Color.green, new Font("TimesRoman", Font.PLAIN, TILE_WIDTH / 2));
        } else if (isTreasure()) {
            g.drawImage(Assets.dirt, x, y, TILE_WIDTH, TILE_HEIGHT, null);
            Text.drawStringFrom(g, "₧", x + (TILE_WIDTH / 2), y + (TILE_HEIGHT / 2), true, Color.yellow, new Font("TimesRoman", Font.PLAIN, TILE_WIDTH / 2));
        } else if (isMonster()) {
            g.drawImage(Assets.dirt, x, y, TILE_WIDTH, TILE_HEIGHT, null);
        } else
            g.drawImage(Assets.wall, x, y, TILE_WIDTH, TILE_HEIGHT, null);
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
