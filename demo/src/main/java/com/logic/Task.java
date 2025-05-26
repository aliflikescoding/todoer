abstract class Task {
    protected String name;
    protected String description;
    protected Boolean done;

    // Getter setters
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getIsDone() {
        return this.done;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDone(Boolean done) {
        this.done = done;
    }

    public abstract void displayTask();

    public abstract String getType();

}