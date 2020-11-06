package model.generators;

import model.Coordinates;
import model.Direction;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;
import model.rooms.BossRoom;
import model.rooms.MonsterRoom;
import model.rooms.Room;
import model.rooms.SimpleRoom;

import java.util.*;

import static java.lang.Math.*;

public class LabyrinthGenerator implements DungeonGenerator {

    private final int MINIMUM_NEEDED_SIMPLEROOM = 1;
    private int nbOfRooms;
    private int nbOfMonsterRooms;
    private int nbOfSimpleRooms;
    private final int nbOfBossRoom = 1;
    private List<Room> simpleRooms = new ArrayList<>();

    public LabyrinthGenerator(int sqrtOfNbOfRooms, int NbOfMonsterRooms){
        this.nbOfRooms = sqrtOfNbOfRooms;
        if(NbOfMonsterRooms+ nbOfBossRoom + MINIMUM_NEEDED_SIMPLEROOM > nbOfRooms )
            throw new IllegalArgumentException();

        this.nbOfMonsterRooms = NbOfMonsterRooms;
        this.nbOfSimpleRooms = nbOfRooms - nbOfMonsterRooms;


    }
    @Override
    public Room[][] generate() {
        Room[][] rooms = new Room[nbOfRooms][nbOfRooms];

        int nbOfSimpleRoomsRemaining = nbOfSimpleRooms;
        int nbOfMonsterRoomsRemaining = nbOfMonsterRooms;
        int nbOfRoomsRemaining;

        int bossX = (int) (Math.random()*nbOfRooms);
        int bossY = (int) (Math.random()*nbOfRooms);
        rooms[bossX][bossY] = new BossRoom(new Coordinates(bossX,bossY),new Monster(MonsterType.ALIEN));

        for (int x = 0; x < nbOfRooms - 1 ; x++) {
            for (int y = 0; y < nbOfRooms - 1; y++) {

                nbOfRoomsRemaining = nbOfSimpleRoomsRemaining + nbOfMonsterRoomsRemaining;
                int roomType =(int) (Math.random()*2); // 0 :simpleRoom  1:MonsterRoom

                if (roomType == 0 && rooms[x][y] == null && nbOfRoomsRemaining != 0){
                    if(nbOfSimpleRoomsRemaining != 0){
                        rooms[x][y] = new SimpleRoom(new Coordinates(x,y));
                        nbOfSimpleRoomsRemaining--;
                    }else{
                        rooms[x][y] = new MonsterRoom(new Coordinates(x,y));
                        nbOfMonsterRoomsRemaining--;
                    }

                } else if (roomType == 1 && rooms[x][y] == null && nbOfRoomsRemaining != 0){
                    if(nbOfMonsterRoomsRemaining != 0){
                        rooms[x][y] = new MonsterRoom(new Coordinates(x,y));
                        nbOfMonsterRoomsRemaining--;
                    }else{
                        rooms[x][y] = new SimpleRoom(new Coordinates(x,y));
                        nbOfSimpleRoomsRemaining--;
                    }
                }
            }
            generateOpenWays(rooms);
        }
        return rooms;
    }

    public void generateOpenWays(Room[][] rooms){
        int[][] testTable = new int[nbOfRooms][nbOfRooms];
        int id=0;
        for (int x = 0; x < nbOfRooms; x++) {
            for (int y = 0; y < nbOfRooms; y++) {
                testTable[x][y] = id;
                id++;

            }

        }
        int x;
        int y;
        int randomWay;
        for (int i = 0; i < nbOfRooms * nbOfRooms - 1; i++) {
            x = (int)(Math.random()*nbOfRooms);
            y = (int)(Math.random()*nbOfRooms);
            randomWay = (int)(Math.random()*4);
            switch (randomWay) {
                case 0:
                    Direction directionDown = Direction.DOWN;
                    if (y + 1 > nbOfRooms) {
                        i--;
                    } else {
                        rooms[x][y].addOpenedWay(directionDown);
                        rooms[x][y + 1].addOpenedWay(directionDown.reverse());

                        testTable[x][y] = min(testTable[x][y],testTable[x][y+1]);
                        testTable[x][y+1] = min(testTable[x][y],testTable[x][y+1]);
                    }
                    break;
                case 1:
                    Direction directionUp = Direction.UP;
                    if (y - 1 < 0) {
                        i--;
                    } else {
                        rooms[x][y].addOpenedWay(directionUp);
                        rooms[x][y - 1].addOpenedWay(directionUp.reverse());

                        testTable[x][y] = min(testTable[x][y],testTable[x][y-1]);
                        testTable[x][y-1] = min(testTable[x][y],testTable[x][y-1]);
                    }
                        break;

                case 2:
                    Direction directionRight = Direction.RIGHT;
                    if (x + 1 > nbOfRooms) {
                        i--;
                    } else {
                        rooms[x][y].addOpenedWay(directionRight);
                        rooms[x + 1][y].addOpenedWay(directionRight.reverse());

                        testTable[x +1][y] = min(testTable[x][y],testTable[x+1][y]);
                        testTable[x][y] = min(testTable[x][y],testTable[x+1][y]);
                    }
                        break;

                case 3:
                    Direction directionLeft = Direction.LEFT;
                    if (x - 1 < 0) {
                        i--;
                    } else {
                        rooms[x][y].addOpenedWay(directionLeft);
                        rooms[x - 1][y].addOpenedWay(directionLeft.reverse());

                        testTable[x][y] = min(testTable[x][y],testTable[x-1][y]);
                        testTable[x-1][y] = min(testTable[x][y],testTable[x-1][y]);
                    }
                        break;

            }
        }


    }

    @Override
    public Room getSpawnRoom() {
        return null;
    }
}
