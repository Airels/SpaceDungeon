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
    private boolean canUpdate;

    public MainGUI(Group root) {
        mainGUI = this;
        guiEntities = new ArrayList<>();
        this.root = root;
        canUpdate = true;
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
        if (entity instanceof Character)
            addEntity(entity, ((Character) entity).getColor());
        else if (entity instanceof Chest)
            addEntity(entity, App.CHEST_COLOR);
        else if (entity instanceof DroppedItem) {
            Item item = ((DroppedItem) entity).getItem();
            if (item instanceof Key)
                addEntity(entity, App.KEYS_COLOR);
        }
        else
            addEntity(entity, Color.BLACK);
    }

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
            guiEntities.remove(guiEntity);
            Platform.runLater(() -> root.getChildren().remove(guiEntity.getFxModel()));
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
}
