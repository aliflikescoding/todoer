package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

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
        String name = nameField.getText();
        String description = descriptionField.getText();
        String storeName = storeNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        int price = Integer.parseInt(priceField.getText());

        System.out
                .println("Submitted: " + name + ", " + description + ", " + storeName + ", " + quantity + ", " + price);
        // TODO: Save or process the form data
    }
}
