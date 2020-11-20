package model.entities.characters.players;

import controller.App;
import javafx.scene.paint.Color;
import javafx.util.Builder;

public class PlayerBuilder implements Builder<Player> {
    private int healthPoints = 400,
        strength = 20;
    private double speed = 1,
        size = 100;
    private Color color = Color.GREEN;

    private String name;


    public PlayerBuilder(String name) {
        this.name = name;
    }

    @Override
    public Player build() {
        return new Player(name, size, healthPoints, strength, speed, color);
    }

    public PlayerBuilder setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
        return this;
    }

    public PlayerBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public PlayerBuilder setSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    public PlayerBuilder setSize(double size) {
        this.size = size;
        return this;
    }

    public PlayerBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getStrength() {
        return strength;
    }

    public double getSpeed() {
        return speed;
    }

    public double getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }
}
