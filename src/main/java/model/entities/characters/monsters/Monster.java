package model.entities.characters.monsters;

import javafx.scene.paint.Color;
import model.Coordinates;
import model.Fight;
import model.Game;
import model.entities.characters.Character;
import model.entities.characters.Player;
import model.entities.characters.ai.AI;

public class Monster extends Character {
    private final AI monsterAI;

    public Monster(MonsterType monsterType) {
        this(monsterType.name, monsterType.healthPoints, monsterType.strength, monsterType.speed, monsterType.monsterAI, monsterType.size, monsterType.color);
    }

    public Monster(String name, int healthPoints, int strength, double speed, AI monsterAI, double size, Color color) {
        // TODO : Coordonnées aléatoires (plutôt vers le centre)

        this.coords = new Coordinates(100, 100);
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

    @Override
    public void action() {

    }

    @Override
    public void attack() {
        Player player = Game.getInstance().getPlayer();

        if (player.getCoords().getDistance(this.coords) <= actionRange) {
            Fight fight = new Fight(this, player);
            fight.doAttack();
        }
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
