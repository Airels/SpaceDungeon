package view;

import javafx.scene.Node;

import java.util.List;

public interface GUIObject {
    void render();
    List<Node> getFxModels();
}
