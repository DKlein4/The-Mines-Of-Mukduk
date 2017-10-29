package entities;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class handles all entities. It basically updates every single entity
 * every tick.
 * 
 * @author Dustin
 */
public class EntityHandler {

	// This list holds every entity
	private static CopyOnWriteArrayList<Entity> entities;

	@SuppressWarnings("unused")
	private Player player;

	public EntityHandler() {
		entities = new CopyOnWriteArrayList<Entity>();
	}

	public void tick() {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			e.tick();
		}
	}

	public void render(Graphics g) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
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
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e.getId() == ID.Player) {
				return (Player) e;
			}
		}
		return null;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public CopyOnWriteArrayList<Entity> getEntities() {
		return entities;
	}
}
