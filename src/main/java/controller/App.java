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
import model.entities.characters.players.PlayerObserver;
import model.entities.characters.players.PlayerType;
import view.GraphicEngine;

public class App extends Application {

    /* === BEGIN SETTINGS === */

    /** GENERAL GAME SETTINGS **/
    public static final double
            WIDTH = 800,
            HEIGHT = 600;

    public static final int FPS = 60;

    /** ROOM CUSTOMIZATIONS SETTINGS **/
    public static final Color BACKGROUND_COLOR = Color.DARKGRAY,
            WALL_COLOR = Color.LIGHTGRAY,
            DOOR_COLOR = Color.GOLD;

    public static final int WALL_SIZE = 20;

    /** ENTITIES AND MOVEMENTS SETTINGS **/
    public static final int DEFAULT_ENTITY_SIZE = 30,
            MOVE_STEP_SIZE = 10;

    /** NOTIFICATIONS AND TEXT FONT SETTINGS **/
    public static final int DEFAULT_NOTIFICATION_DURATION = 5000,
            NOTIFICATION_MARGIN = 25,
            NOTIFICATION_FONT_SIZE = 15;
    public static final String TEXT_FONT_FAMILY = "verdana";

    /** HEALTH BAR MARGIN SETTING **/
    public static final int HEALTH_BAR_MARGIN = 40;

    /** PLAYER TYPE SETTING **/
    public static final PlayerType PLAYER_TYPE = PlayerType.NORMAL;

    /* === END SETTINGS == */

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
