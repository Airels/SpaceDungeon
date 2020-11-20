package model.entities.characters;

import controller.App;
import javafx.scene.paint.Color;
import model.Coordinates;
import model.Direction;
import model.entities.Entity;

import java.util.Objects;

public abstract class Character extends Entity {
    protected int healthPoints, strength;
    protected final int maxHealth;
    protected final double speed, actionRange;
    protected final Inventory inventory;

    private Direction lastDirection;

    public Character(Coordinates coords, String name, double size, int healthPoints, int strength, double speed, Color color) {
        super(coords, name, size, color);
        this.healthPoints = healthPoints;
        this.maxHealth = healthPoints;
        this.strength = strength;
        this.actionRange = size/2;
        this.speed = speed;
        this.inventory = new Inventory();
    }

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
                newY = coords.getY() + (direction.getY() * speed);
                break;
            case LEFT:
            case RIGHT:
                newX = coords.getX() + (direction.getX() * speed);
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

        lastDirection = direction;
    }

    public Direction lastDirection() {
        return lastDirection;
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

    public void addStrength(int value){
        strength += value;
    }

    public void heal(int heal) {
        this.healthPoints += heal;

        if (healthPoints>maxHealth)
            healthPoints = maxHealth;
    }

    public void doDamages(int damages) {
        healthPoints -= damages;

        notify(0);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return maxHealth == character.maxHealth &&
                Double.compare(character.speed, speed) == 0 &&
                Double.compare(character.actionRange, actionRange) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxHealth, speed, actionRange);
    }
}
