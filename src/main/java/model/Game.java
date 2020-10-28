package model;

import controller.App;
import model.entities.Entity;
import model.entities.characters.Character;
import model.entities.characters.Player;
import model.entities.characters.monsters.Monster;
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
        rooms = DungeonGenerator.generate();

        loadRoom(rooms[0][0]);

        spawnPlayer();
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


            // CHANGEMENT DE SALLE



            MainGUI.getInstance().render();

            try {
                Thread.sleep(1000 / App.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public void nextRoom(Direction direction) {
        int x = (int) actualRoom.getCoords().getX();
        int y = (int) actualRoom.getCoords().getY();
        Room newRoom;

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
                throw new IllegalStateException("Unexpected value: " + direction);
        }

        loadRoom(newRoom);
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

    private void loadRoom(Room room) {
        actualRoom = room;
        MainGUI.getInstance().renderRoom(room);
        MainGUI.getInstance().updateEntities(actualRoom.getEntities());
    }

    private void spawnPlayer() {
        MainGUI.getInstance().addEntity(player);
    }

    public void gameOver() {
        // TODO : A implémenter
        System.out.println("GAME OVER");
        System.exit(0);
    }
}
