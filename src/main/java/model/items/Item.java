package model.items;

import model.Game;

public interface Item {
    void use();
    String name();

    default void removeFromInventory() {
        Game.getInstance().getPlayer().getInventory().removeItem(this);
    }
}
