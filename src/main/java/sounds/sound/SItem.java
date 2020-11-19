package sounds.sound;

import sounds.Sound;

public enum SItem implements Sound {
    USE("/sound/item_use.wav");

    private final String path;

    SItem(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
