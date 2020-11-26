package view.assetsLoader;

import javafx.scene.image.Image;

public interface AssetLoaderHelper<T> {
    Image load(T obj);
}
