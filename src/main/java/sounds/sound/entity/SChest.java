package sounds.sound.entity;

import sounds.Sound;

public enum SChest implements Sound {
    OPEN("/sound/open_chest.wav");

    private final String path;

    SChest(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
