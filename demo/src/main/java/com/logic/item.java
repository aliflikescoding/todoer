public class item {
    private String name;
    private int quantity;
    private boolean isPurchased;

    public item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.isPurchased = false;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void purchase() {
        isPurchased = true;
    }
}