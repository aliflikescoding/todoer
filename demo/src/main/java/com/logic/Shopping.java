package com.logic;

public class Shopping extends Task {
    private String storeName;
    private Integer quantity;
    private double price;

    public Shopping(String name, String description, String storeName, Integer quantity, double price) {
        super(name, description);
        this.storeName = storeName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter
    public String getStoreName() {
        return storeName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Setter
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
    }

    @Override
    public String toString() {
        return String.format(
                "{\"name\": \"%s\", \"description\": \"%s\", \"storeName\": \"%s\", \"quantity\": %d, \"price\": %.2f, \"done\": %b}",
                name, description, storeName, quantity, price, done);
    }
}
