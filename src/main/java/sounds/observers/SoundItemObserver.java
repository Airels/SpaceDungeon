package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.SItem;

public class SoundItemObserver implements Observer {
    private static SoundItemObserver instance;

    public SoundItemObserver() {
        instance = this;
    }

    @Override
    public void notify(int arg) {
        SoundPlayer.play(SItem.USE);
    }

    public static SoundItemObserver getInstance() {
        return instance;
    }
}
