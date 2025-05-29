package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.File;

public class SQLiteConnection {
  public static Connection getConnection() {
    try {
      String os = System.getProperty("os.name").toLowerCase();
      String dbDir;

      // Determine OS-specific data directory
      if (os.contains("win")) {
        dbDir = System.getenv("APPDATA") + File.separator + "Todoer";
      } else if (os.contains("mac")) {
        dbDir = System.getProperty("user.home") + "/Library/Application Support/Todoer";
      } else { // Linux/Unix
        dbDir = System.getProperty("user.home") + "/.todoer";
      }

      // Create directory if it doesn't exist
      File dir = new File(dbDir);
      if (!dir.exists()) {
        dir.mkdirs();
      }

      String dbPath = dbDir + File.separator + "todoer.db";
      String url = "jdbc:sqlite:" + dbPath;

      System.out.println("Database path: " + url); // Debug
      return DriverManager.getConnection(url);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}