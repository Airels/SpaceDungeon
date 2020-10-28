package model;

import model.rooms.MonsterRoom;
import model.rooms.Room;

public class DungeonGenerator {

    public static Room[][] generate() {
        Room[][] rooms = new Room[1][1];

        rooms[0][0] = new MonsterRoom(new Coordinates(0, 0), Direction.UP, Direction.RIGHT, Direction.LEFT, Direction.DOWN);

        return rooms;
    }
}
