package view;

import controller.App;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Coordinates;
import model.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GUIEntity implements GUIObject {
    private Entity entity;
    private Image img;
    private Circle shape;

    /*
    public GUIEntity(Entity entity, String imgPath) {
        this.entity = entity;
        this.img = null;

        Coordinates coords = entity.getCoords();

        // TODO : Temporaire
        if (entity instanceof Player)
            shape = new Circle(coords.getX(), coords.getY(), ((Player) entity).getSize(), Color.GREEN);
        else if (entity instanceof Monster)
            shape = new Circle(coords.getX(), coords.getY(), App.PLAYER_SIZE, Color.RED);
    }

     */

    public GUIEntity(Entity entity, Color color) {
        this.entity = entity;
        this.img = null;

        Coordinates coords = entity.getCoords();
        shape = new Circle(coords.getX(), coords.getY(), entity.getSize(), color);
    }

    public Node getFxModel() {
        return shape;
    }

    @Override
    public List<Node> getFxModels() {
        return new ArrayList<>(Collections.singletonList(shape));
    }

    protected Entity getEntity() {
        return entity;
    }

    @Override
    public void render() {
        Coordinates coords = entity.getCoords();

        shape.setCenterX(coords.getX());
        shape.setCenterY(coords.getY());

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
