package model.entities;

import model.Coordinates;
import model.Game;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;
import model.entities.characters.players.Player;
import model.rooms.Room;


public class TrappedChest extends Entity{
    private MonsterType monster = MonsterType.SWARM;

    public void action(){
        Game game = Game.getInstance();
        game.getActualRoom().getEntities().add(new Monster(MonsterType.SWARM));
    }

}
