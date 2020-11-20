package model.entities.characters.monsters;

import controller.App;
import javafx.scene.paint.Color;
import javafx.util.Builder;
import model.Coordinates;
import model.entities.characters.ai.AI;
import model.entities.characters.ai.SimpleMonsterAI;

public class MonsterBuilder implements Builder<Monster> {
    private boolean isBoss = false;
    private Color color = Color.RED;
    private AI monsterAI = new SimpleMonsterAI();
    private double speed = 1,
        size = App.DEFAULT_ENTITY_SIZE;
    private int healthPoints = 100,
            maxHealth = 100,
            strength = 10;

    private Coordinates coords = new Coordinates(100 + Math.random() * (App.WIDTH-200), 100 + Math.random() * (App.HEIGHT-200));
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

    public MonsterBuilder setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;

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

    public MonsterBuilder setSize(double size) {
        this.size = size;

        return this;
    }

    public MonsterBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public MonsterBuilder setBoss(boolean isBoss) {
        this.isBoss = isBoss;

        return this;
    }

    @Override
    public Monster build() {
        return new Monster(coords, name, size, healthPoints, strength, speed, color, monsterAI, isBoss);
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

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public double getSize() {
        return size;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public String getName() {
        return name;
    }

    public boolean isBoss() {
        return isBoss;
    }
}
