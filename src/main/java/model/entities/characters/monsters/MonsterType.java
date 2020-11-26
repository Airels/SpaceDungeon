package model.entities.characters.monsters;

import model.entities.EntityFactory;

public enum MonsterType {

    BLOB(0.50),

    CHIMERE(0.32),

    ALIEN(0.23),

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

    public static MonsterType getMonsterType(Monster monster) {
        for (MonsterType type : MonsterType.values()) {
            if (new EntityFactory().createMonster(type).equals(monster))
                return type;
        }

        return null;
    }
}
