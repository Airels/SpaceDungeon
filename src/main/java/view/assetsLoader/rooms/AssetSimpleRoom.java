package view.assetsLoader.rooms;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import view.assetsLoader.AssetLoader;

public class AssetSimpleRoom implements AssetLoader {
    @Override
    public Image load() {
        throw new MissingAssetException();
    }
}
