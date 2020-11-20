package model.entities;

import controller.App;
import javafx.scene.paint.Color;
import model.Coordinates;
import model.Game;
import model.entities.characters.players.Player;
import model.items.Item;
import sounds.observers.SoundChestObserver;

import java.util.Arrays;
import java.util.List;

public class Chest extends Entity {
    private final List<Item> items;

    public Chest(Item... items) {
        this(Arrays.asList(items));
    }

    public Chest(List<Item> items){
        // TODO : Coordonnées aléatoires à implémenter
        this(new Coordinates(App.WIDTH/2, App.HEIGHT/2), items);
    }

    public Chest(Coordinates coords, List<Item> items) {
        super(coords, "Chest", App.DEFAULT_ENTITY_SIZE, Color.BROWN);

        this.items = items;
        addObserver(SoundChestObserver.getInstance());
    }

    public void openChest() {
        System.out.println("Chest picked up");

        Game game = Game.getInstance();
        Player player = game.getPlayer();

        player.getInventory().addAll(items, true);

        game.addEntity(new OpenedChest(this.coords));
        game.deleteEntity(this);
        game.roomManager().reloadRoom();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You opened a chest, you got :");

        for (Item item : items)
            stringBuilder.append("\n- ").append(item.name());

        game.showNotification(stringBuilder.toString());

        notify(0);
    }
}
