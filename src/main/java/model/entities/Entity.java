package model.entities;

import model.Coordinates;
import model.Game;

public abstract class Entity {
    protected Coordinates coords;
    protected String name;
    protected double size;
    protected boolean isActive = false;

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
        isActive = true;
    }

    public void deleteEntity() {
        Game.getInstance().deleteEntity(this);
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public double getSize() {
        return size;
    }

    @Override
    public String toString() {
        return name;
    }
}
