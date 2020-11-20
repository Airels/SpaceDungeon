package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.SGame;
import sounds.sound.entity.character.SPlayer;

public class SoundPlayerObserver implements Observer {
    private static SoundPlayerObserver instance;

    public SoundPlayerObserver() {
        instance = this;
    }

    @Override
    public void handle(int arg) {
        switch (arg) {
            case 0:
                SoundPlayer.play(SPlayer.TAKE_HIT);
                break;
            case 1:
                SoundPlayer.play(SPlayer.DIE);
                SoundPlayer.stop(SGame.MUSIC_1);
                break;
            default:
                throw new IllegalArgumentException("Unknown argument " + arg);
        }
    }

    public static SoundPlayerObserver getInstance() {
        return instance;
    }
}
