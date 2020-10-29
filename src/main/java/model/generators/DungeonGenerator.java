package model.generators;

import model.Coordinates;
import model.Direction;
import model.rooms.MonsterRoom;
import model.rooms.Room;
import model.rooms.SimpleRoom;

public interface DungeonGenerator {

    Room[][] generate();
    Coordinates getSpawnRoom();
}
