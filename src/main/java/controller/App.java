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
import view.MainGUI;

public class App extends Application {
    public static final double WIDTH = 800,
                                HEIGHT = 600;

    // Please, do not touch at references
    public static final double WIDTH_REFERENCE = 800,
                                HEIGHT_REFERENCE = 600;

    public static final int FPS = 60;

    public static final Color BACKGROUND_COLOR = Color.DARKGRAY;
    public static final Color WALL_COLOR = Color.LIGHTGRAY;
    public static final int WALL_SIZE = 20;
    public static final int PLAYER_SIZE = 20;

    public static final int MONSTER_AI_LATENCY_MS = 250;

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
