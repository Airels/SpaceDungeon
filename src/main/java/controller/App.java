package controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Game;
import model.entities.characters.Player;
import model.entities.characters.PlayerType;
import model.generators.DungeonGenerator;
import model.generators.TestGenerator;
import view.MainGUI;

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

    public static final int MONSTER_AI_LATENCY_MS = 250;

    public static final Color CHEST_COLOR = Color.BROWN,
            KEYS_COLOR = Color.GOLD;

    public static final int DEFAULT_ENTITY_SIZE = 10;

    public static final int DEFAULT_NOTIFICATION_DURATION = 500,
        NOTIFICATION_MARGIN_WITH_PLAYER = 30,
        NOTIFICATION_FONT_SIZE = 15;
    public static final String NOTIFICATION_FONT_FAMILY = "verdana";


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Space Dungeon");
        Group root = new Group();

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR));
        // primaryStage.setFullScreen(true);
        // primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setResizable(false);
        primaryStage.show();


        Player player = new Player(PlayerType.NORMAL);

        Game game = new Game(player);
        MainGUI gui = new MainGUI(root);

        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, JavaFXController.getKeyEventHandler(player));
        primaryStage.addEventHandler(MouseEvent.MOUSE_PRESSED, JavaFXController.getMousePressedEventHandler(player));

        new Thread(game).start();
    }
}
