package sounds.observers;

import utils.Observer;
import sounds.SoundPlayer;
import sounds.sound.entity.character.SMonster;

public class SoundMonsterObserver implements Observer {
    private static SoundMonsterObserver instance;

    public SoundMonsterObserver() {
        instance = this;
    }

    @Override
    public void notify(int arg) {
        switch (arg) {
            case 0:
                SoundPlayer.play(SMonster.TAKE_HIT);
                break;
            case 1:
                SoundPlayer.play(SMonster.DIE);
                break;
            default:
                throw new IllegalArgumentException("Unknown argument " + arg);
        }
    }

    public static SoundMonsterObserver getInstance() {
        return instance;
    }
}
