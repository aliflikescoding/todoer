package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Load all icon sizes
        loadIcons(stage, "/com/example/images/", new String[] {
                "app-icon-16x16.png",
                "app-icon-32x32.png",
                "app-icon-64x64.png",
                "app-icon-128x128.png"
        });

        scene = new Scene(loadFXML("home"));

        stage.setMaximized(true);
        stage.setMinWidth(1000);
        stage.setMinHeight(800);

        scene.getStylesheets().add(getClass().getResource("/com/example/style.css").toExternalForm());
        stage.setTitle("Todoer");
        stage.setScene(scene);
        stage.show();
    }

    private void loadIcons(Stage stage, String basePath, String[] iconFiles) {
        for (String iconFile : iconFiles) {
            try (InputStream iconStream = getClass().getResourceAsStream(basePath + iconFile)) {
                if (iconStream != null) {
                    stage.getIcons().add(new Image(iconStream));
                } else {
                    System.err.println("Icon not found: " + basePath + iconFile);
                }
            } catch (IOException e) {
                System.err.println("Error loading icon " + iconFile + ": " + e.getMessage());
            }
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}