package view.assetsLoader;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.characters.players.Player;
import model.entities.characters.players.PlayerType;

public class AssetPlayerLoader {

    public static Image loadPlayer(PlayerType playerType, double size) {
        /*
        Image image;

        switch (playerType) {
            case NORMAL:
                image = null;
                break;
            case HARD:
                image = null;
                break;
            case IMPOSSIBLE:
                image = null;
                break;
            case GOD_OF_HYPERDEATH:
                image = null;
                break;

            default: throw new MissingAssetException();
        }

        return image;
        */

        try {
            return new Image("/assets/player.gif", size, size, false, false);
        } catch (IllegalArgumentException e) {
            throw new MissingAssetException();
        }
    }

    public static Image loadPlayer(Player player) {
        for (PlayerType type : PlayerType.values()) {
            if (type.getBuilder().build().equals(player))
                return loadPlayer(type, player.getSize());
        }

        throw new IllegalArgumentException("Player absent from PlayerType list");
    }
}
