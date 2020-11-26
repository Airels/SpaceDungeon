package view.assetsLoader.entities;

import controller.App;
import javafx.scene.image.Image;
import view.assetsLoader.AssetLoader;

public class AssetOpenedChest implements AssetLoader {
    @Override
    public Image load() {
        return new Image("/assets/chestopen.png", App.DEFAULT_ENTITY_SIZE, App.DEFAULT_ENTITY_SIZE, false, false);
    }
}
