package model.entities;

import controller.App;
import model.Coordinates;
import model.items.Item;

public class DroppedItem extends Entity {
    private final Item item;

    public DroppedItem(Coordinates coords, Item itemDropped) {
        super(coords, itemDropped.name(), App.DEFAULT_ENTITY_SIZE);
        item = itemDropped;
    }

    public Item getItem() {
        return item;
    }
}
