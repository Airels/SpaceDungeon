package model.items;


import javafx.scene.paint.Color;
import model.Game;
import model.entities.characters.Inventory;
import model.entities.characters.players.Player;

public class Syringe implements Item {

    private final double healPercentage;
    private final int value;
    private final Color color;

    public Syringe(){
        this.value = (int)(Math.random()*(101));

        if(value <= 69){
            this.healPercentage = 0.25;
            this.color = Color.WHITE;
        }
        else if(value > 89){
            this.healPercentage = 0.75;
            this.color = Color.RED;
        }
        else {
            this.healPercentage = 0.50;
            this.color = Color.GREEN;
        }
    }

    @Override
    public void use() {
        Player player = Game.getInstance().getPlayer();
        player.heal((int) (player.getMaxHealth()*healPercentage + player.getHealthPoints()));
        Inventory inventory = player.getInventory();
        inventory.removeItem(this);

        usedMessage();
    }

    @Override
    public String name() {
        return "Syringe";
    }

    @Override
    public Color getColor() {
        return Color.DARKGREEN;
    }
}
