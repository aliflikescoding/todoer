package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CreateShoppingController {

    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField storeNameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;

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
        String storeName = storeNameField.getText().trim();
        String quantityText = quantityField.getText().trim();
        String priceText = priceField.getText().trim();

        // Validate the input
        if (!isValidInput(name, description, storeName, quantityText, priceText)) {
            return; // Exit if validation fails
        }

        // Parse the validated numeric values
        int quantity = Integer.parseInt(quantityText);
        int price = Integer.parseInt(priceText);

        System.out
                .println("Submitted: " + name + ", " + description + ", " + storeName + ", " + quantity + ", " + price);
        // TODO: Save or process the form data
    }

    private boolean isValidInput(String name, String description, String storeName, String quantityText,
            String priceText) {
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

        // Validate store name
        if (storeName.isEmpty()) {
            errorMessage.append("• Store name cannot be empty\n");
        } else if (storeName.length() > 25) {
            errorMessage.append("• Store name cannot exceed 25 characters\n");
        }

        // Validate quantity
        if (quantityText.isEmpty()) {
            errorMessage.append("• Quantity cannot be empty\n");
        } else {
            try {
                int quantity = Integer.parseInt(quantityText);
                if (quantity < 0) {
                    errorMessage.append("• Quantity cannot be negative\n");
                } else if (quantity > 9999) {
                    errorMessage.append("• Quantity cannot exceed 9999\n");
                }
            } catch (NumberFormatException e) {
                errorMessage.append("• Quantity must be a valid number\n");
            }
        }

        // Validate price
        if (priceText.isEmpty()) {
            errorMessage.append("• Price cannot be empty\n");
        } else {
            try {
                int price = Integer.parseInt(priceText);
                if (price < 0) {
                    errorMessage.append("• Price cannot be negative\n");
                } else if (price > 9999) {
                    errorMessage.append("• Price cannot exceed 9999\n");
                }
            } catch (NumberFormatException e) {
                errorMessage.append("• Price must be a valid number\n");
            }
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