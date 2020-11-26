package view.assetsLoader.entities.monsters;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterType;
import view.assetsLoader.AssetLoaderHelper;

import java.util.Objects;

public class AssetMonster implements AssetLoaderHelper<Monster> {

    @Override
    public Image load(Monster monster) {
        switch (Objects.requireNonNull(Objects.requireNonNull(MonsterType.getMonsterType(monster)))) {
            case BLOB:
                return new AssetBlob().load();
            case ALIEN:
                return new AssetAlien().load();
            case CHIMERE:
                return new AssetChimere().load();
            case THE_BOSS:
                return new AssetTheBoss().load();
        }

        throw new MissingAssetException();
    }
}
