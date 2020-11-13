package model.rooms;

import model.generators.LabyrinthGenerator;

public enum RoomType {
    SIMPLE_ROOM,
    MONSTER_ROOM,
    BOSS_ROOM;

    public static RoomType getRandomRoomType() {
        return (Math.random()*2 < 1) ? SIMPLE_ROOM : MONSTER_ROOM;
    }
}
