package model.entities;

import model.items.Item;

public class DroppedItem extends Entity {
    private Item item;

    public DroppedItem(Item itemDropped) {
        item = itemDropped;
    }

    public Item getItem() {
        return item;
    }
}
