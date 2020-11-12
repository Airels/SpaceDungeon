package model.rooms;

import model.Coordinates;
import model.Direction;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;

import java.util.*;

public class MonsterRoom implements Room {
    public static final int MINIMAL_NB_OF_MONSTER = 1;
    public static final int MAXIMAL_NB_OF_MONSTER = 4;
    public static final int NB_TRIES_BEFORE_FORCED_SPAWN = 20;
    public static final double FORCED_RATE_VALUE = 0.01;

    private final Set<Direction> openedWays;
    private final Set<Direction> doorWays;
    private final Set<Direction> finalWays;
    private final Coordinates coords;
    private final List<Entity> entities;

    public MonsterRoom(Coordinates coords, Direction... directionsOpened) {
        this.coords = coords;
        this.entities = new ArrayList<>();

        openedWays = new HashSet<>(); openedWays.addAll(Arrays.asList(directionsOpened));
        doorWays = new HashSet<>();
        finalWays = new HashSet<>();

        generate();
    }

    @Override
    public Coordinates getCoords() {
        return coords;
    }

    @Override
    public void loadedEvent() {

    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public Set<Direction> getOpenedWays() {
        return openedWays;
    }

    @Override
    public void addOpenedWay(Direction direction) {
        openedWays.add(direction);
    }

    @Override
    public void removeOpenedWay(Direction direction) {
        openedWays.remove(direction);
    }

    @Override
    public Set<Direction> getDoorWays() {
        return doorWays;
    }

    @Override
    public void addDoorWay(Direction direction) {
        doorWays.add(direction);
    }

    @Override
    public void removeDoorWay(Direction direction) {
        doorWays.remove(direction);
    }

    @Override
    public Set<Direction> getFinalWays() {
        return finalWays;
    }

    @Override
    public void addFinalWay(Direction direction) {
        finalWays.add(direction);
    }

    @Override
    public void removeFinalWay(Direction direction) {
        finalWays.remove(direction);
    }


    @Override
    public void generate() {
        int nbTriesBeforeForcedSpawn = NB_TRIES_BEFORE_FORCED_SPAWN;
        int nbOfMonster = (int)(MINIMAL_NB_OF_MONSTER + Math.random()*MAXIMAL_NB_OF_MONSTER);

        while (entities.size() < nbOfMonster) {
            double value = Math.random();

            if (nbTriesBeforeForcedSpawn <= 0)
                value = FORCED_RATE_VALUE;

            MonsterType monsterType = MonsterType.randomMonsterType();

            if (value <= monsterType.getSpawnRate()){
                entities.add(new Monster(monsterType));
                nbTriesBeforeForcedSpawn = NB_TRIES_BEFORE_FORCED_SPAWN;
            }
            else
                nbTriesBeforeForcedSpawn--;
        }
    }
}
