package view.assetsLoader;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.Entity;
import model.entities.EntityFactory;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;
import model.entities.characters.players.PlayerType;
import view.GraphicEngine;

public class AssetMonsterLoader {

    public static Image loadMonster(MonsterType monsterType, double size) throws MissingAssetException {
        Image image;

        switch (monsterType) {
            case BLOB:
                image = new Image("/assets/blob.gif", size, size, false, false);
                break;
            case ALIEN:
                image = new Image("/assets/alien.gif", size, size, false, false);
                break;
            case CHIMERE:
                image = new Image("/assets/chimere.gif", size, size, false, false);
                break;
            case SWARM:
                image = null;
                break;
            case THE_BOSS:
                image = new Image("/assets/boss.gif", size, size, false, false);
                break;

            default: throw new MissingAssetException();
        }

        return image;
    }

    public static Image loadMonster(Monster monster) {
        for (MonsterType type : MonsterType.values()) {
            if (new EntityFactory().createMonster(type).equals(monster))
                return loadMonster(type, monster.getSize());
        }

        throw new IllegalArgumentException("Monster absent from MonsterType list");
    }
}
