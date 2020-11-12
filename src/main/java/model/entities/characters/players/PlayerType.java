package model.entities.characters.players;

import javafx.scene.paint.Color;

public enum PlayerType {

    NORMAL(new PlayerBuilder("Normal Player")),

    GOD_OF_HYPERDEATH(new PlayerBuilder("Asriel Dreemurr - God Of HyperDeath")
            .setHealthPoints(1_000_000)
            .setStrength(1_000_000)
            .setColor(Color.LIGHTGREEN)
    );

    private final PlayerBuilder builder;

    PlayerType(PlayerBuilder builder) {
        this.builder = builder;
    }

    public PlayerBuilder getBuilder() {
        return builder;
    }
}
