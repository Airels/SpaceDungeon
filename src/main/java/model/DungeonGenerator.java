package model;

import model.rooms.MonsterRoom;
import model.rooms.Room;
import model.rooms.SimpleRoom;

public class DungeonGenerator {

    public static Room[][] generate() {
        Room[][] rooms = new Room[2][1];

        rooms[0][0] = new SimpleRoom(new Coordinates(0, 0), Direction.UP, Direction.LEFT, Direction.RIGHT, Direction.DOWN);
        //rooms[0][0] = new SimpleRoom(new Coordinates(0, 0), Direction.RIGHT);
        rooms[1][0] = new SimpleRoom(new Coordinates(1, 0), Direction.LEFT);

        return rooms;
    }
}
