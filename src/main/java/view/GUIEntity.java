package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Coordinates;
import model.entities.Entity;
import model.entities.characters.Character;

import java.util.*;

class GUIEntity implements GUIObject {
    private final Entity entity;
    private final Circle shape;
    private GUIHealthBar GUIHealthBar;

    GUIEntity(Entity entity, Color color) {
        this.entity = entity;

        Coordinates coords = entity.getCoords();

        shape = new Circle(coords.getX(), coords.getY(), entity.getSize(), color);

        if (entity instanceof Character)
            GUIHealthBar = new GUIHealthBar((Character) entity);
    }

    @Override
    public List<Node> getFxModels() {
        if (GUIHealthBar == null)
            return new ArrayList<>(Collections.singletonList(shape));

        List<Node> nodes = new ArrayList<>();
        nodes.add(shape);
        nodes.addAll(GUIHealthBar.getFxModels());

        return nodes;
    }

    protected Entity getEntity() {
        return entity;
    }

    @Override
    public void render() {
        Coordinates coords = entity.getCoords();

        shape.setCenterX(coords.getX());
        shape.setCenterY(coords.getY());

        if (GUIHealthBar != null) GUIHealthBar.render();

        // TODO : Effet de fluidité à corriger plus tard
        /*
        double oldX = shape.getCenterX(),
                oldY = shape.getCenterY(),
                newX = coords.getX(),
                newY = coords.getY(),
                deltaX = newX - oldX,
                deltaY = newY - oldY;

        for (float i = 0; i <= 1; i += 6 / (double) App.FPS) {
            shape.setCenterX(oldX + deltaX * i);
            shape.setCenterY(oldY + deltaY * i);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                shape.setCenterX(newX);
                shape.setCenterY(newY);
            }
        }

         */
    }
}
