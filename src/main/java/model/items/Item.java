package model.items;

import javafx.scene.paint.Color;
import model.Game;
import model.Observable;
import sounds.observers.SoundItemObserver;

public interface Item {
    void use();
    String name();
    Color getColor();

    default void removeFromInventory() {
        Game.getInstance().getPlayer().getInventory().removeItem(this);
    }

    default void usedAction() {
        Observable.notify(0, SoundItemObserver.getInstance());
        Game.getInstance().showNotification("You used " + this.name());
    }
}
