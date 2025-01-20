public class Item {
    // Private global variables
    private int price;
    private int stock;

    // No-argument constructor
    public Item() {
        this.price = 0;
        this.stock = 0;
    }

    // Setters
    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getters
    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
