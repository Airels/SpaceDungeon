package model.items;


import javafx.scene.paint.Color;
import model.Game;
import model.entities.characters.Player;


public class Syringe implements Item {

    private double healPercentage;
    private int value;
    private Color color;

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
    public void action() {

    }

    public void use(){
        Player player = Game.getInstance().getPlayer();
        player.heal((int) (player.getMaxHealth()*healPercentage + player.getHealthPoints()));
    }
}
