package view.assetsLoader;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.characters.monsters.Monster;
import model.entities.characters.players.Player;

public class AssetEntityLoader {
    public static Image loadEntity(Entity entity) {
        if (entity instanceof Player) return AssetPlayerLoader.loadPlayer((Player) entity);

        if (entity instanceof Monster) return AssetMonsterLoader.loadMonster((Monster) entity);

        if (entity instanceof Chest) return null;


        throw new MissingAssetException();
    }
}
