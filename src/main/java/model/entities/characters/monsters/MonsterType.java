package model.entities.characters.monsters;

import javafx.scene.paint.Color;
import model.entities.characters.ai.AI;
import model.entities.characters.ai.SimpleMonsterAI;

public enum MonsterType {
    BLOB("Blob",
            50,
            5,
            0.50,
            0.50,
            new SimpleMonsterAI(),
            10,
            Color.CYAN),

    CHIMERE("Chimere",
            100,
            20,
            0.75,
            0.25,
            new SimpleMonsterAI(),
            30,
            Color.RED),

    ALIEN("Alien",
            200,
            50,
            1,
            0.15,
            new SimpleMonsterAI(),
            20,
            Color.BLACK),

    SWARM("Swarm",
            69,
            10,
                0.75,
            0,
            new SimpleMonsterAI(),
            40,
            Color.GREY);

    protected final double spawnRate, speed;

    protected final String name;
    protected final int healthPoints, strength;
    protected final double size;
    protected final AI monsterAI;
    protected final Color color;

    MonsterType(String name, int healthPoints, int strength, double speed, double spawnRate, AI monsterAI, double size, Color color) {
        this.spawnRate = spawnRate;

        this.name = name;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.monsterAI = monsterAI;
        this.speed = speed;
        this.size = size;
        this.color = color;
    }

    public double getSpawnRate(){
        return spawnRate;
    }

    public static MonsterType randomMonsterType() {
        int nbOfMonsters = MonsterType.values().length;

        int value = (int) (Math.random()*(nbOfMonsters));

        return MonsterType.values()[value];
    }
}
