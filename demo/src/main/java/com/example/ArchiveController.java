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
import com.logic.Shopping;

public class ArchiveController {
  private TaskManager workManager;
  private TaskManager shoppingManager;

  @FXML
  private VBox taskContainer;

  public void initialize() {
    this.workManager = App.getWorkManager();
    this.shoppingManager = App.getShoppingManager();
    displayArchivedTasks();
  }

  private void displayArchivedTasks() {
    taskContainer.getChildren().clear();

    // Display archived work tasks
    this.workManager.getArchived().stream()
        .filter(task -> task instanceof WorkTask)
        .map(task -> (WorkTask) task)
        .forEach(task -> {
          createTaskItem(
              task.getName(),
              String.format("%s • Due: %s", task.getDescription(), task.getDeadline()));
        });

    // Display archived shopping tasks
    this.shoppingManager.getArchived().stream()
        .filter(task -> task instanceof Shopping)
        .map(task -> (Shopping) task)
        .forEach(task -> {
          createTaskItem(
              task.getName(),
              String.format("%s • %d x $%.2f • %s",
                  task.getDescription(),
                  task.getQuantity(),
                  task.getPrice(),
                  task.getStoreName()));
        });
  }

  private void createTaskItem(String name, String details) {
    // Create container HBox for each task
    HBox taskItem = new HBox();
    taskItem.getStyleClass().add("task-item");
    taskItem.setSpacing(10);

    // Task content
    VBox taskContent = new VBox();
    taskContent.getStyleClass().add("task-content");
    HBox.setHgrow(taskContent, Priority.ALWAYS);

    Label nameLabel = new Label(name);
    nameLabel.getStyleClass().add("task-name");

    Label detailsLabel = new Label(details);
    detailsLabel.getStyleClass().add("task-details");

    taskContent.getChildren().addAll(nameLabel, detailsLabel);

    // Spacer to push button to the right
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    // Done button (disabled in archive)
    Button doneButton = new Button("Done");
    doneButton.getStyleClass().add("button");
    doneButton.getStyleClass().add("button-primary");
    doneButton.setDisable(true);

    taskItem.getChildren().addAll(taskContent, spacer, doneButton);
    taskContainer.getChildren().add(taskItem);
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
}