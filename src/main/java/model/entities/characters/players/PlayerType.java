package model.entities.characters.players;

import javafx.scene.paint.Color;

public enum PlayerType {

    NORMAL(new PlayerBuilder("Normal Player")),

    GOD_OF_HYPERDEATH(new PlayerBuilder("Asriel Dreemurr - God Of HyperDeath")
            .setHealthPoints(1_000_000)
            .setStrength(1_000_000)
            .setColor(Color.LIGHTGREEN)
    ),

    HARD(new PlayerBuilder("Tarik")
            .setHealthPoints(200)
            .setStrength(10)
            .setSpeed(0.5)
            .setColor(Color.HOTPINK)),

    IMPOSSIBLE(new PlayerBuilder("Tom")
            .setHealthPoints(1)
            .setStrength(0)
            .setSpeed(2));

    private final PlayerBuilder builder;

    PlayerType(PlayerBuilder builder) {
        this.builder = builder;
    }

    public PlayerBuilder getBuilder() {
        return builder;
    }
}
