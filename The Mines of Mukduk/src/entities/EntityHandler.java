package entities;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * @author Dustin; This class handles all entities. It basically updates every
 *         single entity every tick.
 */
public class EntityHandler {
	// This list holds every entity
	public static LinkedList<Entity> entity = new LinkedList<Entity>();

	// Tick each entity
	public void tick() {
		for (int i = 0; i < entity.size(); i++) {
			Entity tempEntity = entity.get(i);
			tempEntity.tick();
		}
	}

	// Render each entity
	public static void render(Graphics g) {
		for (int i = 0; i < entity.size(); i++) {
			Entity tempEntity = entity.get(i);
			tempEntity.render(g);
		}
	}

	public static void addEntity(Entity entity) {
		EntityHandler.entity.add(entity);
	}

	public void removeEntity(Entity entity) {
		EntityHandler.entity.remove(entity);
	}
}
