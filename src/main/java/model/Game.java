package model;

import controller.App;
import exceptions.RoomNotGeneratedException;
import model.entities.Entity;
import model.entities.characters.Character;
import model.entities.characters.players.Player;
import model.entities.characters.monsters.Monster;
import model.generators.DungeonGenerator;
import model.generators.LabyrinthGenerator;
import model.rooms.BossRoom;
import model.rooms.Room;
import view.MainGUI;

import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {
    private static Game game;

    private Room[][] rooms;
    private final Player player;
    private Room actualRoom;
    private long lastProcessedAI;
    private boolean canProcessAI;

    public Game(Player player) {
        game = this;
        this.player = player;
        lastProcessedAI = System.currentTimeMillis();
        canProcessAI = false;
    }

    @Override
    public void run() {
        // DungeonGenerator generator = new BasicGenerator(3, 5, 1);
        DungeonGenerator generator = new LabyrinthGenerator(9, 2);
        // DungeonGenerator generator = new TestGenerator();
        rooms = generator.generate();

        loadRoom(generator.getSpawnRoom());

        MainGUI.getInstance().showNotification("Find and defeat the BOSS!\nOr you will die!\nBecause you are noob", 3000);

        loop();
    }

    private void loop() {
        while (true) {
            // IA
            if (System.currentTimeMillis() - lastProcessedAI > App.MONSTER_AI_LATENCY_MS) {
                lastProcessedAI = System.currentTimeMillis();
                canProcessAI = true;
            }

            for (Entity entity : actualRoom.getEntities()) {
                if (!(entity instanceof Monster)) continue;

                if (canProcessAI) {
                    Monster monster = (Monster) entity;
                    monster.getMonsterAI().process(monster);
                }
            }

            if (canProcessAI) canProcessAI = false;


            MainGUI.getInstance().render();

            try {
                Thread.sleep(1000 / App.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public Room getActualRoom() {
        return actualRoom;
    }

    public Room getNextRoom(Direction direction) {
        int x = (int) actualRoom.getCoords().getX();
        int y = (int) actualRoom.getCoords().getY();
        Room newRoom;

        try {
            switch (direction) {
                case UP:
                    newRoom = rooms[x][y-1];
                    break;
                case RIGHT:
                    newRoom = rooms[x+1][y];
                    break;
                case DOWN:
                    newRoom = rooms[x][y+1];
                    break;
                case LEFT:
                    newRoom = rooms[x-1][y];
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + direction);
            }

            return newRoom;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.print("Index Out Of Bounds: Did you assigned coordinates correctly ? ");
            System.err.println("Actual coords: " + x + ", " + y + " (might be wrong, check your generator)");
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public void loadNextRoom(Direction direction) {
        switch (direction) {
            case UP:
                player.moveToDoor(Direction.DOWN);
                break;
            case RIGHT:
                player.moveToDoor(Direction.LEFT);
                break;
            case DOWN:
                player.moveToDoor(Direction.UP);
                break;
            case LEFT:
                player.moveToDoor(Direction.RIGHT);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + direction);
        }

        loadRoom(getNextRoom(direction));
    }

    public static Game getInstance() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> closestEntities(Character character) {
        List<Entity> closestEntities = new ArrayList<>();

        double charRange = character.getActionRange();
        Coordinates charCoords = character.getCoords();

        for (Entity entity : actualRoom.getEntities()) {
            if (entity.equals(character)) continue;

            if (charCoords.getDistance(entity.getCoords()) <= charRange)
                closestEntities.add(entity);
        }

        return closestEntities;
    }

    public List<Monster> closestMonsters() {
        List<Monster> closestEntities = new ArrayList<>();

        double charRange = player.getActionRange();
        Coordinates charCoords = player.getCoords();

        for (Entity entity : actualRoom.getEntities()) {
            if (!(entity instanceof Monster)) continue;

            if (charCoords.getDistance(entity.getCoords()) <= charRange)
                closestEntities.add((Monster) entity);
        }

        return closestEntities;
    }

    public void addEntity(Entity entity) {
        actualRoom.getEntities().add(entity);
        MainGUI.getInstance().addEntity(entity);
    }

    public void deleteEntity(Entity entity) {
        System.out.println("Entity " + entity + " fainted");

        MainGUI.getInstance().removeEntity(entity);
        actualRoom.getEntities().remove(entity);
    }

    public void reloadRoom() {
        loadRoom(actualRoom);
    }

    private void loadRoom(Room room) {
        if (room == null) throw new RoomNotGeneratedException("The room you try to load don't exist");

        actualRoom = room;
        MainGUI.getInstance().loadRoom(room);
        room.loadedEvent();
    }

    public void gameOver() {
        // TODO : A implémenter
        System.out.println("GAME OVER");

        MainGUI.getInstance().showNotification("YOU DIED\nGAME OVER", 5000);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    public void openDoor(Direction direction) {
        Room nextRoom = getNextRoom(direction);

        actualRoom.removeDoorWay(direction);
        nextRoom.removeDoorWay(direction.reverse());

        reloadRoom();

        showNotification("You opened a door!");
    }

    public void showNotification(String message) {
        showNotification(message, App.DEFAULT_NOTIFICATION_DURATION);
    }

    public void showNotification(String message, int duration) {
        MainGUI.getInstance().showNotification(message, duration);
    }
}
