package sounds.observers;

import model.Observer;
import sounds.SoundPlayer;
import sounds.sound.SRoom;

public class RoomObserver implements Observer {
    private static RoomObserver instance;

    public RoomObserver() {
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

    public static RoomObserver getInstance() {
        return instance;
    }
}
