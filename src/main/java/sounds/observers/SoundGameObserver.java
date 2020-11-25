package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.SGame;

public class SoundGameObserver implements Observer {
    private static SoundGameObserver instance;

    public SoundGameObserver() {
        instance = this;
    }

    @Override
    public void notify(int arg) {
        switch (arg) {
            case 0:
                SoundPlayer.infinitePlay(SGame.MUSIC_1);
                break;
            case 1:
                SoundPlayer.stop(SGame.MUSIC_1);
                break;
            case 2:
                SoundPlayer.play(SGame.PAUSE);
                break;
            case 3:
                SoundPlayer.play(SGame.RESUME);
                break;
            default:
                throw new IllegalArgumentException("Unknown arg : " + arg);
        }
    }

    public static SoundGameObserver getInstance() {
        return instance;
    }
}
