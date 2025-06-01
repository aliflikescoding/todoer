package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

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
        String name = nameField.getText();
        String description = descriptionField.getText();

        System.out
                .println("Submitted: " + name + ", " + description);
        // TODO: Save or process the form data
    }
}
