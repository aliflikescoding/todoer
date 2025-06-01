package com.example;

/**
 * Launcher class to avoid JavaFX module path issues when running from JAR
 * This class doesn't extend Application, which prevents module system conflicts
 */
public class Launcher {
  public static void main(String[] args) {
    // Launch the actual JavaFX Application
    App.main(args);
  }
}