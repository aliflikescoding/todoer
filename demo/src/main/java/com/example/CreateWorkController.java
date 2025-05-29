package com.example;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.logic.TaskManager;
import com.logic.WorkTask;

public class CreateWorkController {
    private TaskManager workManager = App.getWorkManager();

    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private DatePicker deadlinePicker;

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
    private void handleSubmit() throws IOException {
        String name = nameField.getText();
        String description = descriptionField.getText();
        LocalDate deadline = deadlinePicker.getValue();

        if (!isValidInput(name, description, deadline)) {
            return; // Exit if validation fails
        }

        workManager.addTask(new WorkTask(name, description, deadline));
        App.setRoot("work");
    }

    private boolean isValidInput(String name, String description, LocalDate deadline) {
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

        // Validate deadline
        if (deadline == null) {
            errorMessage.append("• Deadline must be selected\n");
        } else if (deadline.isBefore(LocalDate.now())) {
            errorMessage.append("• Deadline cannot be in the past\n");
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