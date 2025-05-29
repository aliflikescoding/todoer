package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.logic.TaskManager;

public class HomeController {
    private TaskManager shoppingManager;
    private TaskManager workManager;
    private TaskManager personalManager;

    @FXML
    private Label shoppingCountLabel;

    @FXML
    private Label workCountLabel;

    @FXML
    private Label personalCountLabel;

    public void initialize() {
        this.shoppingManager = App.getShoppingManager();
        this.workManager = App.getWorkManager();
        this.personalManager = App.getPersonalManager();

        // Update the labels with actual counts
        updateStatistics();

        System.out.println(shoppingManager.getActiveTasksLength());
        System.out.println(workManager.getActiveTasksLength());
        System.out.println(workManager.getTotalTasksLength());
    }

    private void updateStatistics() {
        if (shoppingCountLabel != null && shoppingManager != null) {
            shoppingCountLabel.setText(String.valueOf(shoppingManager.getActiveTasksLength()));
        }

        if (workCountLabel != null && workManager != null) {
            workCountLabel.setText(String.valueOf(workManager.getActiveTasksLength()));
        }

        if (personalCountLabel != null && personalManager != null) {
            personalCountLabel.setText(String.valueOf(personalManager.getActiveTasksLength()));
        }
    }

    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
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