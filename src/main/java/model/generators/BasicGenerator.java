package model.generators;

import model.Coordinates;
import model.Direction;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;
import model.rooms.BossRoom;
import model.rooms.MonsterRoom;
import model.rooms.Room;
import model.rooms.SimpleRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasicGenerator implements DungeonGenerator {

    private int nbOfRooms;
    private int nbOfMonsterRooms;
    private final int nbOfBossRoom = 1;
    private List<Room> simpleRooms = new ArrayList<>();

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
        Room[][] rooms = new Room[nbOfRooms][nbOfRooms];
        int bossRoomX = (int)(Math.random()*nbOfRooms);
        int bossRoomY = (int)(Math.random()*nbOfRooms);
        Monster boss = new Monster(MonsterType.ALIEN);
        Direction[] possibleWays = {Direction.DOWN,Direction.LEFT,Direction.RIGHT,Direction.RIGHT,Direction.UP};
        Direction bossOpenedWay = possibleWays[(int) (Math.random()*4)];

        rooms[bossRoomX][bossRoomY] = new BossRoom(new Coordinates(bossRoomX,bossRoomY),boss,bossOpenedWay);

        Room currentRoom = rooms[bossRoomX][bossRoomY];
        int currentX = bossRoomX;
        int currentY = bossRoomY;
        int nbOfMonsterRoomRemaining = nbOfMonsterRooms;
        int nbOfSimpleRoomRemaining = nbOfRooms - nbOfMonsterRooms - nbOfBossRoom;


        for (int i = 0; i < nbOfRooms ; i++) {
            Set<Direction> ways = currentRoom.getOpenedWays();
            for (Direction way : ways) {
                int roomType = (int) (Math.random() * 2); // result 0 or 1 -> 0: SimpleRoom / 1: MonsterRoom


                if (way == Direction.DOWN && rooms[currentX][currentY + 1] == null) {
                    if (nbOfSimpleRoomRemaining != 0 && (roomType == 0 || nbOfMonsterRoomRemaining == 0)) {
                        rooms[currentX][currentY + 1] = new SimpleRoom(new Coordinates(currentX, currentY + 1));
                        simpleRooms.add(rooms[currentX][currentY+1]);
                    } else {
                        rooms[currentX][currentY + 1] = new MonsterRoom(new Coordinates(currentX, currentY + 1));
                    }
                    currentY += 1;
                }

                if (way == Direction.UP && rooms[currentX][currentY - 1] == null) {
                    if (nbOfSimpleRoomRemaining != 0 && (roomType == 0 || nbOfMonsterRoomRemaining == 0)) {
                        rooms[currentX][currentY - 1] = new SimpleRoom(new Coordinates(currentX, currentY - 1));
                        simpleRooms.add(rooms[currentX][currentY-1]);
                    } else {
                        rooms[currentX][currentY - 1] = new MonsterRoom(new Coordinates(currentX, currentY - 1));
                    }
                    currentY -= 1;
                }

                if (way == Direction.RIGHT && rooms[currentX + 1][currentY] == null) {
                    if (nbOfSimpleRoomRemaining != 0 && (roomType == 0 || nbOfMonsterRoomRemaining == 0)) {
                        rooms[currentX + 1][currentY] = new SimpleRoom(new Coordinates(currentX + 1, currentY));
                        simpleRooms.add(rooms[currentX+1][currentY]);
                    } else {
                        rooms[currentX + 1][currentY] = new MonsterRoom(new Coordinates(currentX + 1, currentY));
                    }
                    currentX += 1;
                }

                if (way == Direction.LEFT && rooms[currentX - 1][currentY] == null) {
                    if (nbOfSimpleRoomRemaining != 0 && (roomType == 0 || nbOfMonsterRoomRemaining == 0)) {
                        rooms[currentX - 1][currentY] = new SimpleRoom(new Coordinates(currentX - 1, currentY));
                        simpleRooms.add(rooms[currentX-1][currentY]);
                    } else {
                        rooms[currentX - 1][currentY] = new MonsterRoom(new Coordinates(currentX - 1, currentY));
                    }
                    currentX -= 1;
                }
            }
            currentRoom = rooms[currentX][currentY];
            if(currentRoom.getOpenedWays().size() == 1){
               Direction wayToOpen = possibleWays[(int)(Math.random()*4)];
               if(currentRoom.getOpenedWays().contains(wayToOpen) && wayToOpen != Direction.UP) {
                   wayToOpen = Direction.UP;
               }else{
                   wayToOpen=Direction.DOWN;
               }
            }
        }
        return rooms;
    }

    @Override
    public Coordinates getSpawnRoom() {
        Room spawnRoom = simpleRooms.get((int)(Math.random()*simpleRooms.size()));
        return spawnRoom.getCoords();
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
