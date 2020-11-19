package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.SGame;

public class GameObserver implements Observer {
    private static GameObserver instance;

    public GameObserver() {
        instance = this;
    }

    @Override
    public void handle(int arg) {
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

    public static GameObserver getInstance() {
        return instance;
    }
}
