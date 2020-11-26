package view.assetsLoader.entities.players;

import controller.App;
import javafx.scene.image.Image;
import model.entities.EntityFactory;

public class AssetPlayer implements AssetPlayerLoader {

    @Override
    public Image load() {
        return load(true);
    }
    public Image load(boolean alive) {
        double size = new EntityFactory().createPlayer(App.PLAYER_TYPE).getSize();

        if (alive)
            return new Image("/assets/player.gif", size, size, false, false);
        else
            return new Image("/assets/player_dead.png", size, size, false, false);
    }
}
