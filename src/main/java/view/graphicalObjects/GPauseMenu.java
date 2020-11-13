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
        textControls.setFont(Font.font(App.NOTIFICATION_FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, App.NOTIFICATION_FONT_SIZE));
        textControls.setTextAlignment(TextAlignment.LEFT);
        textControls.setX(30);
        textControls.setY(App.HEIGHT-180);

        textStats = new Text("- Statistics -\n");
        textStats.setFont(Font.font(App.NOTIFICATION_FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, App.NOTIFICATION_FONT_SIZE));
        textStats.setTextAlignment(TextAlignment.RIGHT);
        textStats.setX(App.WIDTH-140);
        textStats.setY(App.HEIGHT-180);
    }

    @Override
    public void render() {
        StringBuilder builderControls = new StringBuilder();

        builderControls.append("GAME PAUSED").append('\n')
                .append("To resume game, press ESCAPE").append('\n')
                .append('\n').append('\n')
                .append("- CONTROLS").append('\n')
                .append("Move: W-A-S-Q / Z-Q-S-D / Directional arrows").append('\n')
                .append("Open chest: E / RIGHT CLICK").append('\n')
                .append("Attack: LEFT CLICK").append('\n')
                .append("Use items: 1-9 DIGITS");

        textControls.setText(builderControls.toString());


        Player player = Game.getInstance().getPlayer();
        Coordinates actualRoomCoords = Game.getInstance().roomManager().actualRoom().getCoords();
        StringBuilder builderStats = new StringBuilder();

        builderStats.append("- Statistics -").append('\n')
                .append("HP: ").append(player.getHealthPoints()).append('\n')
                .append("Strength: ").append(player.getStrength()).append('\n')
                .append("Room: ").append('(')
                .append((int) actualRoomCoords.getX()).append(',')
                .append((int) actualRoomCoords.getY()).append(')');

        textStats.setText(builderStats.toString());
    }

    @Override
    public List<Node> getFxModels() {
        return new ArrayList<>(Arrays.asList(textControls, textStats));
    }
}
