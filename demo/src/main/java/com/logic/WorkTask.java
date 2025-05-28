package com.logic;
import java.time.*;

public class WorkTask extends Task {
    private LocalDateTime dueDate;
    private boolean isOverdue = false;

    public WorkTask(String name, String description, boolean done, LocalDateTime dueDate) {
        super(name, description);  // Memanggil konstruktor Task
        this.dueDate = dueDate;
    }

    public void checkOverdue() {
        // kalo now date > due date, is overdue = true
    }

}
