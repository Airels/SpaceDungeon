package model.entities.characters.monsters;

import javafx.scene.paint.Color;
import javafx.util.Builder;
import model.Coordinates;
import model.entities.characters.ai.AI;
import model.entities.characters.ai.SimpleMonsterAI;

public class MonsterBuilder implements Builder<Monster> {
    private Color color = Color.RED;
    private AI monsterAI = new SimpleMonsterAI();
    private double speed = 1;
    private int healPoints = 100,
            maxHealth = 100,
            strength = 10,
            size = 20,
            actionRange = size;

    private Coordinates coords;
    private String name;

    public MonsterBuilder(String name) {
        if (name.isEmpty()) throw new IllegalArgumentException("Name of monster cannot be null");

        this.name = name;
    }

    public static MonsterBuilder newBuilder(String name) {
        return new MonsterBuilder(name);
    }

    public MonsterBuilder setCoords(Coordinates coords) {
        this.coords = coords;

        return this;
    }

    public MonsterBuilder setColor(Color color) {
        this.color = color;

        return this;
    }

    public MonsterBuilder setMonsterAI(AI monsterAI) {
        this.monsterAI = monsterAI;

        return this;
    }

    public MonsterBuilder setHealPoints(int healPoints) {
        this.healPoints = healPoints;

        return this;
    }

    public MonsterBuilder setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;

        return this;
    }

    public MonsterBuilder setStrength(int strength) {
        this.strength = strength;

        return this;
    }

    public MonsterBuilder setSpeed(double speed) {
        this.speed = speed;

        return this;
    }

    public MonsterBuilder setSize(int size) {
        this.size = size;

        return this;
    }

    public MonsterBuilder setActionRange(int actionRange) {
        this.actionRange = actionRange;

        return this;
    }

    public MonsterBuilder setName(String name) {
        this.name = name;

        return this;
    }

    @Override
    public Monster build() {
        if (coords == null)
            coords = new Coordinates(100, 100);

        return new Monster(coords, name, healPoints, strength, speed, monsterAI, size, color);
    }

    public Color getColor() {
        return color;
    }

    public AI getMonsterAI() {
        return monsterAI;
    }

    public double getSpeed() {
        return speed;
    }

    public int getHealPoints() {
        return healPoints;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public int getSize() {
        return size;
    }

    public int getActionRange() {
        return actionRange;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public String getName() {
        return name;
    }
}
