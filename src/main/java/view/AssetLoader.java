package view;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.characters.monsters.MonsterType;
import model.entities.characters.players.PlayerType;

public class AssetLoader {

    public static Image loadPlayer(PlayerType playerType) throws MissingAssetException {
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
    }

    public static Image loadMonster(MonsterType monsterType) throws MissingAssetException {
        Image image;

        switch (monsterType) {
            case BLOB:
                image = null;
                break;
            case ALIEN:
                image = null;
                break;
            case CHIMERE:
                image = null;
                break;
            case SWARM:
                image = null;
                break;
            case THE_BOSS:
                image = null;
                break;

            default: throw new MissingAssetException();
        }

        return image;
    }
}
