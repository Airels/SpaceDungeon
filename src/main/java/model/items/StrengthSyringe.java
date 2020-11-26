package model.items;

import model.Game;
import model.entities.characters.Inventory;
import model.entities.characters.players.Player;

public class StrengthSyringe implements Item {
    private final int strBonus;

    public StrengthSyringe() {
        int value = (int) (Math.random() * (101));

        if (value <= 69)
            this.strBonus = 1;
        else if (value > 89)
            this.strBonus = 8;
        else
            this.strBonus = 3;
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
        return "Strength Syringe";
    }
}