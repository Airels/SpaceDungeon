package model.entities.characters;

import controller.App;
import javafx.scene.paint.Color;
import model.Direction;
import model.entities.Entity;
import model.items.Item;

import java.util.List;

public abstract class Character extends Entity {
    protected int healthPoints, maxHealth, strength;
    protected double speed, actionRange;
    protected Inventory inventory;
    protected Color color;

    public abstract void action();
    public abstract void attack();
    public abstract int getStrength();
    public abstract void deathAction();

    public void move(Direction direction) {
        double newX = coords.getX();
        double newY = coords.getY();

        switch (direction) {
            case UP:
            case DOWN:
                newY = coords.getY() + (direction.getY()/speed);
                break;
            case LEFT:
            case RIGHT:
                newX = coords.getX() + (direction.getX()/speed);
                break;
            default:
                throw new IllegalArgumentException("Movement not recognized");
        }

        if (newX-size/2 > App.WALL_SIZE
                && newX+size/2 < App.WIDTH-App.WALL_SIZE
                && newY-size/2 > App.WALL_SIZE
                && newY+size/2 < App.HEIGHT-App.WALL_SIZE) {
            coords.setX(newX);
            coords.setY(newY);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public double getActionRange() {
        return actionRange;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int addStrength(int value){
        strength = strength + value;
        return strength;
    }

    public void heal(int heal) {
        this.healthPoints += heal;
    }

    public void doDamages(int damages) {
        healthPoints -= damages;

        if (isDead()) deathAction();
    }

    public boolean isDead() {
        return (healthPoints <= 0);
    }

    public void doKnockback(Direction direction) {
        this.move(direction);
        this.move(direction);
        this.move(direction);
    }

    public Color getColor() {
        return color;
    }
}
