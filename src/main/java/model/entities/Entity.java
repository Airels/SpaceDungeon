package model.entities;

import controller.App;
import javafx.scene.paint.Color;
import model.Coordinates;
import model.Game;
import model.Observable;

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
        createEntity();
    }

    public void unspawn() {
        deleteEntity();
    }

    public void createEntity() {
        Game.getInstance().addEntity(this);
    }

    public void deleteEntity() {
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
