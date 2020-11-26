package view.graphicalObjects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Coordinates;
import model.entities.Entity;
import model.entities.characters.Character;
import model.entities.characters.players.Player;
import view.assetsLoader.entities.AssetEntity;
import view.assetsLoader.entities.players.AssetPlayer;

import java.util.*;

public class GEntity implements GObject {
    private final Entity entity;
    private final boolean isCharacter;
    private final Node shape;
    private GHealthBar gHealthBar;

    public GEntity(Entity entity) {
        this.entity = entity;

        isCharacter = (entity instanceof Character);

        Image image = new AssetEntity().load(entity);

        if (image != null) {
            shape = new ImageView(image);
        } else {
            shape = new Rectangle();
            Rectangle rect = (Rectangle) shape;
            rect.setWidth(entity.getSize());
            rect.setHeight(entity.getSize());
            rect.setFill(Color.RED);
        }

        if (entity instanceof Character)
            gHealthBar = new GHealthBar((Character) entity);
    }

    @Override
    public List<Node> getFxNodes() {
        if (gHealthBar == null)
            return new ArrayList<>(Collections.singletonList(shape));

        List<Node> nodes = new ArrayList<>();
        nodes.add(shape);
        nodes.addAll(gHealthBar.getFxNodes());

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

        if (isCharacter) {
            Character chara = (Character) entity;

            switch (chara.lastDirection()) {
                case LEFT:
                    shape.setScaleX(1);
                    break;
                case RIGHT:
                    shape.setScaleX(-1);
            }
        }

        if (gHealthBar != null) gHealthBar.render();

        if (entity instanceof Player && shape instanceof ImageView && ((Player) entity).isDead()) {
            AssetPlayer assetPlayer = new AssetPlayer();
            ImageView img = (ImageView) shape;
            img.setImage(assetPlayer.load(false));
        }

        // Tentative d'effet de fluidité mais bugs d'affichages avec JFX
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
