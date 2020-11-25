package model.items;

import javafx.scene.paint.Color;
import model.Direction;
import model.Game;
import model.RoomManager;
import model.entities.characters.players.Player;
import model.rooms.Room;

public class Key implements Item {
    private boolean finalKey;

    @Override
    public void use() {
        Game game = Game.getInstance();
        Player player = game.getPlayer();

        if (RoomManager.isNearFromDoor(player)){
            Direction doorToOpen = RoomManager.directionFromNearestDoor(player);
            game.roomManager().openDoor(doorToOpen);
            removeFromInventory();
        } else {
            game.showNotification("Approach a door to use the key");
        }

        usedAction();
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
