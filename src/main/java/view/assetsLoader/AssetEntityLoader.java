package view.assetsLoader;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.OpenedChest;
import model.entities.characters.monsters.Monster;
import model.entities.characters.players.Player;

public class AssetEntityLoader {
    public static Image loadEntity(Entity entity) {
        if (entity instanceof Player) return AssetPlayerLoader.loadPlayer((Player) entity);

        if (entity instanceof Monster) return AssetMonsterLoader.loadMonster((Monster) entity);

        if (entity instanceof Chest) return new Image("/assets/chestlock.png", entity.getSize(), entity.getSize(), true, true);

        if (entity instanceof OpenedChest) return new Image("/assets/chestopen.png", entity.getSize(), entity.getSize(), true, true);


        throw new MissingAssetException();
    }
}
