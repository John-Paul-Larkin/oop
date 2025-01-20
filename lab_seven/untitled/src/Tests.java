import java.util.Random;

public class Tests {
    public static void main(String[] args) {
        System.out.println("========== STARTING TESTS ==========\n");

        System.out.println("----- Running Student Tests -----");
        testStudent();
        System.out.println("----- Completed Student Tests -----\n");

        System.out.println("----- Running Recipe Tests -----");
        testRecipe();
        System.out.println("----- Completed Recipe Tests -----\n");

        System.out.println("----- Running Item Tests -----");
        testItem();
        System.out.println("----- Completed Item Tests -----\n");

        System.out.println("========== ALL TESTS EXECUTED ==========");
    }

    public static void testItem() {
        Random random = new Random();
        Item[] items = new Item[10];

        // Initialize array with Item objects and set random price and stock
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item();
            items[i].setPrice(random.nextInt(20) + 1); // Random price between 1 and 20
            items[i].setStock(random.nextInt(7));      // Random stock between 0 and 6
        }

        // Print out each item, its index, price, and stock
        System.out.println("Index | Price | Stock");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%5d | %5d | %5d%n", i, items[i].getPrice(), items[i].getStock());
        }

        // Example usage of buyItem and totalPrice methods
        buyItem(items, 2, 3); // Try buying 3 units of the item at index 2
        totalPrice(items);   // Print total price of all stock
    }

    // Method to buy an item
    public static void buyItem(Item[] items, int index, int amount) {
        if (index < 0 || index >= items.length) {
            System.out.println("Invalid item index.");
            return;
        }

        Item item = items[index];
        if (amount > item.getStock()) {
            System.out.println("Not enough stock");
        } else {
            int totalCost = amount * item.getPrice();
            item.setStock(item.getStock() - amount); // Reduce stock
            System.out.println("Total cost for item: " + totalCost);
        }
    }

    // Method to calculate total price of all stock
    public static void totalPrice(Item[] items) {
        int total = 0;
        for (Item item : items) {
            total += item.getPrice() * item.getStock();
        }
        System.out.println("Total price of all stock: " + total);
    }

    public static void testRecipe() {
        // Create an array of 4 Recipe objects
        Recipe[] recipes = new Recipe[4];
        recipes[0] = new Recipe("Cake", true, false, true);
        recipes[1] = new Recipe("Salad", false, false, false);
        recipes[2] = new Recipe("Burger", true, true, true);
        recipes[3] = new Recipe("Pasta", true, false, true);

        // Print recipes that are vegetarian
        System.out.println("Vegetarian Recipes:");
        isVegetarian(recipes);

        // Print recipes that are vegan
        System.out.println("\nVegan Recipes:");
        isVegan(recipes);
    }

    // Method to print vegetarian recipes (no meat)
    public static void isVegetarian(Recipe[] recipes) {
        for (Recipe recipe : recipes) {
            if (!recipe.hasMeat()) {
                System.out.println(recipe.getName());
            }
        }
    }

    // Method to print vegan recipes (no meat and no dairy)
    public static void isVegan(Recipe[] recipes) {
        for (Recipe recipe : recipes) {
            if (!recipe.hasMeat() && !recipe.hasDairy()) {
                System.out.println(recipe.getName());
            }
        }
    }

    public static void testStudent() {
        // Create a new Student object
        Student newStudent = new Student("bob", 202, "programming");

        // Print out the student's name and module using getter methods
        System.out.println("Student Name: " + newStudent.getName());
        System.out.println("Student Module: " + newStudent.getModule());

        // Change the student's module using the setter method
        newStudent.setModule("design");

        // Print out the student's name and updated module
        System.out.println("Updated Student Name: " + newStudent.getName());
        System.out.println("Updated Student Module: " + newStudent.getModule());

    }
}
