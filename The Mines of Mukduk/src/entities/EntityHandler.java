package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class handles all entities. It basically updates every single entity
 * every tick.
 * 
 * @author Dustin
 */
public class EntityHandler {

	// This list holds every entity
	private static ArrayList<Entity> entities = new ArrayList<Entity>();

	private Player player;

	// prevents altering entities while it is being accessed
	private static boolean marker = false;

	public EntityHandler() {
	}

	public void tick() {
		if (!marker) {
			Iterator<Entity> it = entities.iterator();
			while (it.hasNext()) {
				Entity e = it.next();
				e.tick();
			}
		}
	}

	public void render(Graphics g) {
		if (!marker) {
			Iterator<Entity> it = entities.iterator();
			while (it.hasNext()) {
				Entity e = it.next();
				e.render(g);
			}
		}
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	public void removeMonsters() {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e.getId() == ID.Monster) {
				removeEntity(e);
			}
		}
	}

	// GETTERS AND SETTERS

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setMarker(boolean marker) {
		this.marker = marker;
	}
}
