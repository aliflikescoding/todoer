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
import com.logic.Shopping;

public class ShoppingController {
  private TaskManager shoppingManager;

  @FXML
  private VBox taskContainer;

  public void initialize() {
    this.shoppingManager = App.getShoppingManager();
    displayActiveTasks();
  }

  private void displayActiveTasks() {
    taskContainer.getChildren().clear();

    this.shoppingManager.getActive().stream()
        .filter(task -> task instanceof Shopping)
        .map(task -> (Shopping) task)
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

          Label detailsLabel = new Label(String.format("%s • %d x $%.2f • %s",
              task.getDescription(),
              task.getQuantity(),
              task.getPrice(),
              task.getStoreName()));
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
            shoppingManager.markTaskDone(task.getName());
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
  private void switchToCreateShopping() {
    try {
      System.out.println("Attempting to load createShopping.fxml from: " +
          App.class.getResource("createShopping.fxml"));
      App.setRoot("createShopping");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Error loading createShopping.fxml: " + e.getMessage());
    }
  }
}