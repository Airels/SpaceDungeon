package view.assetsLoader.rooms;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.rooms.RoomType;
import view.assetsLoader.AssetLoaderHelper;

public class AssetRoom implements AssetLoaderHelper<RoomType> {
    @Override
    public Image load(RoomType roomType) {
        switch (roomType) {
            case SIMPLE_ROOM:
                return new AssetSimpleRoom().load();
            case MONSTER_ROOM:
                return new AssetMonsterRoom().load();
            case BOSS_ROOM:
                return new AssetBossRoom().load();
        }

        throw new MissingAssetException();
    }
}
