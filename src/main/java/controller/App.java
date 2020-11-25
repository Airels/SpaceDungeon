package controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Game;
import model.GameObserver;
import model.entities.characters.players.Player;
import model.entities.characters.players.PlayerObserver;
import model.entities.characters.players.PlayerType;
import view.GraphicEngine;

public class App extends Application {
    public static final double
            WIDTH = 800,
            HEIGHT = 600;

    // Please, do not touch at these references
    public static final double
            WIDTH_REFERENCE = 800,
            HEIGHT_REFERENCE = 600;

    public static final int FPS = 60;

    public static final Color BACKGROUND_COLOR = Color.DARKGRAY,
            WALL_COLOR = Color.LIGHTGRAY,
            DOOR_COLOR = Color.GOLD;
    public static final int WALL_SIZE = 20;

    public static final int DEFAULT_ENTITY_SIZE = 30;
    public final static int MOVE_STEP_SIZE = 10;

    public static final int DEFAULT_NOTIFICATION_DURATION = 5000,
        NOTIFICATION_MARGIN = 20,
        NOTIFICATION_FONT_SIZE = 15;
    public static final String NOTIFICATION_FONT_FAMILY = "verdana";

    public static final int HEALTH_BAR_MARGIN = 40;

    public static final PlayerType PLAYER_TYPE = PlayerType.NORMAL;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Space Dungeon");
        Group root = new Group();

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR));
        // primaryStage.setFullScreen(true);
        // primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setResizable(false);
        primaryStage.show();


        Game game = new Game(PLAYER_TYPE);
        GraphicEngine gui = new GraphicEngine(root);

        GameObserver gameObserver = new GameObserver(game);
        PlayerObserver playerObserver = new PlayerObserver(game.getPlayer());

        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, JavaFXController.getKeyEventHandler(gameObserver, playerObserver));
        primaryStage.addEventHandler(MouseEvent.MOUSE_PRESSED, JavaFXController.getMousePressedEventHandler(playerObserver));

        game.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
