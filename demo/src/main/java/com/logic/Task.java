package com.logic;

<<<<<<< Updated upstream
public class Task {
=======
abstract class Task {
>>>>>>> Stashed changes
    protected String name;
    protected String description;
    protected boolean done;
    protected boolean archived = false;

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

<<<<<<< Updated upstream
    public boolean isDone() {
        return done;
=======
    public void setDescription(String description) {
        this.description = description;
>>>>>>> Stashed changes
    }

    public void setDone(boolean done) {
        this.done = done;
    }

<<<<<<< Updated upstream
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
=======
    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return done;
    }

    public boolean isArchived() {
        return archived;
    }

    public abstract void updateStatus();
}
>>>>>>> Stashed changes
