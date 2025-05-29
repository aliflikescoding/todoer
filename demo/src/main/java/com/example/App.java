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
import com.logic.Shopping;
import com.logic.WorkTask;
import com.logic.PersonalTask;

public class App extends Application {
    private static Scene scene;
    private static TaskManager workManager = new TaskManager();
    private static TaskManager personalManager = new TaskManager();
    private static TaskManager shoppingManager = new TaskManager();


    @Override
    public void start(Stage stage) throws IOException {
        shoppingManager.addTask(new Shopping("test 1", "test 1", "wallmart", 3, 3.99));
        shoppingManager.addTask(new Shopping("test 2", "test 2", "target", 4, 4.99));
        shoppingManager.addTask(new Shopping("test 3", "test 3", "wallgreens", 5, 5.99));
        workManager.addTask(new WorkTask("test 1", "test 1", java.time.LocalDate.of(2025, 5, 31)));
        workManager.addTask(new WorkTask("test 2", "test 2", java.time.LocalDate.of(2025, 6, 1)));
        workManager.addTask(new WorkTask("test 3", "test 3", java.time.LocalDate.of(2025, 6, 2)));
        personalManager.addTask(new PersonalTask("test 1", "test 1"));
        personalManager.addTask(new PersonalTask("test 2", "test 2"));
        personalManager.addTask(new PersonalTask("test 3", "test 3"));

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