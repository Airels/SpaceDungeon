package view.assetsLoader;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.Chest;
import model.entities.DroppedItem;
import model.entities.Entity;
import model.entities.OpenedChest;
import model.entities.characters.monsters.Monster;
import model.entities.characters.players.Player;
import model.items.Item;

public class AssetEntityLoader {
    public static Image loadEntity(Entity entity) {
        if (entity instanceof Player) return AssetPlayerLoader.loadPlayer((Player) entity, true);

        if (entity instanceof Monster) return AssetMonsterLoader.loadMonster((Monster) entity);

        if (entity instanceof Item) return AssetItemsLoader.loadItem(((DroppedItem) entity).getItem());

        if (entity instanceof Chest) return new Image("/assets/chestlock.png", entity.getSize(), entity.getSize(), false, false);

        if (entity instanceof OpenedChest) return new Image("/assets/chestopen.png", entity.getSize(), entity.getSize(), false, false);



        throw new MissingAssetException();
    }
}
