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

    @Override
    public String toString() {
        return String.format(
                "{\"tasks\": }",
                tasks);
    }
}