package model.entities;

import controller.App;
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
        this(new Coordinates(100 + Math.random() * (App.WIDTH-200), 100 + Math.random() * (App.HEIGHT-200)), items);
        // this(new Coordinates(App.WIDTH/2, App.HEIGHT/2), items);
    }

    public Chest(Coordinates coords, List<Item> items) {
        super(coords, "Chest", App.DEFAULT_ENTITY_SIZE);

        this.items = items;
        addObserver(SoundChestObserver.getInstance());
    }

    public void openChest() {
        System.out.println("Chest picked up");

        Player player = Game.getInstance().getPlayer();

        player.getInventory().addAll(items, true);

        createOpenedChestEntity();
        chestOpenedNotification();
    }

    private void createOpenedChestEntity() {
        new OpenedChest(this.coords).spawn();
        unspawn();
    }

    private void chestOpenedNotification() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You opened a chest, you got :");

        for (Item item : items)
            stringBuilder.append("\n- ").append(item.name());

        Game.getInstance().showNotification(stringBuilder.toString());

        notify(0);
    }
}
