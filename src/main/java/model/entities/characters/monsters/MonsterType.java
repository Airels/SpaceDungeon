package model.entities.characters.monsters;

import javafx.scene.paint.Color;
import model.entities.characters.ai.BossMonsterAI;
import model.entities.characters.ai.StupidMonsterAI;

public enum MonsterType {

    BLOB(0.50),

    CHIMERE(0.25),

    ALIEN(0.15),

    THE_BOSS(0),

    SWARM(0);


    private final double spawnRate;

    MonsterType(double spawnRate) {
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
}
