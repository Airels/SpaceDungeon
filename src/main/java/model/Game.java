package model;

import controller.App;
import model.entities.Entity;
import model.entities.characters.Character;
import model.entities.characters.players.Player;
import model.entities.characters.monsters.Monster;
import model.generators.DungeonGenerator;
import model.generators.LabyrinthGenerator;
import sounds.*;
import sounds.observers.*;
import sounds.sound.SGame;
import sounds.sound.entity.character.SPlayer;
import view.GraphicEngine;

import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {
    private static Game game;
    private RoomManager roomManager;
    private final Player player;
    private boolean isPaused = false;

    public Game(Player player) {
        game = this;
        this.player = player;

        // INIT OBSERVERS
        new BossObserver();
        new ChestObserver();
        new MonsterObserver();
        new PlayerObserver();
        new RoomObserver();
    }

    @Override
    public void run() {
        // DungeonGenerator generator = new TestGenerator();
        DungeonGenerator generator = new LabyrinthGenerator(9, 2, 9);
        roomManager = new RoomManager(generator);

        showNotification("Find and defeat the BOSS!\nOr you will die!\nBecause you are noob", 3000);

        game.pause();

        SoundPlayer.infinitePlay(SGame.MUSIC_1);

        loop();
    }

    private void loop() {
        while (!interrupted()) {
            if (!isPaused) {
                // IA
                List<Entity> entities = roomManager.actualRoom().getEntities();
                for (Entity entity : entities) {
                    if (!(entity instanceof Monster)) continue;

                    Monster monster = (Monster) entity;
                    monster.getMonsterAI().process(monster);
                }

                GraphicEngine.getInstance().render();
            }

            try {
                Thread.sleep(1000 / App.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static Game getInstance() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> closestEntities(Character character) {
        if (roomManager.actualRoom() == null) return new ArrayList<>();

        List<Entity> closestEntities = new ArrayList<>();

        double charRange = character.getActionRange();
        Coordinates charCoords = character.getCoords();

        for (Entity entity : roomManager.actualRoom().getEntities()) {
            if (entity.equals(character)) continue;

            if (charCoords.getDistance(entity.getCoords()) <= charRange)
                closestEntities.add(entity);
        }

        return closestEntities;
    }

    public List<Monster> closestMonsters() {
        if (roomManager.actualRoom() == null) return new ArrayList<>();

        List<Monster> closestEntities = new ArrayList<>();

        double charRange = player.getActionRange();
        Coordinates charCoords = player.getCoords();

        for (Entity entity : roomManager.actualRoom().getEntities()) {
            if (!(entity instanceof Monster)) continue;

            if (charCoords.getDistance(entity.getCoords()) <= charRange)
                closestEntities.add((Monster) entity);
        }

        return closestEntities;
    }

    public void addEntity(Entity entity) {
        roomManager.actualRoom().getEntities().add(entity);
        GraphicEngine.getInstance().addEntity(entity);
    }

    public void deleteEntity(Entity entity) {
        System.out.println("Entity " + entity + " fainted");

        GraphicEngine.getInstance().removeEntity(entity);
        roomManager.actualRoom().getEntities().remove(entity);
    }

    public void gameOver() {
        // TODO : A implémenter
        System.out.println("GAME OVER");

        GraphicEngine.getInstance().showNotification("YOU DIED\nGAME OVER", 5000);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    public void showNotification(String message) {
        showNotification(message, App.DEFAULT_NOTIFICATION_DURATION);
    }

    public void showNotification(String message, int duration) {
        GraphicEngine.getInstance().showNotification(message, duration);
    }

    public void pause() {
        isPaused = true;
        GraphicEngine.getInstance().togglePauseMenu(true);
    }

    public void play() {
        isPaused = false;
        GraphicEngine.getInstance().togglePauseMenu(false);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public RoomManager roomManager() {
        return roomManager;
    }
}
