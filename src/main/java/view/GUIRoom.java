package view;

import controller.App;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.Direction;
import model.rooms.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GUIRoom implements GUIObject {
    private final Room room;
    private final List<Node> rectangles;

    GUIRoom(Room room) {
        this.room = room;
        rectangles = new ArrayList<>();
    }

    @Override
    public void render() {
        Rectangle rectangleBuffer;
        Set<Direction> openedWays = room.getOpenedWays();

        if (openedWays.contains(Direction.LEFT)) {
            rectangleBuffer = new Rectangle(0, 0, App.WALL_SIZE, App.HEIGHT/14*5);
            rectangleBuffer.setFill(App.WALL_COLOR);
            rectangles.add(rectangleBuffer);

            rectangleBuffer = new Rectangle(0, App.HEIGHT/14*9, App.WALL_SIZE, App.HEIGHT/10*5);
        } else {
            rectangleBuffer = new Rectangle(0, 0, App.WALL_SIZE, App.HEIGHT);
        }
        rectangleBuffer.setFill(App.WALL_COLOR);
        rectangles.add(rectangleBuffer);

        if (openedWays.contains(Direction.UP)) {
            rectangleBuffer = new Rectangle(0, 0, App.WIDTH/14*5, App.WALL_SIZE);
            rectangleBuffer.setFill(App.WALL_COLOR);
            rectangles.add(rectangleBuffer);

            rectangleBuffer = new Rectangle(App.WIDTH/14*9, 0, App.WIDTH/14*5, App.WALL_SIZE);
        } else {
            rectangleBuffer = new Rectangle(0, 0, App.WIDTH, App.WALL_SIZE);
        }
        rectangleBuffer.setFill(App.WALL_COLOR);
        rectangles.add(rectangleBuffer);

        if (openedWays.contains(Direction.RIGHT)) {
            rectangleBuffer = new Rectangle(App.WIDTH-App.WALL_SIZE, 0, App.WALL_SIZE, App.HEIGHT/14*5);
            rectangleBuffer.setFill(App.WALL_COLOR);
            rectangles.add(rectangleBuffer);

            rectangleBuffer = new Rectangle(App.WIDTH-App.WALL_SIZE, App.HEIGHT/14*9, App.WALL_SIZE, App.HEIGHT/14*5);
        } else {
            rectangleBuffer = new Rectangle(App.WIDTH-App.WALL_SIZE, 0, App.WALL_SIZE, App.HEIGHT);
        }
        rectangleBuffer.setFill(App.WALL_COLOR);
        rectangles.add(rectangleBuffer);

        if (openedWays.contains(Direction.DOWN)) {
            rectangleBuffer = new Rectangle(0, App.HEIGHT-App.WALL_SIZE, App.WIDTH/14*5, App.WALL_SIZE);
            rectangleBuffer.setFill(App.WALL_COLOR);
            rectangles.add(rectangleBuffer);

            rectangleBuffer = new Rectangle(App.WIDTH/14*9, App.HEIGHT-App.WALL_SIZE, App.WIDTH/14*5, App.WALL_SIZE);
        } else {
            rectangleBuffer = new Rectangle(0, App.HEIGHT-App.WALL_SIZE, App.WIDTH, App.WALL_SIZE);
        }
        rectangleBuffer.setFill(App.WALL_COLOR);
        rectangles.add(rectangleBuffer);
    }

    @Override
    public Node getFxModel() {
        throw new IllegalCallerException("There is multiple fx nodes, call getFxModels() instead");
    }

    @Override
    public List<Node> getFxModels() {
        return rectangles;
    }

    protected Room getRoom() {
        return room;
    }
}
