package sounds.sound;

import sounds.Sound;

public enum SGame implements Sound {
    MUSIC_1("/sound/music1.wav"),
    MUSIC_2("/sound/music2.wav"),
    PAUSE("/sound/pause_game.wav"),
    RESUME("/sound/resume_game.wav");


    private final String path;

    SGame(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
