package model.rooms;

import controller.App;
import model.Coordinates;
import model.Direction;
import model.entities.Entity;
import model.entities.characters.Player;

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

    static boolean isNearFromDoor(Player player) {
        return !(directionFromNearestDoor(player) == null);
    }

    static Direction directionFromNearestDoor(Player player) {
        Coordinates coords = player.getCoords();

        if (coords.getDistance(getTopWayCoordinates()) < player.getActionRange())
            return Direction.UP;
        if (coords.getDistance(getLeftWayCoordinates()) < player.getActionRange())
            return Direction.LEFT;
        if (coords.getDistance(getDownWayCoordinates()) < player.getActionRange())
            return Direction.DOWN;
        if (coords.getDistance(getRightWayCoordinates()) < player.getActionRange())
            return Direction.RIGHT;

        return null;
    }

    static Coordinates getTopWayCoordinates() {
        return new Coordinates(App.WIDTH/2, App.WALL_SIZE);
    }

    static Coordinates getLeftWayCoordinates() {
        return new Coordinates(App.WALL_SIZE, App.HEIGHT/2);
    }

    static Coordinates getDownWayCoordinates() {
        return new Coordinates(App.WIDTH/2, App.HEIGHT-App.WALL_SIZE);
    }

    static Coordinates getRightWayCoordinates() {
        return new Coordinates(App.WIDTH-App.WALL_SIZE, App.HEIGHT/2);
    }
}
