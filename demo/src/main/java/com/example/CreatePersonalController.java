package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CreatePersonalController {
    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionField;

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

    @FXML
    private void handleSubmit() {
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();

        // Validate the input
        if (!isValidInput(name, description)) {
            return; // Exit if validation fails
        }

        System.out.println("Submitted: " + name + ", " + description);
        // TODO: Save or process the form data
    }

    private boolean isValidInput(String name, String description) {
        StringBuilder errorMessage = new StringBuilder();

        // Validate name
        if (name.isEmpty()) {
            errorMessage.append("• Name cannot be empty\n");
        } else if (name.length() > 25) {
            errorMessage.append("• Name cannot exceed 25 characters\n");
        }

        // Validate description
        if (description.isEmpty()) {
            errorMessage.append("• Description cannot be empty\n");
        } else if (description.length() > 50) {
            errorMessage.append("• Description cannot exceed 50 characters\n");
        }

        // If there are validation errors, show alert
        if (errorMessage.length() > 0) {
            showValidationAlert(errorMessage.toString());
            return false;
        }

        return true;
    }

    private void showValidationAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Please correct the following errors:");
        alert.setContentText(message);
        alert.showAndWait();
    }
}