package model.rooms;

import model.Coordinates;
import model.Direction;
import model.entities.Entity;

import java.util.*;

public class SimpleRoom implements Room {
    private final Set<Direction> openedWays;
    private final Set<Direction> doorWays;
    private final Set<Direction> finalWays;
    private final Coordinates coords;
    private final List<Entity> entities;

    public SimpleRoom(Coordinates coords, Direction... directionsOpened) {
        this.coords = coords;
        this.entities = new ArrayList<>();

        openedWays = new HashSet<>(); openedWays.addAll(Arrays.asList(directionsOpened));
        doorWays = new HashSet<>();
        finalWays = new HashSet<>();
    }

    @Override
    public Coordinates getCoords() {
        return coords;
    }

    @Override
    public void loadedEvent() {
        // No events
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
        // Nothing to generate
    }
}
