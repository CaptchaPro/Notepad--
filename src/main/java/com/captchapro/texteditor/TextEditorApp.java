package com.captchapro.texteditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TextEditorApp extends Application {
    private static final int WINDOW_LENGTH = 640;
    private static final int WINDOW_WIDTH = 480;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TextEditorApp.class.getResource("TextEditorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_LENGTH, WINDOW_WIDTH);
        stage.setTitle("Notepad--");
        stage.setScene(scene);
        stage.show();
    }
}