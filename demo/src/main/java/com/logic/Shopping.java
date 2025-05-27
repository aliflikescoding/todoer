package com.logic;

public class Shopping extends Task {

    public Shopping(String name, String description) {
        super(name, description);
    }

    @Override
    public void displayTask() {
        System.out.println("🛒 [Shopping Task]");
        System.out.println("Name       : " + name);
        System.out.println("Description: " + description);
        System.out.println("Status     : " + (done ? "Done ✅" : "Pending ⏳"));
        System.out.println("-----------------------------------");
    }
}
