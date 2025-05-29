package com.example;

import java.io.IOException;

import javafx.fxml.FXML;

public class ArchiveController {
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
