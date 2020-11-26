package model.entities.characters.players;

import controller.App;
import model.*;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.characters.Character;
import model.entities.characters.monsters.Monster;
import model.items.Item;
import sounds.observers.SoundPlayerObserver;

import java.util.List;

public class Player extends Character {

    public Player(String name, double size, int healthPoints, int strength, double speed) {
        super(
                new Coordinates(App.WIDTH/2, App.HEIGHT/2),
                name,
                size,
                healthPoints,
                strength,
                speed
        );

        addObserver(SoundPlayerObserver.getInstance());
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
    public void deathAction() {
        notify(1);
        Game.getInstance().gameOver();
    }

    @Override
    public void move(Direction direction) {
        super.move(direction);

        RoomManager.changeRoomTrigger(this);
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

    public void useItem(int index) {
        Item item = getInventory().getItem(index);
        if (item != null) item.use();
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
}
