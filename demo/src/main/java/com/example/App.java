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

        scene = new Scene(loadFXML("primary"), 640, 480);
        scene.getStylesheets().add(getClass().getResource("/com/example/style.css").toExternalForm());
        stage.setTitle("Todoer");
        stage.setScene(scene);
        stage.show();
    }

    private void loadIcons(Stage stage, String basePath, String[] iconFiles) {
        for (String iconFile : iconFiles) {
            String fullPath = basePath + iconFile;
            System.out.println("Attempting to load: " + fullPath);

            // Try multiple ways to load the resource
            try {
                // Method 1: Using Class.getResourceAsStream
                InputStream iconStream = getClass().getResourceAsStream(fullPath);

                // Method 2: Using ClassLoader.getResourceAsStream
                if (iconStream == null) {
                    String classLoaderPath = fullPath.startsWith("/") ? fullPath.substring(1) : fullPath;
                    System.out.println("Trying ClassLoader with: " + classLoaderPath);
                    iconStream = getClass().getClassLoader().getResourceAsStream(classLoaderPath);
                }

                // Method 3: Try with App.class.getResource().toExternalForm()
                if (iconStream == null) {
                    System.out.println("Trying URL approach");
                    String urlPath = App.class.getResource(fullPath).toExternalForm();
                    stage.getIcons().add(new Image(urlPath));
                    System.out.println("Successfully loaded via URL: " + urlPath);
                    continue;
                }

                if (iconStream != null) {
                    stage.getIcons().add(new Image(iconStream));
                    System.out.println("Successfully loaded: " + fullPath);
                    iconStream.close();
                } else {
                    System.err.println("Icon not found: " + fullPath);
                }
            } catch (Exception e) {
                System.err.println("Error with icon " + iconFile + ": " + e.getMessage());
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