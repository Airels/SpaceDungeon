package model.generators;

import model.rooms.Room;

public interface DungeonGenerator {

    Room[][] generate();
    Room getSpawnRoom();
}
