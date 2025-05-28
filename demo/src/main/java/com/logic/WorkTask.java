package com.logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkTask extends Task {
    private LocalDateTime dueDate;
    private boolean isOverdue = false;

    public WorkTask(String name, String description, boolean done, LocalDateTime dueDate) {
        super(name, description);
        this.dueDate = dueDate;
        this.done = done;
        checkOverdue(); // langsung periksa saat dibuat
    }

    // Getter
    public LocalDateTime getDeadline() {
        return dueDate;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    // Setter
    public void setDeadline(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        checkOverdue(); // periksa ulang jika tanggal diubah
    }

    // Cek apakah task sudah melewati deadline
    public void checkOverdue() {
        if (!done && dueDate != null && LocalDateTime.now().isAfter(dueDate)) {
            isOverdue = true;
        } else {
            isOverdue = false;
        }
    }
}
