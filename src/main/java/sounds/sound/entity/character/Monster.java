package sounds.sound.entity.character;

import sounds.Sound;

public enum Monster implements Sound {
    TAKE_HIT,
    DIE;

    @Override
    public String getPath() {
        return null;
    }
}
