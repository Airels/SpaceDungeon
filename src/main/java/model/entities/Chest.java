package model.entities;

import controller.App;
import model.Coordinates;
import model.Game;
import model.entities.characters.players.Player;
import model.items.Item;

import java.util.Arrays;
import java.util.List;

public class Chest extends Entity {
    private List<Item> items;

    public Chest(Item... items) {
        this(Arrays.asList(items));
    }

    public Chest(List<Item> items){
        // TODO : Coordonnées aléatoires à implémenter
        this(new Coordinates(App.WIDTH/2, App.HEIGHT/2), items);
    }

    public Chest(Coordinates coords, List<Item> items) {
        this.coords = coords;
        this.items = items;
        this.name = "Chest";
    }

    public void openChest(){
        System.out.println("Chest picked up");

        Game game = Game.getInstance();
        Player player = game.getPlayer();

        player.getInventory().addAll(items, true);

        game.deleteEntity(this);
        game.reloadRoom();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You opened a chest, you got :");

        for (Item item : items)
            stringBuilder.append("\n- ").append(item.name());

        game.showNotification(stringBuilder.toString());
    }
}
