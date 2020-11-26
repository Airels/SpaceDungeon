package model.items;

import model.Game;
import model.entities.characters.Inventory;
import model.entities.characters.players.Player;

public class HealthSyringe implements Item {

    private final double healPercentage;

    public HealthSyringe(){
        int value = (int) (Math.random() * (101));

        if(value <= 69)
            this.healPercentage = 0.25;
        else if(value > 89)
            this.healPercentage = 0.75;
        else
            this.healPercentage = 0.50;
    }

    @Override
    public void use() {
        Player player = Game.getInstance().getPlayer();
        player.heal((int) (player.getMaxHealth()*healPercentage + player.getHealthPoints()));
        Inventory inventory = player.getInventory();
        inventory.removeItem(this);

        usedAction();
    }

    @Override
    public String name() {
        return "Health syringe";
    }
}
