package model;

import exceptions.RoomNotGeneratedException;
import model.generators.DungeonGenerator;
import model.rooms.Room;
import view.GraphicEngine;

public class RoomManager {
    private final Game game;
    private final GraphicEngine graphicEngine;
    private Room[][] rooms;
    private Room actualRoom;

    public RoomManager(DungeonGenerator generator) {
        game = Game.getInstance();
        graphicEngine = GraphicEngine.getInstance();

        rooms = generator.generate();

        loadRoom(generator.getSpawnRoom());
    }

    private void loadRoom(Room room) {
        if (room == null) throw new RoomNotGeneratedException("The room you try to load don't exist");

        actualRoom = room;
        graphicEngine.loadRoom(room);
        room.loadedEvent();
    }

    private Room getNextRoom(Direction direction) {
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
                game.getPlayer().moveToDoor(Direction.DOWN);
                break;
            case RIGHT:
                game.getPlayer().moveToDoor(Direction.LEFT);
                break;
            case DOWN:
                game.getPlayer().moveToDoor(Direction.UP);
                break;
            case LEFT:
                game.getPlayer().moveToDoor(Direction.RIGHT);
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
}
