package com.example;

import java.io.IOException;

import javafx.fxml.FXML;

public class PersonalController {
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
  private void switchToCreatePersonal() throws IOException {
    try {
      System.out.println("Attempting to load createShopping.fxml from: " +
          App.class.getResource("createPersonal.fxml"));
      App.setRoot("createPersonal");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Error loading createShopping.fxml: " + e.getMessage());
    }
  }
}
