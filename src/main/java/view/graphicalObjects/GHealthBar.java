package view.graphicalObjects;

import controller.App;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import model.Coordinates;
import model.entities.characters.Character;

import java.util.Collections;
import java.util.List;

public class GHealthBar implements GObject {
    private final Character character;
    private final ProgressBar healthBar;

    public GHealthBar(Character character) {
        this.character = character;
        healthBar = new ProgressBar();

        render();
    }

    @Override
    public void render() {
        Coordinates charCoords = character.getCoords();

        healthBar.setLayoutX(charCoords.getX() - healthBar.getLayoutBounds().getWidth()/2);
        healthBar.setLayoutY(charCoords.getY() + App.HEALTH_BAR_MARGIN);

        healthBar.setProgress(((float) character.getHealthPoints()) / ((float) character.getMaxHealth()));
    }

    @Override
    public List<Node> getFxModels() {
        return Collections.singletonList(healthBar);
    }
}
