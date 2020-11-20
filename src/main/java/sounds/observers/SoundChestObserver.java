package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.entity.SChest;

public class SoundChestObserver implements Observer {
    private static SoundChestObserver soundChestObserver;

    public SoundChestObserver() {
        soundChestObserver = this;
    }

    @Override
    public void handle(int arg) {
        SoundPlayer.play(SChest.OPEN);
    }

    public static SoundChestObserver getInstance() {
        return soundChestObserver;
    }
}