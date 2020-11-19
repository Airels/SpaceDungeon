package model.items;

import javafx.scene.paint.Color;
import model.Game;
import model.entities.characters.Inventory;
import model.entities.characters.players.Player;

public class StrengthPotion implements Item {
    private final int strBonus;
    private final int value;
    private final Color color;

    public StrengthPotion() {
        this.value = (int) (Math.random() * (101));

        if (value <= 69) {
            this.strBonus = 1;
            this.color = Color.WHITE;
        } else if (value > 89) {
            this.strBonus = 8;
            this.color = Color.RED;
        } else {
            this.strBonus = 3;
            this.color = Color.GREEN;
        }
    }

    @Override
    public void use() {
        Game game = Game.getInstance();
        Player player = game.getPlayer();
        player.addStrength(strBonus);
        Inventory inventory = player.getInventory();
        inventory.removeItem(this);

        usedAction();
    }

    @Override
    public String name() {
        return "Potion of Strength";
    }

    @Override
    public Color getColor() {
        return Color.DARKRED;
    }
}