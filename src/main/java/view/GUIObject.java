package view;

import javafx.scene.Node;

import java.util.List;

public interface GUIObject {
    void render();
    Node getFxModel();
    List<Node> getFxModels();
}
