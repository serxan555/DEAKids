/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dea.controllers;

/**
 *
 * @author SS.555
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    

public class TitledPaneExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Some content");
        Button button = new Button("OK");
        VBox content = new VBox(10, label, button);
        TitledPane titledPane = new TitledPane("Titled Pane", content);
        button.setOnAction(e -> titledPane.setExpanded(false));
        VBox root = new VBox(titledPane);
        Scene scene = new Scene(root, 250, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
