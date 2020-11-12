package view;

import controller.App;
import javafx.scene.Node;
import javafx.scene.text.*;

import java.util.Collections;
import java.util.List;

public class GUIPauseMenu implements GUIObject {
    private final Text text;

    GUIPauseMenu() {
        text = new Text("- Inventory -\n");
        text.setFont(Font.font(App.NOTIFICATION_FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, App.NOTIFICATION_FONT_SIZE));
        text.setTextAlignment(TextAlignment.LEFT);
        text.setX(30);
        text.setY(App.HEIGHT-180);
    }

    @Override
    public void render() {
        StringBuilder builder = new StringBuilder();

        builder.append("GAME PAUSED").append('\n')
                .append("To resume game, press ESCAPE").append('\n')
                .append('\n').append('\n')
                .append("- CONTROLS").append('\n')
                .append("Move: W-A-S-Q / Z-Q-S-D / Directional arrows").append('\n')
                .append("Open chest: E / RIGHT CLICK").append('\n')
                .append("Attack: LEFT CLICK").append('\n')
                .append("Use items: 1-9 DIGITS");

        text.setText(builder.toString());
    }

    @Override
    public List<Node> getFxModels() {
        return Collections.singletonList(text);
    }
}
