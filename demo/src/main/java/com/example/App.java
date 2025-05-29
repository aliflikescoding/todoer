package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/* object import */
import com.logic.TaskManager;
import com.database.DatabaseSetup;

public class App extends Application {
    private static Scene scene;
    private static TaskManager workManager = new TaskManager();
    private static TaskManager personalManager = new TaskManager();
    private static TaskManager shoppingManager = new TaskManager();


    @Override
    public void start(Stage stage) throws IOException {
        // Load all icon sizes
        DatabaseSetup.initialize();

        workManager.loadFromDatabase("work_tasks");
        personalManager.loadFromDatabase("personal_tasks");
        shoppingManager.loadFromDatabase("shopping_tasks");

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

    @Override
    public void stop() {
        try {
            // Save current data to database first
            workManager.saveToDatabase("work_tasks");
            personalManager.saveToDatabase("personal_tasks");
            shoppingManager.saveToDatabase("shopping_tasks");

            System.out.println("Application closed - data saved to database");
        } catch (Exception e) {
            System.err.println("Error saving data on exit: " + e.getMessage());
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static TaskManager getWorkManager() {
        return workManager;
    }

    public static TaskManager getShoppingManager() {
        return shoppingManager;
    }

    public static TaskManager getPersonalManager() {
        return personalManager;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}