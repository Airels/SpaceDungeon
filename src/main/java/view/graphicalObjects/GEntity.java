package view.graphicalObjects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Coordinates;
import model.Direction;
import model.entities.Chest;
import model.entities.DroppedItem;
import model.entities.Entity;
import model.entities.OpenedChest;
import model.entities.characters.Character;
import model.entities.characters.monsters.Monster;
import model.entities.characters.players.Player;
import model.entities.characters.players.PlayerType;
import view.assetsLoader.AssetEntityLoader;
import view.assetsLoader.AssetItemsLoader;
import view.assetsLoader.AssetMonsterLoader;
import view.assetsLoader.AssetPlayerLoader;

import java.util.*;

public class GEntity implements GObject {
    private final Entity entity;
    private final boolean isCharacter;
    private final Node shape;
    private GHealthBar gHealthBar;

    public GEntity(Entity entity, Color color) {
        this.entity = entity;

        isCharacter = (entity instanceof Character);

        Coordinates coords = entity.getCoords();

        Image image = AssetEntityLoader.loadEntity(entity);

        if (image != null) {
            shape = new ImageView(image);

            // shape.setFill(new ImagePattern(image));
            // shape.setFill(new ImagePattern(view.getImage()));
        } else {
            // shape = new Rectangle(coords.getX() - (entity.getSize()/2), coords.getY() - (entity.getSize()/2), entity.getSize(), entity.getSize());
            shape = new Rectangle();
            Rectangle rect = (Rectangle) shape;
            rect.setWidth(entity.getSize());
            rect.setHeight(entity.getSize());
            rect.setFill(color);
        }

        shape.setLayoutX(coords.getX() - (entity.getSize()/2));
        shape.setLayoutY(coords.getY() - (entity.getSize()/2));

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

        shape.setLayoutX(coords.getX() - (entity.getSize()/2));
        shape.setLayoutY(coords.getY() - (entity.getSize()/2));

        if (isCharacter && ((Character) entity).lastDirection() == Direction.RIGHT)
            shape.setScaleX(-1);
        else
            shape.setScaleX(1);

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
