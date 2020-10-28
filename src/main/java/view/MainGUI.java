package view;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import model.entities.Entity;
import model.entities.characters.Character;
import model.rooms.Room;

import java.util.ArrayList;
import java.util.List;

public class MainGUI {
    private static MainGUI mainGUI;
    private final Group root;
    private GUIRoom actualRoom;

    private final List<GUIEntity> guiEntities;

    public MainGUI(Group root) {
        mainGUI = this;
        guiEntities = new ArrayList<>();
        this.root = root;
    }

    public static MainGUI getInstance() {
        return mainGUI;
    }

    public void render() {
        List<GUIEntity> guiEntities = new ArrayList<>(this.guiEntities); // Avoid concurrent modification exception

        for (GUIEntity entity : guiEntities)
            entity.render();
    }

    // Never called to add player
    public void updateEntities(List<Entity> entities) {
        Platform.runLater(() -> {
            List<GUIEntity> entitiesToRemove = new ArrayList<>(guiEntities);

            for (GUIEntity guiEntity : entitiesToRemove)
                removeEntity(guiEntity);
        });

        guiEntities.clear();

        for (Entity entity : entities)
            addEntity(entity);
    }

    public void addEntity(Entity entity) {
        if (entity instanceof Character)
            addEntity(entity, ((Character) entity).getColor());
        else
            addEntity(entity, Color.BLACK);
    }

    /*
    private void addEntity(Entity entity, String imgPath) {
        Platform.runLater(() -> {
            GUIEntity guiEntity = new GUIEntity(entity, imgPath);
            root.getChildren().add(guiEntity.getFxModel());
            guiEntities.add(guiEntity);
        });
    }
     */

    private void addEntity(Entity entity, Color color) {
        Platform.runLater(() -> {
            GUIEntity guiEntity = new GUIEntity(entity, color);
            root.getChildren().add(guiEntity.getFxModel());
            guiEntities.add(guiEntity);
        });
    }

    public void removeEntity(Entity entity) {
        for (GUIEntity guiEntity : guiEntities) {
            if (guiEntity.getEntity().equals(entity)) {
                removeEntity(guiEntity);
                return;
            }
        }
    }

    private void removeEntity(GUIEntity guiEntity) {
        if (guiEntities.contains(guiEntity)) {
            Platform.runLater(() -> root.getChildren().remove(guiEntity.getFxModel()));
        }
    }

    public void renderRoom(Room room) {
        GUIRoom guiRoom = new GUIRoom(room);
        guiRoom.render();

        Platform.runLater(() -> {
            if (actualRoom != null)
                root.getChildren().removeAll(actualRoom.getFxModels());

            root.getChildren().addAll(guiRoom.getFxModels());
        });

        actualRoom = guiRoom;
    }
}
