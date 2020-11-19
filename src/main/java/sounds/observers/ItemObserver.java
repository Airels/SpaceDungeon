package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.SItem;

public class ItemObserver implements Observer {
    private static ItemObserver instance;

    public ItemObserver() {
        instance = this;
    }

    @Override
    public void handle(int arg) {
        SoundPlayer.play(SItem.USE);
    }

    public static ItemObserver getInstance() {
        return instance;
    }
}
