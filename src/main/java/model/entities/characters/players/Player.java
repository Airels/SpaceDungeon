package model.entities.characters.players;

import controller.App;
import javafx.scene.paint.Color;
import model.Coordinates;
import model.Fight;
import model.Game;
import model.Direction;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.characters.Character;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterBuilder;
import model.items.Item;
import model.items.Key;
import model.rooms.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Player extends Character {

    public Player(String name, int healthPoints, int strength, int actionRange, double speed, double size, Color color){
        this.name = name;
        this.healthPoints = healthPoints;
        this.maxHealth = healthPoints;
        this.strength = strength;
        this.actionRange = actionRange;
        this.speed = speed;
        this.inventory = new ArrayList<>();
        this.coords = new Coordinates(App.WIDTH/2, App.HEIGHT/2);
        this.size = size;
        this.color = color;
    }

    public Player(PlayerBuilder builder) {
        this(
                builder.getName(),
                builder.getHealthPoints(),
                builder.getStrength(),
                builder.getActionRange(),
                builder.getSpeed(),
                builder.getSize(),
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

            player.getInventory().add((Item) entity);
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

        // DOORS ACTION
        if (Room.isNearFromDoor(this)){
            Game game = Game.getInstance();
            Room room = game.getActualRoom();
            boolean hasKey = false;

            for (Item item : inventory) {
                if (item instanceof Key) {
                    inventory.remove(item);
                    hasKey = true;
                    break;
                }
            }

            if (hasKey) {
                Direction doorToOpen = Room.directionFromNearestDoor(this);
                game.openDoor(doorToOpen);
            } else {
                game.showNotification("You must get a key to open this door !");
            }
        }
    }

    @Override
    public void attack() {
        List<Monster> closestMonsters = Game.getInstance().closestMonsters();

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
            Set<Direction> ways = game.getActualRoom().getOpenedWays();
            Set<Direction> doorWays = game.getActualRoom().getDoorWays();
            Direction directionFromNearestWay = Room.directionFromNearestWay(this);

            if (ways.contains(directionFromNearestWay) && !doorWays.contains(directionFromNearestWay))
                game.loadNextRoom(directionFromNearestWay);
        }
    }
}
