package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.entity.character.SBoss;
import sounds.sound.entity.character.SMonster;

public class BossObserver implements Observer {
    private static BossObserver instance;

    public BossObserver() {
        instance = this;
    }

    @Override
    public void handle(int arg) {
        switch (arg) {
            case 0:
                SoundPlayer.play(SBoss.ENCOUNTER);
                break;
            case 1:
                SoundPlayer.play(SBoss.DIE);
                break;
            default:
                throw new IllegalArgumentException("Unknown argument " + arg);
        }
    }

    public static BossObserver getInstance() {
        return instance;
    }
}
