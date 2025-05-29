package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import com.logic.TaskManager;
import com.logic.Shopping;
public class CreateShoppingController {
    private TaskManager shoppingManager = App.getShoppingManager();

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
    private void handleSubmit() throws IOException {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String storeName = storeNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText()); // Changed from parseInt to parseDouble

        Shopping shoppingTask = new Shopping(name, description, storeName, quantity, price);
        shoppingManager.addTask(shoppingTask);
        try {
            App.setRoot("shopping");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error switching to shopping view: " + e.getMessage());
        }
    }

}
