package model;

import controller.App;
import exceptions.RoomNotGeneratedException;
import model.entities.characters.players.Player;
import model.entities.characters.players.PlayerObserver;
import model.generators.DungeonGenerator;
import model.rooms.Room;
import utils.Observable;
import view.GraphicEngine;

import java.util.Set;

public class RoomManager extends Observable {
    public static final Coordinates TOP_WAY_COORDINATES = new Coordinates(App.WIDTH/2, App.WALL_SIZE);
    public static final Coordinates LEFT_WAY_COORDINATES = new Coordinates(App.WALL_SIZE, App.HEIGHT/2);
    public static final Coordinates DOWN_WAY_COORDINATES = new Coordinates(App.WIDTH/2, App.HEIGHT-App.WALL_SIZE);
    public static final Coordinates RIGHT_WAY_COORDINATES = new Coordinates(App.WIDTH-App.WALL_SIZE, App.HEIGHT/2);

    private final Game game;
    private final GraphicEngine graphicEngine;
    private final Room[][] rooms;
    private Room actualRoom;

    public RoomManager(DungeonGenerator generator) {
        game = Game.getInstance();
        graphicEngine = GraphicEngine.getInstance();

        rooms = generator.generate();

        addObserver(PlayerObserver.getInstance());

        loadRoom(generator.getSpawnRoom());
    }

    private void loadRoom(Room room) {
        if (room == null) throw new RoomNotGeneratedException("The room you try to load don't exist");

        actualRoom = room;
        graphicEngine.loadRoom(room);
        room.loadedEvent();
    }

    public Room getNextRoom(Direction direction) {
        int x = (int) actualRoom.getCoords().getX();
        int y = (int) actualRoom.getCoords().getY();
        Room newRoom;

        try {
            switch (direction) {
                case UP:
                    newRoom = rooms[x][y-1];
                    break;
                case RIGHT:
                    newRoom = rooms[x+1][y];
                    break;
                case DOWN:
                    newRoom = rooms[x][y+1];
                    break;
                case LEFT:
                    newRoom = rooms[x-1][y];
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + direction);
            }

            return newRoom;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.print("Index Out Of Bounds: Did you assigned coordinates correctly ? ");
            System.err.println("Actual coords: " + x + ", " + y + " (might be wrong, check your generator)");
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public void loadNextRoom(Direction direction) {
        switch (direction) {
            case UP:
                notify(18);
                break;
            case RIGHT:
                notify(17);
                break;
            case DOWN:
                notify(16);
                break;
            case LEFT:
                notify(19);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + direction);
        }

        loadRoom(getNextRoom(direction));
    }

    public void reloadRoom() {
        loadRoom(actualRoom);
    }

    public Room actualRoom() {
        return actualRoom;
    }

    public void openDoor(Direction direction) {
        Room nextRoom = getNextRoom(direction);

        actualRoom.removeDoorWay(direction);
        nextRoom.removeDoorWay(direction.reverse());

        reloadRoom();

        game.showNotification("You opened a door!");
    }

    public static void changeRoomTrigger(Player player) {
        if (isNearFromWay(player)){
            Game game = Game.getInstance();
            Set<Direction> ways = game.roomManager().actualRoom().getOpenedWays();
            Set<Direction> doorWays = game.roomManager().actualRoom().getDoorWays();
            Direction directionFromNearestWay = directionFromNearestWay(player);

            if (ways.contains(directionFromNearestWay) && !doorWays.contains(directionFromNearestWay)) {
                if (player.lastDirection() == directionFromNearestWay)
                    game.roomManager().loadNextRoom(directionFromNearestWay);
            }
        }
    }

    public static boolean isNearFromWay(Player player) {
        return !(directionFromNearestWay(player) == null);
    }

    public static Direction directionFromNearestWay(Player player) {
        Coordinates coords = player.getCoords();

        if (coords.getDistance(TOP_WAY_COORDINATES) < player.getSize() - App.WALL_SIZE)
            return Direction.UP;
        if (coords.getDistance(LEFT_WAY_COORDINATES) < player.getSize() - App.WALL_SIZE)
            return Direction.LEFT;
        if (coords.getDistance(DOWN_WAY_COORDINATES) < player.getSize() - App.WALL_SIZE)
            return Direction.DOWN;
        if (coords.getDistance(RIGHT_WAY_COORDINATES) < player.getSize() - App.WALL_SIZE)
            return Direction.RIGHT;

        return null;
    }

    public static boolean isNearFromDoor(Player player) {
        return !(directionFromNearestDoor(player) == null);
    }

    public static Direction directionFromNearestDoor(Player player) {
        Coordinates coords = player.getCoords();
        Set<Direction> waysClosedByDoor = Game.getInstance().roomManager().actualRoom().getDoorWays();

        for (Direction wayClosedByDoor : waysClosedByDoor) {
            switch (wayClosedByDoor) {
                case UP:
                    if (coords.getDistance(TOP_WAY_COORDINATES) < player.getSize())
                        return Direction.UP;
                    break;
                case DOWN:
                    if (coords.getDistance(DOWN_WAY_COORDINATES) < player.getSize())
                        return Direction.DOWN;
                    break;
                case LEFT:
                    if (coords.getDistance(LEFT_WAY_COORDINATES) < player.getSize())
                        return Direction.LEFT;
                    break;
                case RIGHT:
                    if (coords.getDistance(RIGHT_WAY_COORDINATES) < player.getSize())
                        return Direction.RIGHT;
                    break;
                default:
                    throw new RuntimeException("Unhandled direction: " + wayClosedByDoor);
            }
        }

        return null;
    }
}
