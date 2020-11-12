package view;

import controller.App;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import model.Game;
import model.entities.Chest;
import model.entities.DroppedItem;
import model.entities.Entity;
import model.entities.characters.Character;
import model.items.Item;
import model.items.Key;
import model.rooms.Room;

import java.util.ArrayList;
import java.util.List;

public class MainGUI {
    private static MainGUI mainGUI;
    private final Group root;
    private GUIRoom actualRoom;
    private final List<GUIEntity> guiEntities;
    private GUINotification notification;
    private final GUIInventory inventory;

    public MainGUI(Group root) {
        mainGUI = this;
        guiEntities = new ArrayList<>();
        inventory = new GUIInventory();
        this.root = root;

        showNotification(""); // To initialize
    }

    public static MainGUI getInstance() {
        return mainGUI;
    }

    public void render() {
        List<GUIEntity> guiEntities = new ArrayList<>(this.guiEntities); // Avoid concurrent modification exception

        for (GUIEntity entity : guiEntities)
            entity.render();

        inventory.render();

        if (notification != null) {
            notification.render();

            if (notification.expired())
                removeNotification();
        }
    }

    public void addEntity(Entity entity) {
        Color colorOfEntity = entity.getColor();
        addEntity(entity, entity.getColor());
    }

    private void addEntity(Entity entity, Color color) {
        GUIEntity guiEntity = new GUIEntity(entity, color);
        guiEntities.add(guiEntity);

        Platform.runLater(() -> root.getChildren().addAll(guiEntity.getFxModels()));
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
            guiEntities.remove(guiEntity);
            Platform.runLater(() -> root.getChildren().removeAll(guiEntity.getFxModels()));
        }
    }

    public void loadRoom(Room room) {
        GUIRoom guiRoom = new GUIRoom(room);
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

        GUINotification notification = new GUINotification(message, duration);
        this.notification = notification;
        Platform.runLater(() -> {
            if (root.getChildren().containsAll(notification.getFxModels()))
                root.getChildren().removeAll(notification.getFxModels());

            root.getChildren().addAll(notification.getFxModels());
        });
    }

    void removeNotification() {
        GUINotification notification = this.notification;

        if (notification != null)
            Platform.runLater(() -> root.getChildren().removeAll(notification.getFxModels()));
    }
}
