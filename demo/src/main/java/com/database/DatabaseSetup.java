package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseSetup {
  public static void initialize() {
    try (Connection conn = SQLiteConnection.getConnection();
        Statement stmt = conn.createStatement()) {

      stmt.execute("""
              CREATE TABLE IF NOT EXISTS work_tasks (
                  name TEXT,
                  description TEXT,
                  due_date TEXT,
                  done INTEGER,
                  is_overdue INTEGER
              )
          """);

      stmt.execute("""
              CREATE TABLE IF NOT EXISTS personal_tasks (
                  name TEXT,
                  description TEXT,
                  done INTEGER,
                  donetime TEXT
              )
          """);

      stmt.execute("""
              CREATE TABLE IF NOT EXISTS shopping_tasks (
                  name TEXT,
                  description TEXT,
                  store_name TEXT,
                  quantity INTEGER,
                  price REAL,
                  done INTEGER
              )
          """);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void cleanDatabase() {
    try (Connection conn = SQLiteConnection.getConnection();
        Statement stmt = conn.createStatement()) {

      stmt.execute("DELETE FROM work_tasks");
      stmt.execute("DELETE FROM personal_tasks");
      stmt.execute("DELETE FROM shopping_tasks");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}