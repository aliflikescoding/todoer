package com.example;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
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

        workManager.addTask(new WorkTask(name, description, deadline));
        App.setRoot("work");
        // TODO: Save or process the form data
    }
}