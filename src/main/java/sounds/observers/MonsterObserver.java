package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.entity.character.SMonster;

public class MonsterObserver implements Observer {
    private static MonsterObserver instance;

    public MonsterObserver() {
        instance = this;
    }

    @Override
    public void handle(int arg) {
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

    public static MonsterObserver getInstance() {
        return instance;
    }
}
