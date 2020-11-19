package sounds.sound.entity.character;

import sounds.Sound;

public enum SMonster implements Sound {
    TAKE_HIT("/sound/monster_hit.wav"),
    DIE("/sound/monster_die.wav");

    private final String path;

    SMonster(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
