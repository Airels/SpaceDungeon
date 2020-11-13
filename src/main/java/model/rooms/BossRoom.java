package model.rooms;

import model.Coordinates;
import model.Direction;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.characters.monsters.Monster;
import view.GraphicEngine;

import java.util.*;

public class BossRoom implements Room {
    private final Set<Direction> openedWays;
    private final Set<Direction> doorWays;
    private final Set<Direction> finalWays;
    private final Coordinates coords;
    private final List<Chest> chests;
    private final List<Entity> entities;


    public BossRoom(Coordinates coords, List<Chest> chests, Monster boss, Direction... directionsOpened) {
        this.coords = coords;
        this.chests = (chests == null) ? new ArrayList<>() : chests;
        this.entities = new ArrayList<>(); entities.add(boss);

        openedWays = new HashSet<>(); openedWays.addAll(Arrays.asList(directionsOpened));
        doorWays = new HashSet<>();
        finalWays = new HashSet<>();

        generate();
    }

    public BossRoom(Coordinates coords, Monster boss, Direction... directionsOpened) {
        this(coords, new ArrayList<>(), boss, directionsOpened);
    }

    @Override
    public Coordinates getCoords() {
        return coords;
    }

    @Override
    public void loadedEvent() {
        if (!entities.isEmpty())
            GraphicEngine.getInstance().showNotification("Defeat the boss to win!");
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

    }
}
