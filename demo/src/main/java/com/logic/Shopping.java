package com.logic;

public class Shopping extends Task {
    private String storeName;
    private Integer quantity;
    private double price;

    public Shopping(String name, String description) {
        super(name, description);
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
}
