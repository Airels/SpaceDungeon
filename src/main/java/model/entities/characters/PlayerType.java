package model.entities.characters;

import javafx.scene.paint.Color;

public enum PlayerType {

    NORMAL(100, 20, 40, 1, 20, Color.GREEN),
    GOD_OF_HYPERDEATH(1_000_000, 1_000_000, 40, 1, 20, Color.PURPLE);

    PlayerType(int healthPoints, int strength, int actionRange, double speed, double size, Color color) {
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.actionRange = actionRange;
        this.speed = speed;
        this.size = size;
        this.color = color;
    }

    protected final int healthPoints, strength, actionRange;
    protected final double speed, size;
    protected final Color color;
}
