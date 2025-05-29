package com.logic;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.database.SQLiteConnection;
import java.time.format.DateTimeFormatter;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean removetask(String taskName) {
        return tasks.removeIf(task -> task.getName().equals(taskName));
    }

    public boolean markTaskDone(String taskName) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getName().equals(taskName))
                .findFirst();

        if (task.isPresent()) {
            task.get().markAsDone();
            return true;
        } else {
            return false;
        }
    }

    public List<Task> showAllTasks() {
        return tasks;
    }

    public Optional<Task> showTaskByName(String taskName) {
        return tasks.stream()
                .filter(task -> task.getName().equals(taskName))
                .findFirst();
    }

    public List<WorkTask> getNotOverdueWorkTasks() {
        LocalDate today = LocalDate.now();
        return tasks.stream()
                .filter(task -> task instanceof WorkTask)
                .map(task -> (WorkTask) task)
                .filter(workTask -> !workTask.isDone() &&
                        (workTask.getDeadline().isAfter(today) || workTask.getDeadline().isEqual(today)))
                .collect(Collectors.toList());
    }

    public List<WorkTask> getOverdueWorkTasks() {
        return tasks.stream()
                .filter(task -> task instanceof WorkTask)
                .map(task -> (WorkTask) task)
                .filter(workTask -> workTask.getDeadline().isBefore(LocalDate.now())) // Changed to LocalDate
                .collect(Collectors.toList());
    }

    public List<Task> showPendingTasks() {
        return tasks.stream()
                .filter(task -> !task.isDone())
                .collect(Collectors.toList());
    }

    public List<Task> getArchived() {
        return tasks.stream()
                .filter(Task::isDone)
                .collect(Collectors.toList());
    }

    // New method to get active tasks (where done is false)
    public List<Task> getActive() {
        return tasks.stream()
                .filter(task -> !task.isDone())
                .collect(Collectors.toList());
    }

    public int getActiveTasksLength() {
        return (int) tasks.stream()
                .filter(task -> !task.isDone())
                .count();
    }
    
    public int getTotalTasksLength() {
        return tasks.size();
    }
    
    public int getArchivedSize() {
        return (int) tasks.stream()
                .filter(Task::isDone)
                .count();
    }

        public void loadFromDatabase(String tableName) {
        try (Connection conn = SQLiteConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)) {
            
            // Clear current tasks before loading
            tasks.clear();
            
            while (rs.next()) {
                switch (tableName) {
                    case "work_tasks":
                        loadWorkTask(rs);
                        break;
                    case "personal_tasks":
                        loadPersonalTask(rs);
                        break;
                    case "shopping_tasks":
                        loadShoppingTask(rs);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadWorkTask(ResultSet rs) throws Exception {
        String name = rs.getString("name");
        String description = rs.getString("description");
        LocalDate dueDate = LocalDate.parse(rs.getString("due_date"));
        boolean done = rs.getInt("done") == 1;
        boolean isOverdue = rs.getInt("is_overdue") == 1;
        
        WorkTask task = new WorkTask(name, description, dueDate);
        if (done) task.markAsDone();
        if (isOverdue) task.checkOverdue();
        tasks.add(task);
    }

    private void loadPersonalTask(ResultSet rs) throws Exception {
        String name = rs.getString("name");
        String description = rs.getString("description");
        boolean done = rs.getInt("done") == 1;
        String donetimeStr = rs.getString("donetime");
        
        PersonalTask task = new PersonalTask(name, description);
        if (done && donetimeStr != null) {
            task.setDonetime(LocalDateTime.parse(donetimeStr));
            task.markAsDone();
        }
        tasks.add(task);
    }

    private void loadShoppingTask(ResultSet rs) throws Exception {
        String name = rs.getString("name");
        String description = rs.getString("description");
        String storeName = rs.getString("store_name");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        boolean done = rs.getInt("done") == 1;
        
        Shopping task = new Shopping(name, description, storeName, quantity, price);
        if (done) task.markAsDone();
        tasks.add(task);
    }


    public void saveToDatabase(String tableName) {
        Connection conn = null;
        try {
            conn = SQLiteConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Clear existing entries for this manager
            try (PreparedStatement clearStmt = conn.prepareStatement(
                    "DELETE FROM " + tableName)) {
                clearStmt.executeUpdate();
            }

            // Save each task
            for (Task task : tasks) {
                if (tableName.equals("work_tasks") && task instanceof WorkTask) {
                    saveWorkTask(conn, (WorkTask) task);
                } else if (tableName.equals("personal_tasks") && task instanceof PersonalTask) {
                    savePersonalTask(conn, (PersonalTask) task);
                } else if (tableName.equals("shopping_tasks") && task instanceof Shopping) {
                    saveShoppingTask(conn, (Shopping) task);
                }
            }

            conn.commit(); // Commit transaction
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception ex) {
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private void saveWorkTask(Connection conn, WorkTask task) throws Exception {
        String sql = "INSERT INTO work_tasks (name, description, due_date, done, is_overdue) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getDeadline().toString());
            pstmt.setInt(4, task.isDone() ? 1 : 0);
            pstmt.setInt(5, task.isOverdue() ? 1 : 0);
            pstmt.executeUpdate();
        }
    }

    private void savePersonalTask(Connection conn, PersonalTask task) throws Exception {
        String sql = "INSERT INTO personal_tasks (name, description, done, donetime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setInt(3, task.isDone() ? 1 : 0);
            pstmt.setString(4, task.getDonetime() != null ? 
                task.getDonetime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null);
            pstmt.executeUpdate();
        }
    }

    private void saveShoppingTask(Connection conn, Shopping task) throws Exception {
        String sql = "INSERT INTO shopping_tasks (name, description, store_name, quantity, price, done) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getStoreName());
            pstmt.setInt(4, task.getQuantity());
            pstmt.setDouble(5, task.getPrice());
            pstmt.setInt(6, task.isDone() ? 1 : 0);
            pstmt.executeUpdate();
        }
    }


    @Override
    public String toString() {
        return String.format(
                "{\"tasks\": }",
                tasks);
    }
}