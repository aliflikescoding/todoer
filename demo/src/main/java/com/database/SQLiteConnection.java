package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.File;
import java.net.URISyntaxException;

public class SQLiteConnection {
  public static Connection getConnection() {
    try {
      // For development, use a fixed path or user home directory
      String dbPath = System.getProperty("user.home") + File.separator + "todoer.db";
      String url = "jdbc:sqlite:" + dbPath;
      System.out.println("Database path: " + url); // Debug output
      return DriverManager.getConnection(url);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
