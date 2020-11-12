package model.entities.characters.ai;

import model.Direction;
import model.entities.characters.Character;

public class StupidMonsterAI implements AI {
    public static final int STUPID_MONSTER_AI_LATENCY_MS = 50;

    private long lastProcessedAI;
    private boolean canProcessAI;

    @Override
    public void process(Character monster) {
        if (System.currentTimeMillis() - lastProcessedAI > STUPID_MONSTER_AI_LATENCY_MS) {
            lastProcessedAI = System.currentTimeMillis();
            canProcessAI = true;
        }

        if (canProcessAI) {
            switch ((int) (Math.random()*5)) {
                case 0: monster.move(Direction.LEFT);   break;
                case 1: monster.move(Direction.UP);     break;
                case 2: monster.move(Direction.RIGHT);  break;
                case 3: monster.move(Direction.DOWN);   break;
            }

            monster.attack();
        }

        if (canProcessAI) canProcessAI = false;
    }
}
