package com.logic;

public class Task {
    protected String name;
    protected String description;
    protected boolean done;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.done = false;  // Default status task adalah belum selesai
    }

    // Getter dan Setter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    // Menandai task sebagai selesai
    public void markAsDone() {
        this.done = true;
    }

    // Menampilkan task
    public void displayTask() {
        System.out.println("📋 [Task]");
        System.out.println("Name       : " + name);
        System.out.println("Description: " + description);
        System.out.println("Status     : " + (done ? "Done ✅" : "Pending ⏳"));
        System.out.println("-----------------------------------");
    }
}
