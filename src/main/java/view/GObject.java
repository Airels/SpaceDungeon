package view;

import javafx.scene.Node;

import java.util.List;

public interface GObject {
    void render();
    List<Node> getFxModels();
}
