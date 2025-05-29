package com.logic;

public abstract class Task {
    protected String name;
    protected String description;
    protected boolean done = false;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    // Setter
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    // Tandai task selesai
    public void markAsDone() {
        this.done = true;
    }

    public String toString() {
        return String.format(
                "{\"name\":\"%s\", \"description\":\"%s\", \"done\":%b}",
                name, description, done);
    }
}
