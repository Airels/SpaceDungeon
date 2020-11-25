package model.entities.characters.players;

import model.Direction;
import model.Observer;
import model.items.Item;

public class PlayerObserver implements Observer {
    private Player player;

    public PlayerObserver(Player player) {
        this.player = player;
    }

    @Override
    public void notify(int arg) {
        switch (arg) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                player.useItem(arg);
                break;
            case 10:
                player.move(Direction.UP);
                break;
            case 11:
                player.move(Direction.LEFT);
                break;
            case 12:
                player.move(Direction.DOWN);
                break;
            case 13:
                player.move(Direction.RIGHT);
                break;
            case 14:
                player.attack();
                break;
            case 15:
                player.action();
                break;
            default:
                throw new IllegalArgumentException("Unknown argument: " + arg);
        }
    }
}
