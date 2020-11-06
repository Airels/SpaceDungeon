package model.rooms;

import model.Coordinates;
import model.Direction;
import model.entities.Chest;
import model.entities.Entity;

import java.util.*;

public class SimpleRoom implements Room {
    private Set<Direction> openedWays, doorWays, finalWays;
    private Coordinates coords;
    private List<Chest> chests;
    private List<Entity> entities;

    public SimpleRoom(Coordinates coords, List<Chest> chests, Direction... directionsOpened) {
        this.coords = coords;
        this.chests = (chests == null) ? new ArrayList<>() : chests;
        this.entities = new ArrayList<>();

        openedWays = new HashSet<>(); openedWays.addAll(Arrays.asList(directionsOpened));
        doorWays = new HashSet<>();
        finalWays = new HashSet<>();
    }

    public SimpleRoom(Coordinates coords, Direction... directionsOpened) {
        this(coords, new ArrayList<>(), directionsOpened);
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

    }
}
