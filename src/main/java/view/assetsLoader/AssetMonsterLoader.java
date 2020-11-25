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
                return new Image("/assets/blob.gif", size, size, false, false);
            case ALIEN:
                return new Image("/assets/alien.gif", size, size, false, false);
            case CHIMERE:
                return new Image("/assets/chimere.gif", size, size, false, false);
            case SWARM:
                throw new MissingAssetException("Swarm asset missing");
            case THE_BOSS:
                return new Image("/assets/boss.gif", size, size, false, false);
        }

        throw new MissingAssetException();
    }

    public static Image loadMonster(Monster monster) {
        for (MonsterType type : MonsterType.values()) {
            if (new EntityFactory().createMonster(type).equals(monster))
                return loadMonster(type, monster.getSize());
        }

        throw new IllegalArgumentException("Monster absent from MonsterType list");
    }
}
