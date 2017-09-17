package world_gen;

public class Tile {
	
	//Different states a tile can be
	private boolean isWall,
		isFloor,
		isDoor,
		isUnexplored,
		isTreasure,
		isMonster;
	
	public Tile(){
		isWall =
		isFloor =
		isDoor =
		isUnexplored =
		isTreasure =
		isMonster = false;
	}

	public boolean isWall() {
		return isWall;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}

	public boolean isFloor() {
		return isFloor;
	}

	public void setFloor(boolean isFloor) {
		this.isFloor = isFloor;
	}

	public boolean isDoor() {
		return isDoor;
	}

	public void setDoor(boolean isDoor) {
		this.isDoor = isDoor;
	}

	public boolean isUnexplored() {
		return isUnexplored;
	}

	public void setUnexplored(boolean isUnexplored) {
		this.isUnexplored = isUnexplored;
	}

	public boolean isTreasure() {
		return isTreasure;
	}

	public void setTreasure(boolean isTreasure) {
		this.isTreasure = isTreasure;
	}

	public boolean isMonster() {
		return isMonster;
	}

	public void setMonster(boolean isMonster) {
		this.isMonster = isMonster;
	}
	
	
}
