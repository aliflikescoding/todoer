package com.logic;

public abstract class Task {
    protected String name;
    protected String description;
    protected boolean done;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.done = false;
    }

    // Getter dan Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    // Method umum yang bisa diimplementasikan ulang oleh anak
    public abstract void displayTask();
}