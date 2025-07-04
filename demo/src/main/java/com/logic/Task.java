package com.logic;

public abstract class Task {
    protected String name;
    protected String description;
    protected boolean done = false;
    protected boolean archived = false;

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

    public boolean isArchived() {
        return archived;
    }

    // Setter
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    // Tandai task selesai
    public void markAsDone() {
        this.done = true;
    }

    public String toString() {
        return String.format(
            "{\"name\":\"%s\", \"description\":\"%s\", \"done\":%b, \"archived\":%b}",
            name, description, done, archived
        );
    }
}
