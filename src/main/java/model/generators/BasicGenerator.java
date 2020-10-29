package model.generators;

import model.rooms.Room;

public class BasicGenerator implements DungeonGenerator {

    private int nbOfRooms;
    private int nbOfMonsterRooms;
    private final int nbOfBossRoom = 1;

    public BasicGenerator(int minNbOfRooms, int maxNbOfRooms, int minNbOfMonsterRooms){
        if(minNbOfMonsterRooms + nbOfBossRoom >= maxNbOfRooms)
            throw new IllegalArgumentException();

        this.nbOfMonsterRooms = (int) (minNbOfMonsterRooms + Math.random()*3);
        if(nbOfMonsterRooms + nbOfBossRoom >= maxNbOfRooms )
            this.nbOfMonsterRooms = minNbOfMonsterRooms;

        int minNeededRoom = 0;

        if(minNbOfRooms - nbOfBossRoom - nbOfMonsterRooms > 0)
            minNeededRoom = minNbOfRooms - nbOfBossRoom - nbOfMonsterRooms;

        int maxAddedRoom = maxNbOfRooms - (minNeededRoom + nbOfMonsterRooms + nbOfBossRoom);
        this.nbOfRooms = (int) (minNeededRoom + nbOfMonsterRooms + nbOfBossRoom + Math.random()*(maxAddedRoom+1));
    }

    @Override
    public Room[][] generate() {
        return new Room[0][];
    }

    public int getNbOfRooms() {
        return nbOfRooms;
    }

    public int getNbOfMonsterRooms() {
        return nbOfMonsterRooms;
    }

    public int getNbOfBossRoom() {
        return nbOfBossRoom;
    }
}
