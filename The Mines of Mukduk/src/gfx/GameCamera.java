package gfx;

import display.GUImain;
import entities.Entity;
import main.Handler;
import world_gen.Tile;

public class GameCamera {
	
	private Handler handler;
	
	private float xOffset, yOffset;
	
	private int tileWidth;
	private int tileHeight;
	private int screenWidth;
	private int screenHeight;
	private int gridSize;
	
	public GameCamera(Handler handler, float xOffset, float yOffset){
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		tileWidth = Tile.TILEWIDTH;
		tileHeight = Tile.TILEHEIGHT;
		screenWidth = GUImain.WIDTH;
		screenHeight = GUImain.HEIGHT;
		
	}
	
	public void checkBlankSpace(){
		gridSize = handler.getWorld().getMap().getGridSize();
		
		if(xOffset < 0){
			xOffset = 0;
		}else if(xOffset > gridSize * tileWidth - screenWidth){
			xOffset = gridSize * tileWidth - screenWidth;
		}
		
		if(yOffset < 0){
			yOffset = 0;
		}else if(yOffset > gridSize * tileHeight - screenHeight){
			yOffset = gridSize * tileHeight - screenHeight;
		}
	}
	
	public void centerOnEntity(Entity e){
		xOffset = e.getXPos() - GUImain.WIDTH / 2 + e.getWidth() / 2;
		yOffset = e.getYPos() - GUImain.HEIGHT / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt){
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}
