package model.entities;

import controller.App;
import model.Coordinates;
import model.Game;
import utils.Observable;

public abstract class Entity extends Observable {
    protected final Coordinates coords;
    protected final String name;
    protected final double size;

    public Entity(Coordinates coords, String name, double size) {
        this.coords = coords;
        this.name = name;
        this.size = size;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public void spawn() {
        Game.getInstance().addEntity(this);
    }

    public void unspawn() {
        Game.getInstance().deleteEntity(this);
    }

    public double getSize() {
        return (size == 0) ? App.DEFAULT_ENTITY_SIZE : size;
    }

    @Override
    public String toString() {
        return (name == null) ? "Unnamed entity" : name;
    }
}
