package com.logic;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    // Menambahkan task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Menghapus task berdasarkan nama
    public void removeTask(String name) {
        tasks.removeIf(task -> task.getName().equalsIgnoreCase(name));
    }

    // Menampilkan semua task
    public void showAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Tidak ada tugas.");
        } else {
            tasks.forEach(Task::displayTask);
        }
    }

    // Menampilkan hanya yang belum selesai
    public void showPendingTasks() {
        tasks.stream()
             .filter(task -> !task.isDone())
             .forEach(Task::displayTask);
    }

    // Menandai task sebagai selesai
    public void markTaskDone(String name) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                task.setDone(true);
                System.out.println("Task '" + name + "' ditandai sebagai selesai.");
                return;
            }
        }
        System.out.println("Task dengan nama '" + name + "' tidak ditemukan.");
    }
}
