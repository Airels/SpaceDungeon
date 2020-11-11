package model.entities.characters.monsters;

import javafx.scene.paint.Color;
import model.entities.characters.ai.AI;
import model.entities.characters.ai.SimpleMonsterAI;

public enum MonsterType {

    BLOB(new MonsterBuilder("Blob")
            .setHealPoints(50)
            .setStrength(5)
            .setSpeed(0.50)
            .setSize(10)
            .setColor(Color.CYAN), 0.50),

    CHIMERE(new MonsterBuilder("Chimere")
            .setHealPoints(100)
            .setStrength(20)
            .setSpeed(0.75)
            .setSize(30)
            .setColor(Color.RED), 0.25),

    ALIEN(new MonsterBuilder("Alien")
            .setHealPoints(200)
            .setStrength(50)
            .setSize(20)
            .setColor(Color.BLACK), 0.15),
    THE_BOSS(new MonsterBuilder("Sett")
            .setHealPoints(300)
            .setStrength(70)
            .setSize(25)
            .setColor(Color.PURPLE), 0),

    SWARM(new MonsterBuilder("Swarm")
            .setHealPoints(69)
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
