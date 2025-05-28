package com.logic;
import java.time.*;

public class PersonalTask extends Task {
    private LocalDateTime donetime;

    public PersonalTask(String name, String description, Bool done) {
        super(name, description, done);
        this.donetime = null;
    }

    @Override
    public void markAsDone() {
        this,done = true;
        this.donetime = LocalDateTime.now();
    }

    public void checkstatus() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(donetime)) {
            this.setDone(false);
        }
    }
}
