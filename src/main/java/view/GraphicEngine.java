package view;

import controller.App;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import model.Game;
import model.entities.Entity;
import model.rooms.Room;
import view.graphicalObjects.*;

import java.util.ArrayList;
import java.util.List;

public class GraphicEngine {
    private static GraphicEngine graphicEngine;
    private final Group root;
    private GRoom actualRoom;
    private final List<GEntity> guiEntities;
    private GNotification notification;
    private final GInventory inventory;
    private final GPauseMenu pauseMenu;

    public GraphicEngine(Group root) {
        graphicEngine = this;

        guiEntities = new ArrayList<>();

        inventory = new GInventory();
        inventory.render();

        this.root = root;

        showNotification(""); // To initialize

        pauseMenu = new GPauseMenu();
    }

    public static GraphicEngine getInstance() {
        return graphicEngine;
    }

    public void render() {
        Platform.runLater(() -> {
            List<GEntity> guiEntities = new ArrayList<>(this.guiEntities); // Avoid concurrent modification exception

            for (GEntity entity : guiEntities)
                entity.render();

            inventory.render();

            if (notification != null) {
                notification.render();

                if (notification.expired())
                    removeNotification();
            }
        });
    }

    public void addEntity(Entity entity) {
        Color colorOfEntity = entity.getColor();
        addEntity(entity, entity.getColor());
    }

    private void addEntity(Entity entity, Color color) {
        GEntity gEntity = new GEntity(entity, color);
        guiEntities.add(gEntity);

        Platform.runLater(() -> root.getChildren().addAll(gEntity.getFxModels()));
    }

    public void removeEntity(Entity entity) {
        for (GEntity gEntity : guiEntities) {
            if (gEntity.getEntity().equals(entity)) {
                removeEntity(gEntity);
                return;
            }
        }
    }

    private void removeEntity(GEntity gEntity) {
        if (guiEntities.contains(gEntity)) {
            guiEntities.remove(gEntity);
            Platform.runLater(() -> root.getChildren().removeAll(gEntity.getFxModels()));
        }
    }

    public void loadRoom(Room room) {
        GRoom guiRoom = new GRoom(room);
        guiRoom.render();

        Platform.runLater(() -> root.getChildren().clear());
        guiEntities.clear();

        Platform.runLater(() ->  {
            root.getChildren().addAll(guiRoom.getFxModels());
            root.getChildren().addAll(inventory.getFxModels());

            if (!notification.expired())
                root.getChildren().addAll(notification.getFxModels());
        });

        for (Entity entity : room.getEntities())
            addEntity(entity);

        addEntity(Game.getInstance().getPlayer());

        actualRoom = guiRoom;
    }

    public void showNotification(String message) {
        showNotification(message, App.DEFAULT_NOTIFICATION_DURATION);
    }

    public void showNotification(String message, int duration) {
        removeNotification();

        GNotification notification = new GNotification(message, duration);
        this.notification = notification;
        Platform.runLater(() -> {
            if (root.getChildren().containsAll(notification.getFxModels()))
                root.getChildren().removeAll(notification.getFxModels());

            root.getChildren().addAll(notification.getFxModels());
        });
    }

    void removeNotification() {
        GNotification notification = this.notification;

        if (notification != null)
            Platform.runLater(() -> root.getChildren().removeAll(notification.getFxModels()));
    }

    public void togglePauseMenu(boolean isPaused) {
        if (isPaused) {
            pauseMenu.render();
            Platform.runLater(() -> root.getChildren().addAll(pauseMenu.getFxModels()));
        }
        else
            Platform.runLater(() -> root.getChildren().removeAll(pauseMenu.getFxModels()));
    }
}
