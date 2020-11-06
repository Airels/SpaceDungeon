package model.items;

import javafx.scene.paint.Color;
import model.Game;
import model.entities.characters.players.Player;

public class StrengthPotion implements Item {


    private int strBonus;
    private int value;
    private Color color;

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
    public void action() {
        Game game = Game.getInstance();
        Player player = game.getPlayer();

        player.addStrength(strBonus);
    }

    @Override
    public String name() {
        return "Potion of Strength";
    }
}