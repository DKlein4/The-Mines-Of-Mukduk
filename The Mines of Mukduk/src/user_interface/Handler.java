package user_interface;

import java.awt.Graphics;
import java.util.LinkedList;

import entities.Entity;

/**
 * @author Dustin; This class handles all entities. It basically updates every
 *         single entity every tick.
 */
public class Handler {
	// This list holds every entity
	LinkedList<Entity> entity = new LinkedList<Entity>();

	// Tick each entity
	public void tick() {
		for (int i = 0; i < entity.size(); i++) {
			Entity tempEntity = entity.get(i);
			tempEntity.tick();
		}
	}

	// Render each entity
	public void render(Graphics g) {
		for (int i = 0; i < entity.size(); i++) {
			Entity tempEntity = entity.get(i);
			tempEntity.render(g);
		}
	}

	public void addEntity(Entity entity) {
		this.entity.add(entity);
	}

	public void removeEntity(Entity entity) {
		this.entity.remove(entity);
	}
}