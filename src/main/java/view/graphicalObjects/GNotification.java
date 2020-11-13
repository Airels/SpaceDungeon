package view.graphicalObjects;

import controller.App;
import javafx.scene.Node;
import javafx.scene.text.*;
import model.Coordinates;
import model.Game;

import java.util.Collections;
import java.util.List;

public class GNotification implements GObject {
    private final Text text;
    private final int duration; // in milliseconds
    private final long creationTime;

    public GNotification(String message, int duration) {
        text = new Text(message);
        text.setFont(Font.font(App.NOTIFICATION_FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, App.NOTIFICATION_FONT_SIZE));
        text.setTextAlignment(TextAlignment.CENTER);

        this.duration = duration;
        creationTime = System.currentTimeMillis();
        render();
    }

    @Override
    public void render() {
        Coordinates playerCoords = Game.getInstance().getPlayer().getCoords();

        text.setX(playerCoords.getX() - text.getLayoutBounds().getWidth()/2);
        text.setY(playerCoords.getY() - App.NOTIFICATION_MARGIN - text.getLayoutBounds().getHeight());
    }

    @Override
    public List<Node> getFxModels() {
        return Collections.singletonList(text);
    }

    public boolean expired() {
        return (creationTime + duration < System.currentTimeMillis());
    }
}
