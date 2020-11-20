package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.entity.character.SBoss;

public class SoundBossObserver implements Observer {
    private static SoundBossObserver instance;

    public SoundBossObserver() {
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

    public static SoundBossObserver getInstance() {
        return instance;
    }
}