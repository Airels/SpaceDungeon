package model.generators;

import model.Coordinates;
import model.Direction;
import model.entities.Chest;
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

    private static final int NB_OF_BOSS_ROOMS = 1;
    private static final MonsterType BOSS_MONSTER = MonsterType.THE_BOSS;


    private int nbOfRooms;
    private int nbOfMonsterRooms;
    private int nbOfSimpleRooms;
    private List<Room> simpleRooms;
    private int nbOfChests;
    private boolean[][] visitedRooms;

    public LabyrinthGenerator(int nbOfRooms, int nbOfMonsterRooms, int nbOfChests) {
        this.nbOfChests = nbOfChests;
        this.nbOfRooms = ((int) Math.sqrt(nbOfRooms));
        visitedRooms = new boolean[this.nbOfRooms][this.nbOfRooms];

        int MINIMUM_NEEDED_SIMPLEROOM = 1;
        if (nbOfMonsterRooms + NB_OF_BOSS_ROOMS + MINIMUM_NEEDED_SIMPLEROOM > nbOfRooms)
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

    private void generateRoomsTypes(Room[][] rooms) {
        int nbOfSimpleRoomsRemaining = nbOfSimpleRooms;
        int nbOfMonsterRoomsRemaining = nbOfMonsterRooms;
        int nbOfRoomsRemaining;

        int bossX = (int) (Math.random() * nbOfRooms);
        int bossY = (int) (Math.random() * nbOfRooms);
        rooms[bossX][bossY] = new BossRoom(new Coordinates(bossX, bossY),new Monster(BOSS_MONSTER));

        for (int x = 0; x < nbOfRooms; x++) {
            for (int y = 0; y < nbOfRooms; y++) {

                nbOfRoomsRemaining = nbOfSimpleRoomsRemaining + nbOfMonsterRoomsRemaining;
                RoomType roomType = RoomType.getRandomRoomType();

                if (roomType == RoomType.SIMPLE_ROOM && rooms[x][y] == null && nbOfRoomsRemaining != 0) {
                    if (nbOfSimpleRoomsRemaining != 0) {
                        rooms[x][y] = new SimpleRoom(new Coordinates(x, y));
                        nbOfSimpleRoomsRemaining--;
                        simpleRooms.add(rooms[x][y]);
                    } else {
                        rooms[x][y] = new MonsterRoom(new Coordinates(x, y));
                        nbOfMonsterRoomsRemaining--;
                    }

                } else if (roomType == RoomType.MONSTER_ROOM && rooms[x][y] == null && nbOfRoomsRemaining != 0) {
                    if (nbOfMonsterRoomsRemaining != 0) {
                        rooms[x][y] = new MonsterRoom(new Coordinates(x, y));
                        nbOfMonsterRoomsRemaining--;
                    } else {
                        rooms[x][y] = new SimpleRoom(new Coordinates(x, y));
                        nbOfSimpleRoomsRemaining--;
                        simpleRooms.add(rooms[x][y]);
                    }
                }
            }
        }
    }

    private void generateOpenedWays(Room[][] rooms) {

        int x = (int) (random() * nbOfRooms);
        int y = (int) (random() * nbOfRooms);

        generateOpenedWaysRec(rooms,x,y);

        for (int i = 0; i <nbOfRooms; i++) {
            for (int j = 0; j < nbOfRooms; j++) {
                System.out.print("[" + visitedRooms[i][j] + "]");
            }
            System.out.println();
        }
    }

    private void generateOpenedWaysRec(Room[][] rooms, int x, int y) {
        visitedRooms[x][y] = true;
        List<Room> closeRooms = new ArrayList<>();
        if(!(y + 1 >= nbOfRooms)){
            closeRooms.add(rooms[x][y+1]);
        }
        if(!(y - 1 < 0 )){
            closeRooms.add(rooms[x][y-1]);
        }
        if(!(x + 1 >= nbOfRooms)){
            closeRooms.add(rooms[x+1][y]);
        }
        if(!(x - 1 < 0 )){
            closeRooms.add(rooms[x-1][y]);
        }
        Collections.shuffle(closeRooms); // pour éviter que les salles soit toujours générer dans le meme sens

        for (Room room:closeRooms) {
            int currentX = (int) room.getCoords().getX();
            int currentY = (int) room.getCoords().getY();
            if(!visitedRooms[currentX][currentY]){

                if(currentX == x - 1 && currentY == y){
                    rooms[x][y].addOpenedWay(Direction.LEFT);
                    rooms[x-1][y].addOpenedWay(Direction.LEFT.reverse());
                    generateOpenedWaysRec(rooms,currentX,currentY);

                }else if(currentX == x + 1 && currentY == y){
                    rooms[x][y].addOpenedWay(Direction.RIGHT);
                    rooms[x+1][y].addOpenedWay(Direction.RIGHT.reverse());
                    generateOpenedWaysRec(rooms,currentX,currentY);

                }else if(currentX == x && currentY == y - 1){
                    rooms[x][y].addOpenedWay(Direction.UP);
                    rooms[x][y-1].addOpenedWay(Direction.UP.reverse());
                    generateOpenedWaysRec(rooms,currentX,currentY);
                }else if(currentX == x && currentY == y + 1){
                    rooms[x][y].addOpenedWay(Direction.DOWN);
                    rooms[x][y+1].addOpenedWay(Direction.DOWN.reverse());
                    generateOpenedWaysRec(rooms,currentX,currentY);
                }

            }

        }
    }





    private void generateChest(Room[][] rooms){
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
