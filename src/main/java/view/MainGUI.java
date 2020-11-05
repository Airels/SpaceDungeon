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

        if (notification != null) {
            notification.render();

            if (notification.expired())
                removeNotification();
        }
    }

    // Never called to add player
    /*
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

     */

    /*
    private void addEntity(Entity entity, String imgPath) {
        Platform.runLater(() -> {
            GUIEntity guiEntity = new GUIEntity(entity, imgPath);
            root.getChildren().add(guiEntity.getFxModel());
            guiEntities.add(guiEntity);
        });
    }
     */

    public void addEntity(Entity entity) {
        Color colorOfEntity = Color.BLACK;

        if (entity instanceof Character)
            colorOfEntity = ((Character) entity).getColor();
        else if (entity instanceof Chest)
            colorOfEntity = App.CHEST_COLOR;
        else if (entity instanceof DroppedItem) {
            Item item = ((DroppedItem) entity).getItem();
            if (item instanceof Key)
                colorOfEntity = App.KEYS_COLOR;
        }

        addEntity(entity, colorOfEntity);
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

        Platform.runLater(() -> root.getChildren().addAll(guiRoom.getFxModels()));
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
        Platform.runLater(() -> root.getChildren().add(notification.getFxModel()));
    }

    void removeNotification() {
        GUINotification notification = this.notification;

        if (notification != null)
            Platform.runLater(() -> root.getChildren().remove(notification.getFxModel()));
    }
}
