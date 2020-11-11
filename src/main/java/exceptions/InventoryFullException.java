package exceptions;

import model.entities.characters.Inventory;

public class InventoryFullException extends RuntimeException {
    public InventoryFullException(String msg) {
        super(msg);
    }
}
