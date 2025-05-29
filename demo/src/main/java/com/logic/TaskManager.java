package com.logic;

import java.time.LocalDate; // Changed from LocalDateTime
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return String.format(
                "{\"tasks\": }",
                tasks);
    }
}