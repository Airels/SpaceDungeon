package view.assetsLoader.items;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import view.assetsLoader.AssetLoader;

public class AssetKey implements AssetLoader {
    @Override
    public Image load() {
        throw new MissingAssetException();
    }
}
