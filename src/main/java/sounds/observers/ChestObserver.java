package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.entity.SChest;

public class ChestObserver implements Observer {
    private static ChestObserver chestObserver;

    public ChestObserver() {
        chestObserver = this;
    }

    @Override
    public void handle(int arg) {
        SoundPlayer.play(SChest.OPEN);
    }

    public static ChestObserver getInstance() {
        return chestObserver;
    }
}
