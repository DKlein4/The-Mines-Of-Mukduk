package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Dustin; This class handles all entities. It basically updates every
 *         single entity every tick.
 */
public class EntityHandler {
	// This list holds every entity
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	private Player player;
	
	public EntityHandler(){
	}

	// Tick each entity
	public void tick() {
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			e.tick();
		}
	}

	// Render each entity
	public void render(Graphics g) {
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			e.render(g);
		}
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	
	// GETTERS AND SETTERS
	
	
	public Player getPlayer(){
		return player;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
}
