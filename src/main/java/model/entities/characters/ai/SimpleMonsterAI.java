package model.entities.characters.ai;

import model.Coordinates;
import model.Game;
import model.entities.characters.Character;
import model.Direction;

public class SimpleMonsterAI implements AI {

    @Override
    public void process(Character monster) {
        Coordinates playerCoords = Game.getInstance().getPlayer().getCoords();
        Coordinates monsterCoords = monster.getCoords();

        if (Math.abs(playerCoords.getX() - monsterCoords.getX()) > Math.abs(playerCoords.getY() - monsterCoords.getY())) {
            if (monsterCoords.getX() - playerCoords.getX() > 0)
                monster.move(Direction.LEFT);
            else
                monster.move(Direction.RIGHT);
        } else {
            if (monsterCoords.getY() - playerCoords.getY() > 0)
                monster.move(Direction.UP);
            else
                monster.move(Direction.DOWN);
        }

        monster.attack();
    }
}
