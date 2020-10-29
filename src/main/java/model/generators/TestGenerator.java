package model.generators;

import model.Coordinates;
import model.Direction;
import model.rooms.Room;
import model.rooms.SimpleRoom;

public class TestGenerator implements DungeonGenerator {
    @Override
    public Room[][] generate() {
        Room[][] rooms = new Room[2][2];

        rooms[0][0] = new SimpleRoom(new Coordinates(0, 0), Direction.RIGHT);
        rooms[1][0] = new SimpleRoom(new Coordinates(1, 0), Direction.LEFT);

        return rooms;
    }

    @Override
    public Coordinates getSpawnRoom() {
        return new Coordinates(0, 0);
    }
}
