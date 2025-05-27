package com.logic;

public class WorkTask extends Task {
    // Constructor
    public WorkTask(String name, String description) {
        this.name = name;
        this.description = description;
        this.done = false;
    }

    // Getter and Setter
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

    // Method tambahan
    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public void displayTask() {
        System.out.println("📋 [Work Task]");
        System.out.println("Name       : " + name);
        System.out.println("Description: " + description);
        System.out.println("Status     : " + (done ? "Done ✅" : "Pending ⏳"));
        System.out.println("-----------------------------------");
    }
}
