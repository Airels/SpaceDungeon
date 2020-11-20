package view.assetsLoader;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;
import model.entities.characters.players.PlayerType;
import view.GraphicEngine;

public class AssetMonsterLoader {

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
                image = new Image("/assets/chimere.gif");
                break;
            case SWARM:
                image = null;
                break;
            case THE_BOSS:
                image = new Image("/assets/boss.gif");
                break;

            default: throw new MissingAssetException();
        }

        return image;
    }

    public static Image loadMonster(Monster monster) {
        for (MonsterType type : MonsterType.values()) {
            if (type.getBuilder().build().equals(monster))
                return loadMonster(type);
        }

        throw new IllegalArgumentException("Monster absent from MonsterType list");
    }
}
