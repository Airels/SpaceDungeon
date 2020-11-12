package model.entities;

import controller.App;
import javafx.scene.paint.Color;
import model.Coordinates;
import model.Game;

public abstract class Entity {
    protected final Coordinates coords;
    protected final String name;
    protected final double size;
    protected final Color color;

    public Entity(Coordinates coords, String name, double size, Color color) {
        this.coords = coords;
        this.name = name;
        this.size = size;
        this.color = color;
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

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return (name == null) ? "Unnamed entity" : name;
    }
}
