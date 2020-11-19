package sounds.sound.entity.character;

import sounds.Sound;

public enum SBoss implements Sound {
    ENCOUNTER("/sound/boss_encounter.wav"),
    DIE("/sound/boss_defeated.wav");

    private final String path;

    SBoss(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
