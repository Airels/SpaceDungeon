package model.entities.characters;

import controller.App;
import javafx.scene.paint.Color;
import model.Coordinates;
import model.Fight;
import model.Game;
import model.Direction;
import model.entities.Entity;
import model.entities.characters.monsters.Monster;
import model.items.Item;
import model.rooms.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Player extends Character {

    public Player(PlayerType playerType) {
        this(playerType.healthPoints, playerType.strength, playerType.actionRange, playerType.speed, playerType.size, playerType.color);
    }

    public Player(int healthPoints, int strength, int actionRange, double speed, double size, Color color){
        this.name = "Player";
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
                coords.setX(App.WALL_SIZE);
                coords.setY(App.HEIGHT/2);
                break;
            case UP:
                coords.setX(App.WIDTH/2);
                coords.setY(App.WALL_SIZE);
                break;
            case RIGHT:
                coords.setX(App.WIDTH-App.WALL_SIZE);
                coords.setY(App.HEIGHT/2);
                break;
            case DOWN:
                coords.setX(App.WIDTH/2);
                coords.setY(App.HEIGHT-App.WALL_SIZE);
                break;
        }
    }

    @Override
    public void action() {

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
        if (Room.isNearFromDoor(this)){
            Game game = Game.getInstance();
            Set<Direction> doorWays = game.getActualRoom().getOpenedWays();

            if (doorWays.contains(Room.directionFromNearestDoor(this)))
                game.nextRoom(Room.directionFromNearestDoor(this));
        }
    }
}
