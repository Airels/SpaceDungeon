package model.entities;

import model.Game;
import model.entities.characters.Player;
import model.items.Item;

import java.util.List;

public class Chest extends Entity {
    private List<Item> items;

    public Chest(List<Item> items){
        this.items = items;
    }

    public void openChest(){
        Game game = Game.getInstance();
        Player player = game.getPlayer();

        player.getInventory().addAll(items);

    }

}
