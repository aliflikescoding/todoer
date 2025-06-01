package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class AllController implements Initializable {

    @FXML
    private VBox mainContentContainer;

    private String[] dataArray = { "Task 1: Buy groceries", "Task 2: Finish report", "Task 3: Call Mom",
            "Task 4: Go to gym" };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Use Platform.runLater to ensure FXML injection is complete
        javafx.application.Platform.runLater(() -> {
            if (mainContentContainer != null) {
                populateMainContent();
            } else {
                System.err.println("mainContentContainer is still null after Platform.runLater!");
            }
        });
    }

    private void populateMainContent() {
        if (mainContentContainer == null) {
            System.err.println("mainContentContainer is null!");
            return;
        }

        // Clear any existing children
        mainContentContainer.getChildren().clear();

        // Add title
        Label homeViewLabel = new Label("Your Tasks");
        homeViewLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10px;");
        mainContentContainer.getChildren().add(homeViewLabel);

        // Loop through the dataArray and create UI elements
        for (String task : dataArray) {
            HBox taskRow = new HBox(10);
            taskRow.setAlignment(javafx.geometry.Pos.CENTER);

            Label taskLabel = new Label(task);
            taskLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #333;");

            Button completeButton = new Button("Complete");
            completeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
            completeButton.setOnAction(event -> {
                System.out.println("Task completed: " + task);
                mainContentContainer.getChildren().remove(taskRow);
            });

            taskRow.getChildren().addAll(taskLabel, completeButton);
            mainContentContainer.getChildren().add(taskRow);
        }
    }

    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home"); // This loads the home FXML
        // Don't call populateMainContent() here - let initialize() handle it
    }

    @FXML
    private void switchToPersonal() throws IOException {
        App.setRoot("personal");
    }

    @FXML
    private void switchToWork() throws IOException {
        App.setRoot("work");
    }

    @FXML
    private void switchToShopping() throws IOException {
        App.setRoot("shopping");
    }
}