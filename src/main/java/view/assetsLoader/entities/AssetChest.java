package view.assetsLoader.entities;

import controller.App;
import javafx.scene.image.Image;
import view.assetsLoader.AssetLoader;

public class AssetChest implements AssetLoader {

    @Override
    public Image load() {
        return new Image("/assets/chestlock.png", App.DEFAULT_ENTITY_SIZE, App.DEFAULT_ENTITY_SIZE, false, false);
    }
}
