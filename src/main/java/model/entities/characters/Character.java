package model.entities.characters;

import controller.App;
import javafx.scene.paint.Color;
import model.Coordinates;
import model.Direction;
import model.entities.Entity;

public abstract class Character extends Entity {
    protected int healthPoints, strength;
    protected final int maxHealth;
    protected double speed, actionRange;
    protected final Inventory inventory;

    private Direction lastDirection;

    public Character(Coordinates coords, String name, double size, int healthPoints, int strength, int actionRange, double speed, Color color) {
        super(coords, name, size, color);
        this.healthPoints = healthPoints;
        this.maxHealth = healthPoints;
        this.strength = strength;
        this.actionRange = actionRange;
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
}
