package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Direction;
import model.Game;
import model.entities.characters.Inventory;
import model.entities.characters.players.Player;
import model.items.Item;

public class JavaFXController {

    public static EventHandler<? super KeyEvent> getKeyEventHandler(Player player) {
        return (EventHandler<KeyEvent>) event -> {
            Item item;

            switch (event.getCode()) {
                case Z: case W: case UP:    player.move(Direction.UP);    break;
                case Q: case A: case LEFT:  player.move(Direction.LEFT);  break;
                case S:         case DOWN:  player.move(Direction.DOWN);  break;
                case D:         case RIGHT: player.move(Direction.RIGHT); break;
                case E: player.action(); break;

                case DIGIT1:
                    item = player.getInventory().getItem(0);
                    if (item != null) item.action();
                    break;
                case DIGIT2:
                    item = player.getInventory().getItem(1);
                    if (item != null) item.action();
                    break;
                case DIGIT3:
                    item = player.getInventory().getItem(2);
                    if (item != null) item.action();
                    break;
                case DIGIT4:
                    item = player.getInventory().getItem(3);
                    if (item != null) item.action();
                    break;
                case DIGIT5:
                    item = player.getInventory().getItem(4);
                    if (item != null) item.action();
                    break;
                case DIGIT6:
                    item = player.getInventory().getItem(5);
                    if (item != null) item.action();
                    break;
                case DIGIT7:
                    item = player.getInventory().getItem(6);
                    if (item != null) item.action();
                    break;
                case DIGIT8:
                    item = player.getInventory().getItem(7);
                    if (item != null) item.action();
                    break;
                case DIGIT9:
                    item = player.getInventory().getItem(8);
                    if (item != null) item.action();
                    break;
            }
        };
    }

    public static EventHandler<? super MouseEvent> getMousePressedEventHandler(Player player) {
        return (EventHandler<MouseEvent>) event -> {
            switch (event.getButton()) {
                case PRIMARY:
                    player.attack();
                    break;
                case SECONDARY:
                    player.action();
            }
        };
    }
}
