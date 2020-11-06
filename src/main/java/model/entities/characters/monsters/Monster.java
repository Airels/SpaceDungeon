package model.entities.characters.monsters;

import javafx.scene.paint.Color;
import model.Coordinates;
import model.Fight;
import model.Game;
import model.entities.characters.Character;
import model.entities.characters.players.Player;
import model.entities.characters.ai.AI;

public class Monster extends Character {
    private final AI monsterAI;

    public Monster(Coordinates coords, String name, int healthPoints, int strength, double speed, AI monsterAI, double size, Color color) {
        this.coords = coords;
        this.name = name;
        this.healthPoints = healthPoints;
        this.maxHealth = healthPoints;
        this.strength = strength;
        this.speed = speed;
        this.size = size;
        this.actionRange = size;
        this.monsterAI = monsterAI;
        this.color = color;
    }

    public Monster(MonsterBuilder builder) {
        this(
                builder.getCoords(),
                builder.getName(),
                builder.getHealPoints(),
                builder.getStrength(),
                builder.getSpeed(),
                builder.getMonsterAI(),
                builder.getSize(),
                builder.getColor()
        );
    }

    public Monster(MonsterType monsterType) {
        this(monsterType.getBuilder());
    }

    public static Monster create(MonsterType monsterType) {
        return monsterType.getBuilder().build();
    }

    @Override
    public void action() {

    }

    @Override
    public void attack() {
        Player player = Game.getInstance().getPlayer();

        Fight fight = new Fight(this, player);
        fight.doAttack();
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void deathAction() {
        Game.getInstance().deleteEntity(this);
    }

    @Override
    public int getHealthPoints() {
        return healthPoints;
    }

    public AI getMonsterAI() {
        return monsterAI;
    }
}
