package model.items;

import javafx.scene.paint.Color;
import model.Direction;
import model.Game;
import model.entities.characters.Inventory;
import model.entities.characters.players.Player;
import model.rooms.Room;

public class Key implements Item {
    private boolean finalKey;

    @Override
    public void use() {
        Game game = Game.getInstance();
        Player player = game.getPlayer();

        if (Room.isNearFromDoor(player)){
            Direction doorToOpen = Room.directionFromNearestDoor(player);
            game.openDoor(doorToOpen);
            removeFromInventory();
        } else {
            game.showNotification("Approach a door to use the key");
        }
    }

    @Override
    public String name() {
        return "Key";
    }

    @Override
    public Color getColor() {
        return Color.GOLD;
    }
}
