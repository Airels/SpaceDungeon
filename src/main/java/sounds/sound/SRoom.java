package sounds.sound;

import sounds.Sound;

public enum SRoom implements Sound {
    OPEN_DOOR("/sound/open_door.wav");

    private final String path;

    SRoom(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
