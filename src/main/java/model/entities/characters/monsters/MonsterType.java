package model.entities.characters.monsters;

import javafx.scene.paint.Color;
import model.entities.characters.ai.BossMonsterAI;
import model.entities.characters.ai.StupidMonsterAI;

public enum MonsterType {

    BLOB(new MonsterBuilder("Blob")
            .setHealthPoints(50)
            .setStrength(5)
            .setSpeed(0.50)
            .setSize(50)
            .setColor(Color.CYAN)
            .setMonsterAI(new StupidMonsterAI()), 0.50),

    CHIMERE(new MonsterBuilder("Chimere")
            .setHealthPoints(100)
            .setStrength(20)
            .setSpeed(0.75)
            .setSize(150)
            .setColor(Color.RED), 0.25),

    ALIEN(new MonsterBuilder("Alien")
            .setHealthPoints(200)
            .setStrength(50)
            .setSize(100)
            .setColor(Color.BLACK), 0.15),

    THE_BOSS(new MonsterBuilder("Sett")
            .setHealthPoints(300)
            .setStrength(70)
            .setSize(200)
            .setColor(Color.PURPLE)
            .setMonsterAI(new BossMonsterAI())
            .setBoss(true), 0),

    SWARM(new MonsterBuilder("Swarm")
            .setHealthPoints(69)
            .setStrength(10)
            .setSpeed(0.75)
            .setSize(40)
            .setColor(Color.GREY), 0);


    private final MonsterBuilder builder;
    private final double spawnRate;

    MonsterType(MonsterBuilder builder, double spawnRate) {
        this.builder = builder;
        this.spawnRate = spawnRate;
    }

    public double getSpawnRate(){
        return spawnRate;
    }

    public static MonsterType randomMonsterType() {
        int nbOfMonsters = MonsterType.values().length;

        int value = (int) (Math.random()*(nbOfMonsters));

        return MonsterType.values()[value];
    }

    public MonsterBuilder getBuilder() {
        return builder;
    }
}
