// John Paul Larkin
// C00001754
// OOP - Lab seven - 20/1/25

import java.util.Scanner;
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
        // Create a new Random object
        Random random = new Random();

        // Create an array of 10 Item objects
        Item[] items = new Item[10];

        // Loop through the array and set the price and stock to random values
        for (int i = 0; i < items.length; i++) {
            // Create a new Item object
            items[i] = new Item();
            // Random price between 1 and 20 - add 1 as Zero index
            items[i].setPrice(random.nextInt(20) + 1); 
            // Random stock between 0 and 6
            items[i].setStock(random.nextInt(7));      
        }

        // Print out each item, its index, price, and stock
        System.out.println("Index | Price | Stock");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%5d | %5d | %5d%n", i, items[i].getPrice(), items[i].getStock());
        }

        // Call the buyItem method
        buyItem(items); 
        // Print total price of all stock
        totalPrice(items);   
    }

    // Method to buy an item
    public static void buyItem(Item[] items) {
        Scanner scanner = new Scanner(System.in);
        
        // Get user input for the index and amount of the item they want to buy
        System.out.print("Enter the index of the item you want to buy: ");
        int index = scanner.nextInt();
        System.out.print("Enter the amount of the item you want to buy: ");
        int amount = scanner.nextInt();
        
        // Check if the index is in range 
        if (index < 0 || index >= items.length) {
            System.out.println("Invalid item index.");
            return;
        }

        // Get the item at the index
        Item item = items[index];
        // Check if the amount is greater than the available stock
        if (amount > item.getStock()) {
            System.out.println("Not enough stock");
        } else {
            // Calculate the total cost of the items
            int totalCost = amount * item.getPrice();
            // Reduce stock
            item.setStock(item.getStock() - amount); 
            // Print total cost
            System.out.println("Total cost for item: " + totalCost);
        }
        // Close the scanner
        scanner.close();
    }

    // Method to calculate total price of all stock
    public static void totalPrice(Item[] items) {
        int total = 0;  
        // Loop through the array and calculate the total price of all stock
        for (Item item : items) {
            total += item.getPrice() * item.getStock();
        }
        // Print total price
        System.out.println("Total price of all stock: " + total);
    }

    public static void testRecipe() {
        // Create an array of 4 Recipe objects
        Recipe[] recipes = new Recipe[4];
        // Initialise the array with Recipe objects
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
        // Loop throught each recipe in the array
        for (Recipe recipe : recipes) {
            // Check if the recipe is vegetarian
            if (!recipe.hasMeat()) {
                // Print the recipe name
                System.out.println(recipe.getName());
            }
        }
    }

    // Method to print vegan recipes (no meat and no dairy)
    public static void isVegan(Recipe[] recipes) {
        // Loop throught each recipe in the array
        for (Recipe recipe : recipes) {
            // Check if the recipe is vegan (no meat and no dairy)
            if (!recipe.hasMeat() && !recipe.hasDairy()) {
                // Print the recipe name
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
