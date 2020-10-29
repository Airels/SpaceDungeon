package model.generators;

import controller.App;
import model.Coordinates;
import model.Direction;
import model.entities.Chest;
import model.items.Item;
import model.items.Key;
import model.rooms.MonsterRoom;
import model.rooms.Room;
import model.rooms.SimpleRoom;

import java.util.ArrayList;
import java.util.Collections;

public class TestGenerator implements DungeonGenerator {
    @Override
    public Room[][] generate() {
        Room[][] rooms = new Room[2][2];

        rooms[0][0] = new SimpleRoom(new Coordinates(0, 0), Direction.RIGHT, Direction.DOWN);
        rooms[0][1] = new MonsterRoom(new Coordinates(0, 1), Direction.UP);

        Room room = new SimpleRoom(new Coordinates(1, 0), Direction.LEFT, Direction.DOWN);
        room.addDoorWay(Direction.DOWN);
        Chest chest = new Chest(new ArrayList<>(Collections.singleton(new Key())));
        room.getEntities().add(chest);
        rooms[1][0] = room;

        rooms[1][1] = new SimpleRoom(new Coordinates(1, 1), Direction.UP);

        return rooms;
    }

    @Override
    public Coordinates getSpawnRoom() {
        return new Coordinates(0, 0);
    }
}
