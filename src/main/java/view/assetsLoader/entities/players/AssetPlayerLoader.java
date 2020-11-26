package view.assetsLoader.entities.players;

import javafx.scene.image.Image;
import view.assetsLoader.AssetLoader;

public interface AssetPlayerLoader extends AssetLoader {
    Image load(boolean alive);
}
