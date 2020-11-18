package view.graphicalObjects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Coordinates;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.OpenedChest;
import model.entities.characters.Character;
import view.assetsLoader.AssetEntityLoader;

import java.util.*;

public class GEntity implements GObject {
    private final Entity entity;
    private final Rectangle shape;
    private GHealthBar gHealthBar;

    public GEntity(Entity entity, Color color) {
        this.entity = entity;

        Coordinates coords = entity.getCoords();

        // shape = new Circle(coords.getX(), coords.getY(), entity.getSize(), color);
        shape = new Rectangle(coords.getX() - (entity.getSize()/2), coords.getY() - (entity.getSize()/2), entity.getSize(), entity.getSize());
        shape.setFill(color);

        if (entity instanceof Chest || entity instanceof OpenedChest) {
            Image image = AssetEntityLoader.loadEntity(entity);
            shape.setFill(new ImagePattern(image));
        }

        if (entity instanceof Character)
            gHealthBar = new GHealthBar((Character) entity);
    }

    @Override
    public List<Node> getFxModels() {
        if (gHealthBar == null)
            return new ArrayList<>(Collections.singletonList(shape));

        List<Node> nodes = new ArrayList<>();
        nodes.add(shape);
        nodes.addAll(gHealthBar.getFxModels());

        return nodes;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public void render() {
        Coordinates coords = entity.getCoords();

        shape.setX(coords.getX() - (entity.getSize()/2));
        shape.setY(coords.getY() - (entity.getSize()/2));

        if (gHealthBar != null) gHealthBar.render();

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
