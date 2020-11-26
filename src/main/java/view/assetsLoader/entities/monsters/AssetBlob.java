package view.assetsLoader.entities.monsters;

import javafx.scene.image.Image;
import model.entities.EntityFactory;
import model.entities.characters.monsters.MonsterType;
import view.assetsLoader.AssetLoader;

public class AssetBlob implements AssetLoader {
    @Override
    public Image load() {
        double size = new EntityFactory().createMonster(MonsterType.BLOB).getSize();
        return new Image("/assets/blob.gif", size, size, false, false);
    }
}
