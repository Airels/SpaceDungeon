package model.entities.characters.players;

import model.Direction;
import model.Game;
import utils.Observer;

public class PlayerObserver implements Observer {
    private static PlayerObserver instance;
    private final Player player;

    public PlayerObserver(Player player) {
        instance = this;
        this.player = player;
    }

    @Override
    public void notify(int arg) {
        if (player.isDead() || Game.getInstance().isPaused()) return;

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
            case 16:
                player.moveToDoor(Direction.UP);
                break;
            case 17:
                player.moveToDoor(Direction.LEFT);
                break;
            case 18:
                player.moveToDoor(Direction.DOWN);
                break;
            case 19:
                player.moveToDoor(Direction.RIGHT);
                break;
            default:
                throw new IllegalArgumentException("Unknown argument: " + arg);
        }
    }

    public static PlayerObserver getInstance() {
        return instance;
    }
}
