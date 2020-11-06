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

    private static final int MINIMUM_NEEDED_SIMPLE_ROOM = 1;

    private int nbOfRooms;
    private int nbOfMonsterRooms;
    private final int nbOfBossRoom = 1;
    private List<Room> simpleRooms = new ArrayList<>();

    public BasicGenerator(int minNbOfRooms, int maxNbOfRooms, int minNbOfMonsterRooms){
        int minNeededSimpleRoom = MINIMUM_NEEDED_SIMPLE_ROOM;

        if(minNbOfMonsterRooms + nbOfBossRoom + minNeededSimpleRoom >= maxNbOfRooms)
            throw new IllegalArgumentException();

        this.nbOfMonsterRooms = (int) (minNbOfMonsterRooms + Math.random()*3);
        if(nbOfMonsterRooms + nbOfBossRoom + minNeededSimpleRoom >= maxNbOfRooms )
            this.nbOfMonsterRooms = minNbOfMonsterRooms;

        if(minNbOfRooms - nbOfBossRoom - nbOfMonsterRooms + minNeededSimpleRoom > 0)
            minNeededSimpleRoom = minNbOfRooms - nbOfBossRoom - nbOfMonsterRooms;

        int maxAddedRoom = maxNbOfRooms - (minNeededSimpleRoom + nbOfMonsterRooms + nbOfBossRoom);
        this.nbOfRooms = (int) (minNeededSimpleRoom + nbOfMonsterRooms + nbOfBossRoom + Math.random()*(maxAddedRoom+1));
    }

    @Override
    public Room[][] generate() {
        Room[][] rooms = new Room[nbOfRooms*3][nbOfRooms*3];
        int bossRoomX = nbOfRooms + nbOfRooms/2;
        int bossRoomY = nbOfRooms + nbOfRooms/2;
        Monster boss = new Monster(MonsterType.ALIEN);
        Direction[] possibleWays = {Direction.DOWN,Direction.LEFT,Direction.RIGHT,Direction.UP};
        Direction bossOpenedWay = possibleWays[(int) (Math.random()*4)];

        rooms[bossRoomX][bossRoomY] = new BossRoom(new Coordinates(bossRoomX,bossRoomY),boss,bossOpenedWay);

        Room currentRoom = rooms[bossRoomX][bossRoomY];
        int currentX = bossRoomX;
        int currentY = bossRoomY;
        int nbOfMonsterRoomRemaining = nbOfMonsterRooms;
        int nbOfSimpleRoomRemaining = nbOfRooms - nbOfMonsterRooms - nbOfBossRoom;


        for (int i = 0; i < nbOfRooms-1 ; i++) {
            Set<Direction> ways = currentRoom.getOpenedWays();
            for (Direction way : ways) {
                int roomType = (int) (Math.random() * 2); // result 0 or 1 -> 0: SimpleRoom / 1: MonsterRoom

                if (way == Direction.DOWN && rooms[currentX][currentY + 1] == null) {
                    if (roomType == 0 && nbOfSimpleRoomRemaining > 0) {
                        rooms[currentX][currentY + 1] =
                                new SimpleRoom(new Coordinates(currentX, currentY + 1),Direction.UP);
                        simpleRooms.add(rooms[currentX][currentY+1]);

                        nbOfSimpleRoomRemaining--;
                        currentY++;
                    } else if (roomType == 1 && nbOfMonsterRoomRemaining > 0) {
                        rooms[currentX][currentY + 1] =
                                new MonsterRoom(new Coordinates(currentX, currentY + 1),Direction.UP);

                        nbOfMonsterRoomRemaining--;
                        currentY++;
                    } else {
                        currentRoom.removeDoorWay(Direction.DOWN);
                    }
                }

                if (way == Direction.UP && rooms[currentX][currentY - 1] == null) {
                    if (roomType == 0 && nbOfSimpleRoomRemaining > 0) {
                        rooms[currentX][currentY - 1] =
                                new SimpleRoom(new Coordinates(currentX, currentY - 1),Direction.DOWN);
                        simpleRooms.add(rooms[currentX][currentY-1]);

                        nbOfSimpleRoomRemaining--;
                        currentY--;
                    } else if (roomType == 1 && nbOfMonsterRoomRemaining > 0) {
                        rooms[currentX][currentY - 1] =
                                new MonsterRoom(new Coordinates(currentX, currentY - 1),Direction.DOWN);

                        nbOfMonsterRoomRemaining--;
                        currentY--;
                    } else {
                        currentRoom.removeDoorWay(Direction.UP);
                    }
                }

                if (way == Direction.RIGHT && rooms[currentX + 1][currentY] == null) {
                    if (roomType == 0 && nbOfSimpleRoomRemaining > 0) {
                        rooms[currentX + 1][currentY] =
                                new SimpleRoom(new Coordinates(currentX + 1, currentY), Direction.LEFT);
                        simpleRooms.add(rooms[currentX + 1][currentY]);

                        nbOfSimpleRoomRemaining--;
                        currentX++;
                    } else if (roomType == 1 && nbOfMonsterRoomRemaining > 0) {
                        rooms[currentX + 1][currentY] =
                                new MonsterRoom(new Coordinates(currentX + 1, currentY), Direction.LEFT);

                        nbOfMonsterRoomRemaining--;
                        currentX++;
                    } else {
                        currentRoom.removeDoorWay(Direction.RIGHT);
                    }
                }

                if (way == Direction.LEFT && rooms[currentX - 1][currentY] == null) {
                    if (roomType == 0 && nbOfSimpleRoomRemaining > 0) {
                        rooms[currentX - 1][currentY] =
                                new SimpleRoom(new Coordinates(currentX - 1, currentY), Direction.RIGHT);
                        simpleRooms.add(rooms[currentX - 1][currentY]);

                        nbOfSimpleRoomRemaining--;
                        currentX--;
                    } else if (roomType == 1 && nbOfMonsterRoomRemaining > 0) {
                        rooms[currentX - 1][currentY] =
                                new MonsterRoom(new Coordinates(currentX - 1, currentY), Direction.RIGHT);

                        nbOfMonsterRoomRemaining--;
                        currentX--;
                    } else {
                        currentRoom.removeDoorWay(Direction.LEFT);
                    }
                }
            }


            currentRoom = rooms[currentX][currentY];
            if (nbOfSimpleRoomRemaining + nbOfMonsterRoomRemaining < nbOfRooms - nbOfBossRoom) {
                if (currentRoom.getOpenedWays().size() < 2) {
                    for (int j = 0; j < (int) (Math.random()*4) ; j++) {
                        Direction wayToOpen = possibleWays[(int) (Math.random() * 4)];
                        currentRoom.addOpenedWay(wayToOpen);
                    }
                } else {
                    int random = (int) (Math.random()*4);
                    switch (random) {
                        case 0: currentX++; break;
                        case 1: currentX--; break;
                        case 2: currentY++; break;
                        case 3: currentY--; break;
                    }
                }
            }
        }

        for (int x = 0; x < rooms.length; x++) {
            for (int y = 0; y < rooms.length; y++) {
                currentRoom = rooms[x][y];

                if (currentRoom == null) continue;

                Room targetRoom;

                for (Direction way : currentRoom.getOpenedWays()) {
                    switch (way) {
                        case UP:    targetRoom = rooms[x][y - 1]; break;
                        case DOWN:  targetRoom = rooms[x][y + 1]; break;
                        case LEFT:  targetRoom = rooms[x - 1][y]; break;
                        case RIGHT: targetRoom = rooms[x + 1][y]; break;
                        default:    targetRoom = null;
                    }

                    if (targetRoom == null) {
                        currentRoom.removeDoorWay(way);
                    } else if (!targetRoom.getOpenedWays().contains(way)) {
                        targetRoom.addOpenedWay(way.reverse());
                    }
                }
            }
        }

        for (int y = 0; y < rooms.length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                System.out.print("[" + ((rooms[x][y] == null) ? 0 : 1) + "]");
            }

            System.out.println();
        }


        // TODO : A supprimer quand le débug sera terminé
        Room bossRoom = rooms[bossRoomX][bossRoomY];
        // bossRoom.getOpenedWays().clear();

        return rooms;
    }

    @Override
    public Room getSpawnRoom() {
        return simpleRooms.get((int)(Math.random()*simpleRooms.size()));
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
