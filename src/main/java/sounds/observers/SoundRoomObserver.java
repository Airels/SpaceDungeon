package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.SRoom;

public class SoundRoomObserver implements Observer {
    private static SoundRoomObserver instance;

    public SoundRoomObserver() {
        instance = this;
    }

    @Override
    public void handle(int arg) {
        switch (arg) {
            case 0:
                SoundPlayer.play(SRoom.OPEN_DOOR);
                break;
            default:
                throw new IllegalArgumentException("Unknown argument " + arg);
        }
    }

    public static SoundRoomObserver getInstance() {
        return instance;
    }
}
