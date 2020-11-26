package view.graphicalObjects;

import controller.App;
import javafx.scene.Node;
import javafx.scene.text.*;
import model.Coordinates;
import model.Game;
import model.entities.characters.players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GPauseMenu implements GObject {
    private final Text textControls, textStats;

    public GPauseMenu() {
        textControls = new Text("GAME PAUSED\n");
        textControls.setFont(Font.font(App.TEXT_FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, App.NOTIFICATION_FONT_SIZE));
        textControls.setTextAlignment(TextAlignment.LEFT);
        textControls.setX(30);
        textControls.setY(App.HEIGHT-180);

        textStats = new Text("- Statistics -\n");
        textStats.setFont(Font.font(App.TEXT_FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, App.NOTIFICATION_FONT_SIZE));
        textStats.setTextAlignment(TextAlignment.RIGHT);
        textStats.setX(App.WIDTH-140);
        textStats.setY(App.HEIGHT-180);
    }

    @Override
    public void render() {

        String builderControls = "GAME PAUSED" + '\n' +
                "To resume game, press ESCAPE" + '\n' +
                '\n' + '\n' +
                "- CONTROLS" + '\n' +
                "Move: W-A-S-D / Z-Q-S-D / Directional arrows" + '\n' +
                "Open chest: E / RIGHT CLICK" + '\n' +
                "Attack: LEFT CLICK" + '\n' +
                "Use items: 1-9 DIGITS";

        textControls.setText(builderControls);


        Player player = Game.getInstance().getPlayer();
        Coordinates actualRoomCoords = Game.getInstance().roomManager().actualRoom().getCoords();

        String builderStats = "- Statistics -" + '\n' +
                "HP: " + player.getHealthPoints() + '\n' +
                "Strength: " + player.getStrength() + '\n' +
                "Room: " + '(' +
                (int) actualRoomCoords.getX() + ',' +
                (int) actualRoomCoords.getY() + ')';

        textStats.setText(builderStats);
    }

    @Override
    public List<Node> getFxNodes() {
        return new ArrayList<>(Arrays.asList(textControls, textStats));
    }
}
