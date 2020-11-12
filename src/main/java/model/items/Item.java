package model.items;

import javafx.scene.paint.Color;
import model.Game;

public interface Item {
    void use();
    String name();
    Color getColor();

    default void removeFromInventory() {
        Game.getInstance().getPlayer().getInventory().removeItem(this);
    }
}
