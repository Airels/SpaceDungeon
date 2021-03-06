package view.graphicalObjects;

import controller.App;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.Direction;
import model.rooms.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GRoom implements GObject {
    private final Room room;
    private final List<Node> walls;

    public GRoom(Room room) {
        this.room = room;
        walls = new ArrayList<>();
    }

    @Override
    public void render() {
        Rectangle rectangleBuffer;
        Set<Direction> openedWays = room.getOpenedWays();
        Set<Direction> waysWithDoor = room.getDoorWays();

        if (openedWays.contains(Direction.LEFT) || waysWithDoor.contains(Direction.LEFT)) {
            rectangleBuffer = new Rectangle(0, 0, App.WALL_SIZE, App.HEIGHT*0.40);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);

            rectangleBuffer = new Rectangle(0, App.HEIGHT*0.60, App.WALL_SIZE, App.HEIGHT*0.40);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);

            if (waysWithDoor.contains(Direction.LEFT)) {
                rectangleBuffer = new Rectangle(0, App.HEIGHT*0.40, App.WALL_SIZE, App.HEIGHT*0.20);
                rectangleBuffer.setFill(App.DOOR_COLOR);
                walls.add(rectangleBuffer);
            }
        } else {
            rectangleBuffer = new Rectangle(0, 0, App.WALL_SIZE, App.HEIGHT);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);
        }

        if (openedWays.contains(Direction.UP) || waysWithDoor.contains(Direction.UP)) {
            rectangleBuffer = new Rectangle(0, 0, App.WIDTH*0.40, App.WALL_SIZE);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);

            rectangleBuffer = new Rectangle(App.WIDTH*0.60, 0, App.WIDTH*0.40, App.WALL_SIZE);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);

            if (waysWithDoor.contains(Direction.UP)) {
                rectangleBuffer = new Rectangle(App.WIDTH*0.40, 0, App.WIDTH*0.20, App.WALL_SIZE);
                rectangleBuffer.setFill(App.DOOR_COLOR);
                walls.add(rectangleBuffer);
            }
        } else {
            rectangleBuffer = new Rectangle(0, 0, App.WIDTH, App.WALL_SIZE);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);
        }

        if (openedWays.contains(Direction.RIGHT) || waysWithDoor.contains(Direction.RIGHT)) {
            rectangleBuffer = new Rectangle(App.WIDTH-App.WALL_SIZE, 0, App.WALL_SIZE, App.HEIGHT*0.40);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);

            rectangleBuffer = new Rectangle(App.WIDTH-App.WALL_SIZE, App.HEIGHT*0.60, App.WALL_SIZE, App.HEIGHT*0.40);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);

            if (waysWithDoor.contains(Direction.RIGHT)) {
                rectangleBuffer = new Rectangle(App.WIDTH-App.WALL_SIZE, App.HEIGHT*0.40, App.WALL_SIZE, App.HEIGHT*0.20);
                rectangleBuffer.setFill(App.DOOR_COLOR);
                walls.add(rectangleBuffer);
            }
        } else {
            rectangleBuffer = new Rectangle(App.WIDTH-App.WALL_SIZE, 0, App.WALL_SIZE, App.HEIGHT);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);
        }

        if (openedWays.contains(Direction.DOWN)) {
            rectangleBuffer = new Rectangle(0, App.HEIGHT-App.WALL_SIZE, App.WIDTH*0.40, App.WALL_SIZE);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);

            rectangleBuffer = new Rectangle(App.WIDTH*0.60, App.HEIGHT-App.WALL_SIZE, App.WIDTH*0.40, App.WALL_SIZE);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);

            if (waysWithDoor.contains(Direction.DOWN)) {
                rectangleBuffer = new Rectangle(App.WIDTH*0.40, App.HEIGHT-App.WALL_SIZE, App.WIDTH*0.20, App.WALL_SIZE);
                rectangleBuffer.setFill(App.DOOR_COLOR);
                walls.add(rectangleBuffer);
            }
        } else {
            rectangleBuffer = new Rectangle(0, App.HEIGHT-App.WALL_SIZE, App.WIDTH, App.WALL_SIZE);
            rectangleBuffer.setFill(App.WALL_COLOR);
            walls.add(rectangleBuffer);
        }
    }

    @Override
    public List<Node> getFxNodes() {
        return walls;
    }
}
