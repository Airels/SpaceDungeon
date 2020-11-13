package model.entities;

import javafx.scene.paint.Color;
import model.Coordinates;
import model.Game;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;
import model.entities.characters.players.Player;
import model.rooms.Room;


public class TrappedChest extends Entity{
    private final MonsterType monster;

    public TrappedChest(Coordinates coords, String name, double size, Color color, MonsterType monster) {
        super(coords, name, size, color);
        this.monster = monster;
    }

    public void action(){
        Game game = Game.getInstance();
        game.roomManager().actualRoom().getEntities().add(new Monster(monster));
    }

}
