package model.rooms;

import model.Coordinates;
import model.Direction;
import model.entities.Entity;

import java.util.List;
import java.util.Set;

public interface Room {

    Coordinates getCoords();

    List<Entity> getEntities();

    Set<Direction> getOpenedWays();
    void addOpenedWay(Direction direction);
    void removeOpenedWay(Direction direction);

    Set<Direction> getDoorWays();
    void addDoorWay(Direction direction);
    void removeDoorWay(Direction direction);

    Set<Direction> getFinalWays();
    void addFinalWay(Direction direction);
    void removeFinalWay(Direction direction);

    void generate();
}
