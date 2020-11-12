package model.entities.characters.ai;

import controller.App;
import model.Coordinates;
import model.Game;
import model.entities.characters.Character;
import model.Direction;

public class SimpleMonsterAI implements AI {
    public static final int SIMPLE_MONSTER_AI_LATENCY_MS = 125;

    private long lastProcessedAI;
    private boolean canProcessAI;

    @Override
    public void process(Character monster) {
        if (System.currentTimeMillis() - lastProcessedAI > SIMPLE_MONSTER_AI_LATENCY_MS) {
            lastProcessedAI = System.currentTimeMillis();
            canProcessAI = true;
        }

        if (canProcessAI) {
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

        if (canProcessAI) canProcessAI = false;
    }
}
