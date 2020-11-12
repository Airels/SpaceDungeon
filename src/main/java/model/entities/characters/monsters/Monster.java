package model.entities.characters.monsters;

import controller.App;
import javafx.scene.paint.Color;
import model.Coordinates;
import model.Fight;
import model.Game;
import model.entities.characters.Character;
import model.entities.characters.players.Player;
import model.entities.characters.ai.AI;

public class Monster extends Character {
    private final AI monsterAI;

    public Monster(Coordinates coords, String name, double size, int healthPoints, int strength, int actionRange, double speed, Color color, AI monsterAI) {
        super(
                coords,
                name,
                size,
                healthPoints,
                strength,
                actionRange,
                speed,
                color
        );

        this.monsterAI = monsterAI;
    }

    public Monster(MonsterBuilder builder) {
        this(
                builder.getCoords(),
                builder.getName(),
                builder.getSize(),
                builder.getHealthPoints(),
                builder.getStrength(),
                builder.getActionRange(),
                builder.getSpeed(),
                builder.getColor(),
                builder.getMonsterAI()
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
