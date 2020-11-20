package model.entities.characters.players;

import controller.App;
import javafx.scene.paint.Color;
import model.*;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.characters.Character;
import model.entities.characters.monsters.Monster;
import model.items.Item;
import model.rooms.Room;
import sounds.observers.PlayerObserver;

import java.util.List;
import java.util.Set;

public class Player extends Character {

    public Player(String name, double size, int healthPoints, int strength, double speed, Color color) {
        super(
                new Coordinates(App.WIDTH/2, App.HEIGHT/2),
                name,
                size,
                healthPoints,
                strength,
                speed,
                color
        );

        addObserver(PlayerObserver.getInstance());
    }

    public Player(PlayerBuilder builder) {
        this(
                builder.getName(),
                builder.getSize(),
                builder.getHealthPoints(),
                builder.getStrength(),
                builder.getSpeed(),
                builder.getColor()
        );
    }

    public Player(PlayerType playerType) {
        this(playerType.getBuilder());
    }

    public void pickupItem() {
        Game game = Game.getInstance();
        Player player = game.getPlayer();

        List<Entity> closestEntities = game.closestEntities(this);

        for (Entity entity : closestEntities) {
            if (!(entity instanceof Item)) continue;

            player.getInventory().addItem((Item) entity);
        }
    }

    public void moveToDoor(Direction position) {
        switch (position) {
            case LEFT:
                coords.setX(App.WALL_SIZE + size);
                coords.setY(App.HEIGHT/2);
                break;
            case UP:
                coords.setX(App.WIDTH/2);
                coords.setY(App.WALL_SIZE + size);
                break;
            case RIGHT:
                coords.setX(App.WIDTH-App.WALL_SIZE - size);
                coords.setY(App.HEIGHT/2);
                break;
            case DOWN:
                coords.setX(App.WIDTH/2);
                coords.setY(App.HEIGHT-App.WALL_SIZE - size);
                break;
        }
    }

    @Override
    public void action() {
        // ENTITIES ACTION
        List<Entity> closestEntities = Game.getInstance().closestEntities(this);

        for (Entity entity : closestEntities) {
            if (entity instanceof Chest) {
                ((Chest) entity).openChest(); return;
            }
        }
    }

    @Override
    public void attack() {
        List<Monster> closestMonsters = Game.getInstance().getMonsters();

        for (Monster monster : closestMonsters) {
            Fight fight = new Fight(this, monster);
            fight.doAttack();
        }
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void deathAction() {
        notify(1);
        Game.getInstance().gameOver();
    }

    @Override
    public int getHealthPoints() {
        return healthPoints;
    }


    @Override
    public void move(Direction direction) {
        super.move(direction);

        // CHANGEMENT DE SALLE
        if (Room.isNearFromWay(this)){
            Game game = Game.getInstance();
            Set<Direction> ways = game.roomManager().actualRoom().getOpenedWays();
            Set<Direction> doorWays = game.roomManager().actualRoom().getDoorWays();
            Direction directionFromNearestWay = Room.directionFromNearestWay(this);

            if (ways.contains(directionFromNearestWay) && !doorWays.contains(directionFromNearestWay)) {
                if (this.lastDirection() == directionFromNearestWay)
                    game.roomManager().loadNextRoom(directionFromNearestWay);
            }
        }
    }
}
