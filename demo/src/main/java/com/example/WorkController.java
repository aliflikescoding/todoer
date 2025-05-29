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
import com.logic.WorkTask;

public class WorkController {
  private TaskManager workManager;

  @FXML
  private VBox taskContainer; // This will reference the VBox in FXML where tasks will be displayed

  public void initialize() {
    this.workManager = App.getWorkManager();
    displayActiveTasks();
  }

  private void displayActiveTasks() {
    taskContainer.getChildren().clear();

    System.out.println(this.workManager.getActive());

    this.workManager.getActive().stream()
        .filter(task -> task instanceof WorkTask)
        .map(task -> (WorkTask) task)
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

          Label detailsLabel = new Label(String.format("%s • Due: %s",
              task.getDescription(),
              task.getDeadline()));
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
            workManager.markTaskDone(task.getName());
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
  private void switchToCreateWork() {
    try {
      System.out.println("Attempting to load createWork.fxml from: " +
          App.class.getResource("createWork.fxml"));
      App.setRoot("createWork");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Error loading createWork.fxml: " + e.getMessage());
    }
  }
}