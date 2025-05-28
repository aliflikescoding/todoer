package com.logic;
import java.time.LocalDateTime;

public class PersonalTask extends Task {
    private LocalDateTime donetime;

    public PersonalTask(String name, String description) {
        super(name, description);
        this.donetime = null;
    }

    public LocalDateTime getDonetime() {
        return donetime;
    }

    public void setDonetime(LocalDateTime donetime) {
        this.donetime = donetime;
    }

    @Override
    public void markAsDone() {
        this.done = true;
        this.donetime = LocalDateTime.now();
    }

    public void checkstatus() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(donetime)) {
            setDone(false);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "{\"name\":\"%s\", \"description\":\"%s\", \"done\":%b, \"donetime\":\"%s\"}",
                name, description, done, donetime);
    }
}
