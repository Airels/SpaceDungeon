package view.assetsLoader.entities.monsters;

import javafx.scene.image.Image;
import model.entities.EntityFactory;
import model.entities.characters.monsters.MonsterType;
import view.assetsLoader.AssetLoader;

public class AssetTheBoss implements AssetLoader {
    @Override
    public Image load() {
        double size = new EntityFactory().createMonster(MonsterType.THE_BOSS).getSize();
        return new Image("/assets/boss.gif", size, size, false, false);
    }
}
