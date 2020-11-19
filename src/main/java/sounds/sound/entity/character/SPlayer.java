package sounds.sound.entity.character;

import sounds.Sound;

public enum SPlayer implements Sound {
    TAKE_HIT("/sound/player_hit.wav"),
    DIE("/sound/player_die.wav");


    private final String path;

    SPlayer(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
