package model;

import utils.Observer;

public class GameObserver implements Observer {
    private final Game game;

    public GameObserver(Game game) {
        this.game = game;
    }

    @Override
    public void notify(int arg) {
        if (game.getPlayer().isDead()) return;

        switch (arg) {
            case 0:
                if (game.isPaused()) game.play();
                else game.pause();
                break;
            default:
                throw new IllegalArgumentException("Unknown argument: " + arg);
        }
    }
}
