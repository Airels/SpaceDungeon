package model.entities;

import javafx.scene.paint.Color;
import model.Coordinates;

public class Trap extends Entity {
    private int damages;

    public Trap(Coordinates coords, String name, double size, Color color) {
        super(coords, name, size);
    }

    public void action() {

    }
}
