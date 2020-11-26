package model.items;

import model.Game;
import utils.Observable;
import sounds.observers.SoundItemObserver;

public interface Item {
    void use();
    String name();

    default void removeFromInventory() {
        Game.getInstance().getPlayer().getInventory().removeItem(this);
    }

    default void usedAction() {
        Observable.notify(0, SoundItemObserver.getInstance());
        Game.getInstance().showNotification("You used " + this.name());
    }
}
