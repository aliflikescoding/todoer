package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import com.logic.TaskManager;
import com.logic.PersonalTask;
import java.time.format.DateTimeFormatter;

public class PersonalController {
  private TaskManager personalManager;

  @FXML
  private VBox taskContainer;

  public void initialize() {
    this.personalManager = App.getPersonalManager();
    displayActiveTasks();
  }

  private void displayActiveTasks() {
    taskContainer.getChildren().clear();

    this.personalManager.getActive().stream()
        .filter(task -> task instanceof PersonalTask)
        .map(task -> (PersonalTask) task)
        .forEach(task -> {
          // Create container HBox for each task
          HBox taskItem = new HBox();
          taskItem.getStyleClass().add("task-item");
          taskItem.setSpacing(10);

          // Task content
          VBox taskContent = new VBox();
          taskContent.getStyleClass().add("task-content");
          HBox.setHgrow(taskContent, Priority.ALWAYS);

          Label nameLabel = new Label(task.getName());
          nameLabel.getStyleClass().add("task-name");

          String details = task.getDescription();
          if (task.getDonetime() != null) {
            details += " • Completed at: " +
                task.getDonetime().format(DateTimeFormatter.ofPattern("MMM dd, hh:mm a"));
          }

          Label detailsLabel = new Label(details);
          detailsLabel.getStyleClass().add("task-details");

          taskContent.getChildren().addAll(nameLabel, detailsLabel);

          // Spacer to push button to the right
          Region spacer = new Region();
          HBox.setHgrow(spacer, Priority.ALWAYS);

          // Done button
          Button doneButton = new Button("Done");
          doneButton.getStyleClass().add("button");
          doneButton.getStyleClass().add("button-primary");
          doneButton.setOnAction(e -> {
            personalManager.markTaskDone(task.getName());
            displayActiveTasks(); // Refresh the list
          });

          taskItem.getChildren().addAll(taskContent, spacer, doneButton);
          taskContainer.getChildren().add(taskItem);
        });
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

  @FXML
  private void switchToArchive() throws IOException {
    App.setRoot("archive");
  }

  @FXML
  private void switchToCreatePersonal() {
    try {
      System.out.println("Attempting to load createPersonal.fxml from: " +
          App.class.getResource("createPersonal.fxml"));
      App.setRoot("createPersonal");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Error loading createPersonal.fxml: " + e.getMessage());
    }
  }
}