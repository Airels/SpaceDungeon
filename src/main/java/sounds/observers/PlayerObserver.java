package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.entity.character.SPlayer;

public class PlayerObserver implements Observer {
    private static PlayerObserver instance;

    public PlayerObserver() {
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
                break;
            default:
                throw new IllegalArgumentException("Unknown argument " + arg);
        }
    }

    public static PlayerObserver getInstance() {
        return instance;
    }
}
