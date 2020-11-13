package view.assetsLoader;

import exceptions.MissingAssetException;
import javafx.scene.image.Image;
import model.rooms.Room;
import model.rooms.RoomType;

public class AssetRoomLoader {
    public static Image loadBackground(RoomType room) {
        switch (room) {
            case SIMPLE_ROOM:
                break;
            case MONSTER_ROOM:
                break;
            case BOSS_ROOM:
                break;

            default: throw new MissingAssetException();
        }

        return null;
    }

    public static Image loadDoors() {
        throw new MissingAssetException();
    }

    public static Room loadBackground(Room room) {
        throw new MissingAssetException();
    }
}
