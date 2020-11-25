package controller;

import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Direction;
import model.Game;
import model.GameObserver;
import model.Observable;
import model.entities.characters.players.Player;
import model.entities.characters.players.PlayerObserver;
import model.items.Item;

public class JavaFXController {

    public static EventHandler<? super KeyEvent> getKeyEventHandler(GameObserver gameObserver, PlayerObserver playerObserver) {
        return (EventHandler<KeyEvent>) event -> {
            switch (event.getCode()) {
                case ESCAPE: gameObserver.notify(0); break;

                case Z: case W: case UP:    playerObserver.notify(10);    break;
                case Q: case A: case LEFT:  playerObserver.notify(11);  break;
                case S:         case DOWN:  playerObserver.notify(12);  break;
                case D:         case RIGHT: playerObserver.notify(13); break;
                case E: playerObserver.notify(15); break;

                case DIGIT1:
                    playerObserver.notify(0);
                    break;
                case DIGIT2:
                    playerObserver.notify(1);
                    break;
                case DIGIT3:
                    playerObserver.notify(2);
                    break;
                case DIGIT4:
                    playerObserver.notify(3);
                    break;
                case DIGIT5:
                    playerObserver.notify(4);
                    break;
                case DIGIT6:
                    playerObserver.notify(5);
                    break;
                case DIGIT7:
                    playerObserver.notify(6);
                    break;
                case DIGIT8:
                    playerObserver.notify(7);
                    break;
                case DIGIT9:
                    playerObserver.notify(8);
                    break;
            }
        };
    }

    public static EventHandler<? super MouseEvent> getMousePressedEventHandler(PlayerObserver playerObserver) {
        return (EventHandler<MouseEvent>) event -> {
            switch (event.getButton()) {
                case PRIMARY:
                    playerObserver.notify(14);
                    break;
                case SECONDARY:
                    playerObserver.notify(15);
            }
        };
    }
}
