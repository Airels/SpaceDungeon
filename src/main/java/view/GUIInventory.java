package view;

import controller.App;
import javafx.scene.Node;
import javafx.scene.text.*;
import model.Game;
import model.entities.characters.Inventory;
import model.items.Item;

import java.util.Collections;
import java.util.List;

public class GUIInventory implements GUIObject {
    private Text text;

    GUIInventory() {
        text = new Text("- Inventory -\n");
        text.setFont(Font.font(App.NOTIFICATION_FONT_FAMILY, FontWeight.BOLD, FontPosture.REGULAR, App.NOTIFICATION_FONT_SIZE));
        text.setTextAlignment(TextAlignment.LEFT);
        text.setX(30);
        text.setY(40);
    }

    @Override
    public void render() {
        Inventory inventory = Game.getInstance().getPlayer().getInventory();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("- Inventory -\n");

        for (int i = 0; i < Inventory.CAPACITY; i++) {
            stringBuilder.append(i).append(". ");

            Item item = inventory.getItem(i);
            if (item != null)
                stringBuilder.append(inventory.getItem(i).name());

            stringBuilder.append('\n');
        }

        text.setText(stringBuilder.toString());
    }

    @Override
    public List<Node> getFxModels() {
        return Collections.singletonList(text);
    }
}
