package com.logic;

public class Shopping extends Task {
    private List<item> items = new ArrayList<>();

    public Shopping(String name, String description, boolean done) {
        super(name, description, done);
    }

    public void addItem(item item) {
        items.add(item);
    }
}
