package com.logic;

public class Shopping extends Task {
  public Shopping(String name, String description, boolean done) {
        super(name, description, done);
    }

    @Override
    public void method() {
        System.out.println("Executing Shopping Task: " + name);
    }
}