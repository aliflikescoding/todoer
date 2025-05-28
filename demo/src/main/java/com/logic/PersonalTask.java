public class PersonalTask extends Task {
    public PersonalTask(String name, String desc){
        super(name, desc);
    }

    @Override
    public void displayTask(){
        System.out.println("[Tugas pribadi]" +name+ ", " + desc + "(selesai :)" + done +")");
    } 

    @Override
    public String getType(){
        return "Pribadi";
    }
}