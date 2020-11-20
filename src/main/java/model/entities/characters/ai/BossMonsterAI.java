package model.entities.characters.ai;

import model.Coordinates;
import model.Direction;
import model.Game;
import model.entities.characters.Character;

public class BossMonsterAI implements AI {
    public static final int AI_LATENCY_MS = 75;
    public static final int AI_LATENCY_ATTACK_MS = 500;

    private long lastProcessedAI, lastAttack;
    private boolean canProcessAI, canAttack;

    @Override
    public void process(Character monster) {
        if (System.currentTimeMillis() - lastProcessedAI > AI_LATENCY_MS) {
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

            canProcessAI = false;
        }


        if (System.currentTimeMillis() - lastAttack > AI_LATENCY_ATTACK_MS) {
            lastAttack = System.currentTimeMillis();
            canAttack = true;
        }

        if (canAttack)  {
            monster.attack();
            canAttack = false;
        }
    }
}
