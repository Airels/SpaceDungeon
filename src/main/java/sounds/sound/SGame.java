package sounds.sound;

import sounds.Sound;

public enum SGame implements Sound {
    MUSIC_1("/sound/music1.wav"),
    MUSIC_2("/sound/music2.wav");


    private final String path;

    SGame(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
