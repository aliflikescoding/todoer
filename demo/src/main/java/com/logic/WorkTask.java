package com.logic;

import java.time.LocalDate;

public class WorkTask extends Task {
    private LocalDate dueDate;
    private boolean isOverdue = false;

    public WorkTask(String name, String description, LocalDate dueDate) {
        super(name, description);
        this.dueDate = dueDate;
        checkOverdue(); // check immediately when created
    }

    // Getter
    public LocalDate getDeadline() {
        return dueDate;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    // Setter
    public void setDeadline(LocalDate dueDate) {
        this.dueDate = dueDate;
        checkOverdue(); // re-check if date is changed
    }

    // Check if task has passed its deadline
    public void checkOverdue() {
        if (!done && dueDate != null && LocalDate.now().isAfter(dueDate)) {
            isOverdue = true;
        } else {
            isOverdue = false;
        }
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
        setArchived(true);
    }

    @Override
    public String toString() {
        return String.format(
                "{\"name\": \"%s\", \"description\": \"%s\", \"done\": %b, \"archived\": %b, \"dueDate\": \"%s\", \"isOverdue\": %b}",
                name, description, done, archived, dueDate, isOverdue);
    }
}