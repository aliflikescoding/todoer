package com.example;

import java.io.IOException;

import javafx.fxml.FXML;

public class WorkController {
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
      System.out.println("Attempting to load createShopping.fxml from: " +
          App.class.getResource("createWork.fxml"));
      App.setRoot("createWork");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Error loading createShopping.fxml: " + e.getMessage());
    }
  }
}
