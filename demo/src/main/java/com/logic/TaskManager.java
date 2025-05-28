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

    public List<Task> showArchivedTasks() {
        return tasks.stream()
                .filter(Task::isArchived)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format(
                "{\"tasks\": }",
                tasks);
    }
}