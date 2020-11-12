package model.entities.characters.ai;

import model.Coordinates;
import model.Direction;
import model.Game;
import model.entities.characters.Character;

public class BossMonsterAI implements AI {
    public static final int BOSS_MONSTER_AI_LATENCY_MS = 75;

    private long lastProcessedAI;
    private boolean canProcessAI;

    @Override
    public void process(Character monster) {
        if (System.currentTimeMillis() - lastProcessedAI > BOSS_MONSTER_AI_LATENCY_MS) {
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
