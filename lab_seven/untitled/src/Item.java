// John Paul Larkin
// C00001754
// OOP - Lab seven - 20/1/25

public class Item {
    // Private global variables
    private int price;
    private int stock;

    // Constructor with no parameters defaults price and stock to 0
    public Item() {
        this.price = 0;
        this.stock = 0;
    }

    // Setter for 'price'
    public void setPrice(int price) {
        this.price = price;
    }

    // Setter for 'stock'
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getter for 'price'
    public int getPrice() {
        return price;
    }

    // Getter for 'stock'
    public int getStock() {
        return stock;
    }
}
