package model.generators;

import model.Coordinates;
import model.Direction;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;
import model.items.Item;
import model.items.StrengthPotion;
import model.items.Syringe;
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
    private int nbOfChests;
    public LabyrinthGenerator(int nbOfRooms, int nbOfMonsterRooms, int nbOfChests){
        this.nbOfChests = nbOfChests;
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
        generateChest(rooms);

        return rooms;
    }

    public void generateRoomsTypes(Room[][] rooms) {
        int nbOfSimpleRoomsRemaining = nbOfSimpleRooms;
        int nbOfMonsterRoomsRemaining = nbOfMonsterRooms;
        int nbOfRoomsRemaining;

        int bossX = (int) (Math.random()*nbOfRooms);
        int bossY = (int) (Math.random()*nbOfRooms);
        rooms[bossX][bossY] = new BossRoom(new Coordinates(bossX,bossY), new Monster(MonsterType.THE_BOSS));

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
        int x;
        int y;
        int randomWay;
        int countWay = 0;
        boolean eachRoomGotWay = true;
        boolean visitedRoomTable[][] = new boolean[nbOfRooms][nbOfRooms];

        for (int i = 0; i < nbOfRooms * nbOfRooms; i++) {
            x = (int)(Math.random()*nbOfRooms);
            y = (int)(Math.random()*nbOfRooms);
            randomWay = (int)(Math.random()*4);

            switch (randomWay) {
                case 0:
                    Direction directionDown = Direction.DOWN;
                    if (rooms[x][y].getOpenedWays().contains(directionDown)){
                        i--;
                        break;
                    }
                    if (y + 1 >= nbOfRooms) {
                        i--;
                    } else {
                        rooms[x][y].addOpenedWay(directionDown);
                        rooms[x][y + 1].addOpenedWay(directionDown.reverse());

                        visitedRoomTable[x][y] = true;
                        visitedRoomTable[x][y+1] = true;
                    }
                    break;
                case 1:
                    Direction directionUp = Direction.UP;
                    if (rooms[x][y].getOpenedWays().contains(directionUp)){
                        i--;
                        break;
                    }
                    if (y - 1 < 0) {
                        i--;
                    } else {
                        rooms[x][y].addOpenedWay(directionUp);
                        rooms[x][y - 1].addOpenedWay(directionUp.reverse());

                        visitedRoomTable[x][y] = true;
                        visitedRoomTable[x][y-1] = true;
                    }
                    break;
                case 2:
                    Direction directionRight = Direction.RIGHT;
                    if (rooms[x][y].getOpenedWays().contains(directionRight)){
                        i--;
                        break;
                    }
                    if (x + 1 >= nbOfRooms) {
                        i--;
                    } else {
                        rooms[x][y].addOpenedWay(directionRight);
                        rooms[x + 1][y].addOpenedWay(directionRight.reverse());

                        visitedRoomTable[x][y] = true;
                        visitedRoomTable[x + 1][y] = true;
                    }
                    break;
                case 3:
                    Direction directionLeft = Direction.LEFT;
                    if(rooms[x][y].getOpenedWays().contains(directionLeft)){
                        i--;
                        break;
                    }
                    if (x - 1 < 0) {
                        i--;
                    } else {
                        rooms[x][y].addOpenedWay(directionLeft);
                        rooms[x - 1][y].addOpenedWay(directionLeft.reverse());

                        visitedRoomTable[x][y] = true;
                        visitedRoomTable[x - 1][y] = true;
                    }
                    break;
            }
        }
        for (int i = 0; i <nbOfRooms; i++) {
            for (int j = 0; j < nbOfRooms; j++) {
                System.out.print("[" + visitedRoomTable[i][j] + "]");

            }
            System.out.println();
        }
    }

    public void generateChest(Room[][] rooms){
        if (nbOfChests > nbOfRooms*nbOfRooms){
            throw new IllegalArgumentException();
        }
        int itemType;
        Item item;
        int x;
        int y;
        List<Room> roomVisited = new ArrayList<>();
        for (int i = 0; i < nbOfChests ; i++) {

            x = (int) (Math.random()*nbOfRooms);
            y = (int) (Math.random()*nbOfRooms);

            Room currentRoom  = rooms[x][y];

            if(!roomVisited.contains(currentRoom)){
                roomVisited.add(currentRoom);
            }else{
                i--;
                continue;
            }
            itemType = (int) (Math.random()*2);
            if (itemType == 1){
                item = new Syringe();
            }else{
                item = new StrengthPotion();
            }

            currentRoom.getEntities().add(new Chest(item));
        }
    }

    @Override
    public Room getSpawnRoom() {
        Room room;

        do {
            room = simpleRooms.get((int) (Math.random()*simpleRooms.size()));
        } while (room.getOpenedWays().size() == 0);

        return room;
    }


    enum RoomType {
        SIMPLE_ROOM,
        MONSTER_ROOM;

        public static RoomType getRandomRoomType() {
            return (Math.random()*2 < 1) ? SIMPLE_ROOM : MONSTER_ROOM;
        }
    }
}
