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
    private static final int NB_OF_BOSS_ROOMS = 1;
    private static final MonsterType BOSS_MONSTER = MonsterType.ALIEN;

    private int nbOfRooms;
    private int nbOfMonsterRooms;
    private int nbOfSimpleRooms;
    private List<Room> simpleRooms;

    public LabyrinthGenerator(int nbOfRooms, int nbOfMonsterRooms){
        this.nbOfRooms = ((int) Math.sqrt(nbOfRooms));

        if(nbOfMonsterRooms + NB_OF_BOSS_ROOMS + MINIMUM_NEEDED_SIMPLEROOM > nbOfRooms )
            throw new IllegalArgumentException(
                    "Your number of rooms must be higher than "
                    + (nbOfMonsterRooms + NB_OF_BOSS_ROOMS + MINIMUM_NEEDED_SIMPLEROOM)
            );

        this.nbOfMonsterRooms = nbOfMonsterRooms;
        this.nbOfSimpleRooms = nbOfRooms - nbOfMonsterRooms;

        simpleRooms = new ArrayList<>();
    }

    @Override
    public Room[][] generate() {
        Room[][] rooms = new Room[nbOfRooms][nbOfRooms];

        generateRoomsTypes(rooms);
        generateOpenedWays(rooms);

        return rooms;
    }

    public void generateRoomsTypes(Room[][] rooms) {
        int nbOfSimpleRoomsRemaining = nbOfSimpleRooms;
        int nbOfMonsterRoomsRemaining = nbOfMonsterRooms;
        int nbOfRoomsRemaining;

        int bossX = (int) (Math.random()*nbOfRooms);
        int bossY = (int) (Math.random()*nbOfRooms);
        rooms[bossX][bossY] = new BossRoom(new Coordinates(bossX,bossY), Monster.newInstance(MonsterType.ALIEN));

        for (int x = 0; x < nbOfRooms; x++) {
            for (int y = 0; y < nbOfRooms; y++) {

                nbOfRoomsRemaining = nbOfSimpleRoomsRemaining + nbOfMonsterRoomsRemaining;
                RoomType roomType = RoomType.getRandomRoomType();

                if (roomType == RoomType.SIMPLE_ROOM && rooms[x][y] == null && nbOfRoomsRemaining != 0){
                    if(nbOfSimpleRoomsRemaining != 0){
                        rooms[x][y] = new SimpleRoom(new Coordinates(x,y));
                        nbOfSimpleRoomsRemaining--;
                        simpleRooms.add(rooms[x][y]);
                    }else{
                        rooms[x][y] = new MonsterRoom(new Coordinates(x,y));
                        nbOfMonsterRoomsRemaining--;
                    }

                } else if (roomType == RoomType.MONSTER_ROOM && rooms[x][y] == null && nbOfRoomsRemaining != 0){
                    if(nbOfMonsterRoomsRemaining != 0){
                        rooms[x][y] = new MonsterRoom(new Coordinates(x,y));
                        nbOfMonsterRoomsRemaining--;
                    }else{
                        rooms[x][y] = new SimpleRoom(new Coordinates(x,y));
                        nbOfSimpleRoomsRemaining--;
                        simpleRooms.add(rooms[x][y]);
                    }
                }
            }
        }
    }

    public void generateOpenedWays(Room[][] rooms){
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
                    if (y + 1 >= nbOfRooms) {
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
                    if (x + 1 >= nbOfRooms) {
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

        for (int b = 0; b < nbOfRooms; b++) {
            for (int a = 0; a < nbOfRooms; a++) {
                System.out.print("[" + testTable[a][b] + "]");
            }
            System.out.println();
        }
    }

    @Override
    public Room getSpawnRoom() {
        return simpleRooms.get((int) (Math.random()*simpleRooms.size()));
    }


    enum RoomType {
        SIMPLE_ROOM,
        MONSTER_ROOM;

        public static RoomType getRandomRoomType() {
            return (Math.random()*2 < 1) ? SIMPLE_ROOM : MONSTER_ROOM;
        }
    }

    class SetOfRooms {
        private Set<Room> rooms;

        SetOfRooms(Room room) {
            rooms = new HashSet<>(Collections.singleton(room));
        }

        public boolean contains(Room room) {
            return rooms.contains(room);
        }

        public Room randomRoom() {
            Iterator<Room> it = rooms.iterator();
            int index = (int) (Math.random()*rooms.size());

            for (int i = 0; i < index; i++) {
                if (i == index) return it.next();

                it.next();
            }

            return null;
        }

        public void merge(SetOfRooms set) {
            rooms.addAll(set.rooms);
        }
    }
}
