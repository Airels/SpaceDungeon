package view.assetsLoader.items;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.items.Item;
import model.items.Key;
import view.assetsLoader.AssetLoaderHelper;

public class AssetItem implements AssetLoaderHelper<Item> {
    @Override
    public Image load(Item item) {
        if (item instanceof Key) return new AssetKey().load();

        throw new MissingAssetException();
    }
}
