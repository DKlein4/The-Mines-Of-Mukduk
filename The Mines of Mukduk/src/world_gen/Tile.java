package world_gen;

public class Tile {

	// Different states a tile can be
	private boolean isWall, isFloor, isDoor, isUnexplored, isTreasure, isMonster;

	public Tile() {
		isWall = isFloor = isDoor = isUnexplored = isTreasure = isMonster = false;
	}
	
	private void clearTile(){
		isWall = isFloor = isDoor = isUnexplored = isTreasure = isMonster = false;
	}

	public boolean isWall() {
		return isWall;
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

	public boolean isDoor() {
		return isDoor;
	}

	public void setDoor(boolean isDoor) {
		clearTile();
		this.isDoor = isDoor;
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
}
