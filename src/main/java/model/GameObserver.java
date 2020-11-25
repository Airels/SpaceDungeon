package model;

public class GameObserver implements Observer {
    private Game game;

    public GameObserver(Game game) {
        this.game = game;
    }

    @Override
    public void notify(int arg) {
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
