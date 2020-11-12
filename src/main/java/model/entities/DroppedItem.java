package model.entities;

import controller.App;
import model.Coordinates;
import model.items.Item;

public class DroppedItem extends Entity {
    private Item item;

    public DroppedItem(Coordinates coords, Item itemDropped) {
        super(coords, itemDropped.name(), App.DEFAULT_ENTITY_SIZE, itemDropped.getColor());
        item = itemDropped;
    }

    public Item getItem() {
        return item;
    }
}
