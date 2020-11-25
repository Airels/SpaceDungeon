package view.assetsLoader;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.EntityFactory;
import model.entities.characters.players.Player;
import model.entities.characters.players.PlayerType;

public class AssetPlayerLoader {

    public static Image loadPlayer(PlayerType playerType, double size, boolean alive) {
        Image image;

        switch (playerType) {
            case NORMAL:
            case HARD:
            case IMPOSSIBLE:
            case GOD_OF_HYPERDEATH:
                if (alive)
                    return new Image("/assets/player.gif", size, size, false, false);
                else
                    return new Image("/assets/player_dead.png", size, size, false, false);
        }

        throw new MissingAssetException();
    }

    public static Image loadPlayer(Player player, boolean alive) {
        for (PlayerType type : PlayerType.values()) {
            if (new EntityFactory().createPlayer(type).equals(player))
                return loadPlayer(type, player.getSize(), alive);
        }

        throw new IllegalArgumentException("Player absent from PlayerType list");
    }
}
