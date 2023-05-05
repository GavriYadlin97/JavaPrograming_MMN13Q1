package com.example.maman13q1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Q1Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Q1Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 375, 400);
        stage.setTitle("MAMAN 13 Question 1- Sudoku");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}