package view.graphicalObjects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Coordinates;
import model.entities.Entity;
import model.entities.characters.Character;

import java.util.*;

public class GEntity implements GObject {
    private final Entity entity;
    private final Circle shape;
    private GHealthBar GHealthBar;

    public GEntity(Entity entity, Color color) {
        this.entity = entity;

        Coordinates coords = entity.getCoords();

        shape = new Circle(coords.getX(), coords.getY(), entity.getSize(), color);

        if (entity instanceof Character)
            GHealthBar = new GHealthBar((Character) entity);
    }

    @Override
    public List<Node> getFxModels() {
        if (GHealthBar == null)
            return new ArrayList<>(Collections.singletonList(shape));

        List<Node> nodes = new ArrayList<>();
        nodes.add(shape);
        nodes.addAll(GHealthBar.getFxModels());

        return nodes;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public void render() {
        Coordinates coords = entity.getCoords();

        shape.setCenterX(coords.getX());
        shape.setCenterY(coords.getY());

        if (GHealthBar != null) GHealthBar.render();

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
