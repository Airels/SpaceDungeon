package view.assetsLoader.entities.monsters;

import javafx.scene.image.Image;
import model.entities.EntityFactory;
import model.entities.characters.monsters.MonsterType;
import view.assetsLoader.AssetLoader;

public class AssetAlien implements AssetLoader {
    @Override
    public Image load() {
        double size = new EntityFactory().createMonster(MonsterType.ALIEN).getSize();
        return new Image("/assets/alien.gif", size, size, false, false);
    }
}
