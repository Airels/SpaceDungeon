package model.entities;

import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;


public class TrappedChest extends Entity{
    private MonsterType monster = MonsterType.SWARM;

    public void action(){
        new Monster(monster).spawn();
    }

}
