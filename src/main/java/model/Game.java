package model;

import controller.App;
import model.entities.Entity;
import model.entities.EntityFactory;
import model.entities.characters.Character;
import model.entities.characters.players.Player;
import model.entities.characters.monsters.Monster;
import model.entities.characters.players.PlayerType;
import model.generators.DungeonGenerator;
import model.generators.LabyrinthGenerator;
import sounds.observers.*;
import utils.Observable;
import view.GraphicEngine;

import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {
    private static Game game;
    private GraphicEngine graphicEngine;
    private RoomManager roomManager;
    private final Player player;
    private boolean isPaused = false;

    public Game(PlayerType playerType) {
        // INIT OBSERVERS
        Observable.initObservers();

        game = this;
        player = new EntityFactory().createPlayer(playerType);
    }

    @Override
    public void run() {
        graphicEngine = GraphicEngine.getInstance();

        // DungeonGenerator generator = new TestGenerator();
        DungeonGenerator generator = new LabyrinthGenerator(25, 15, 10);
        roomManager = new RoomManager(generator);

        Observable.notify(0, SoundGameObserver.getInstance());

        showNotification("Find and defeat the BOSS!\nOr you will die!\nBecause you are noob", 3000);

        graphicEngine.render(); // Premier render (initialisation)

        game.pause(); // Pour afficher les contrôles avant de commencer la partie

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

                graphicEngine.render();
            }

            try {
                Thread.sleep(1000 / App.FPS);
            } catch (InterruptedException ignored) {
                stopGame();
            }
        }

        stopGame();
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

    public List<Monster> getMonsters() {
        if (roomManager.actualRoom() == null) return new ArrayList<>();

        List<Monster> monsters = new ArrayList<>();
        List<Entity> entities = roomManager.actualRoom().getEntities();

        for (Entity entity : entities) {
            if (entity instanceof Monster)
                monsters.add((Monster) entity);
        }

        return monsters;
    }

    public void addEntity(Entity entity) {
        roomManager.actualRoom().getEntities().add(entity);
        GraphicEngine.getInstance().addEntity(entity);
        game.roomManager().reloadRoom();
    }

    public void deleteEntity(Entity entity) {
        System.out.println("Entity " + entity + " fainted");

        GraphicEngine.getInstance().removeEntity(entity);
        roomManager.actualRoom().getEntities().remove(entity);
    }

    public void gameOver() {
        System.out.println("GAME OVER");

        graphicEngine.showNotification("YOU DIED\nGAME OVER", 5000);
        graphicEngine.render(); // Pour afficher le sprite "game over" du personnage

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopGame();
    }

    public void showNotification(String message) {
        showNotification(message, App.DEFAULT_NOTIFICATION_DURATION);
    }

    public void showNotification(String message, int duration) {
        GraphicEngine.getInstance().showNotification(message, duration);
    }

    public void play() {
        isPaused = false;
        GraphicEngine.getInstance().togglePauseMenu(false);
        Observable.notify(3, SoundGameObserver.getInstance());
    }

    public void pause() {
        isPaused = true;
        GraphicEngine.getInstance().togglePauseMenu(true);
        Observable.notify(2, SoundGameObserver.getInstance());
    }

    public void stopGame() {
        System.exit(0);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public RoomManager roomManager() {
        return roomManager;
    }
}
