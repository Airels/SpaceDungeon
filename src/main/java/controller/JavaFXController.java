package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Direction;
import model.entities.characters.Player;

public class JavaFXController {

    public static EventHandler<? super KeyEvent> getKeyEventHandler(Player player) {
        return (EventHandler<KeyEvent>) event -> {
            switch (event.getCode()) {
                case Z: case W: case UP:    player.move(Direction.UP);    break;
                case Q: case A: case LEFT:  player.move(Direction.LEFT);  break;
                case S:         case DOWN:  player.move(Direction.DOWN);  break;
                case D:         case RIGHT: player.move(Direction.RIGHT); break;
                case E: player.action(); break;
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
