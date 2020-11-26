package view.assetsLoader.entities;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.entities.Chest;
import model.entities.DroppedItem;
import model.entities.Entity;
import model.entities.OpenedChest;
import model.entities.characters.monsters.Monster;
import model.entities.characters.players.Player;
import model.items.Item;
import view.assetsLoader.AssetLoaderHelper;
import view.assetsLoader.entities.monsters.AssetMonster;
import view.assetsLoader.entities.players.AssetPlayer;
import view.assetsLoader.items.AssetItem;

public class AssetEntity implements AssetLoaderHelper<Entity> {

    @Override
    public Image load(Entity entity) {
        if (entity instanceof Player) return new AssetPlayer().load();

        if (entity instanceof Monster) return new AssetMonster().load((Monster) entity);

        if (entity instanceof Item) return new AssetItem().load(((DroppedItem) entity).getItem());

        if (entity instanceof Chest) return new AssetChest().load();

        if (entity instanceof OpenedChest) return new AssetOpenedChest().load();



        throw new MissingAssetException();
    }
}
