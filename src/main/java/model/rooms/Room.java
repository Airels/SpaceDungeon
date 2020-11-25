package model.rooms;

import controller.App;
import model.Coordinates;
import model.Direction;
import model.Game;
import model.entities.Entity;
import model.entities.characters.players.Player;

import java.util.List;
import java.util.Set;

public interface Room {

    Coordinates getCoords();

    void loadedEvent();

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

    static void openDoor(Direction direction) {
        Game.getInstance().roomManager().openDoor(direction);
    }
}
