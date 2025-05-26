public class PersonalTask extends Task {

    public PersonalTask(String name, String description) {
        super(name, description);
    }

    @Override
    public void displayTask() {
        System.out.println("🏠 [Personal Task]");
        System.out.println("Name       : " + name);
        System.out.println("Description: " + description);
        System.out.println("Status     : " + (done ? "Done ✅" : "Pending ⏳"));
        System.out.println("-----------------------------------");
    }
}
