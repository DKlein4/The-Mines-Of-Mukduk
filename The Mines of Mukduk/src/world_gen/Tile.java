package world_gen;

/**
 * @author Dustin; One space on the map that has different attributes.
 */
public class Tile {

	// Different states a tile can be
	private boolean isWall, isFloor, isLadder, isUnexplored, isTreasure, isMonster, isSpawn;

	public Tile() {
		isWall = isFloor = isLadder = isUnexplored = isTreasure = isMonster = isSpawn = false;
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
