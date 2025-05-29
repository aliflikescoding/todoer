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

    // Add overdue section header if there are overdue tasks
    if (!workManager.getOverdueWorkTasks().isEmpty()) {
      Label overdueHeader = new Label("⚠️ OVERDUE TASKS");
      overdueHeader.getStyleClass().add("overdue-header");
      taskContainer.getChildren().add(overdueHeader);

      // Display overdue tasks
      workManager.getOverdueWorkTasks().forEach(task -> {
        if (!task.isDone()) {
          taskContainer.getChildren().add(createTaskItem(task));
        }
      });

      // Add separator
      Region separator = new Region();
      separator.getStyleClass().add("separator");
      taskContainer.getChildren().add(separator);
    }

    // Add not overdue section header
    Label notOverdueHeader = new Label("UPCOMING TASKS");
    notOverdueHeader.getStyleClass().add("not-overdue-header");
    taskContainer.getChildren().add(notOverdueHeader);

    // Display not overdue tasks
    workManager.getNotOverdueWorkTasks().forEach(task -> {
      taskContainer.getChildren().add(createTaskItem(task));
    });
  }

  private HBox createTaskItem(WorkTask task) {
    // Create container HBox for each task
    HBox taskItem = new HBox();
    taskItem.getStyleClass().add("task-item");

    // Add specific style for overdue tasks
    if (workManager.getOverdueWorkTasks().contains(task)) {
      taskItem.getStyleClass().add("overdue-task");
    } else {
      taskItem.getStyleClass().add("");
    }

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
    return taskItem;
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