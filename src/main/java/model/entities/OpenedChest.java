package model.entities;

import controller.App;
import model.Coordinates;

public class OpenedChest extends Entity {
    public OpenedChest(Coordinates coords) {
        super(coords, "Opened Chest", App.DEFAULT_ENTITY_SIZE);
    }
}
