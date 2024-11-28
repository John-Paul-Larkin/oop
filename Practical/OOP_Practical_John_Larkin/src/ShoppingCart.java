// John Paul Larkin
// C00001754
// OOP - Practical assessment - 28/11/24

// I implemented removing duplicates from the shopping list
// IN the updateShoppingList method - line 40

import java.util.Scanner;

public class ShoppingCart {
 
    private static String getItemInput(Scanner scanner) {
        // ask the user for the item name
        System.out.print("Enter the item name: ");
        String itemName = scanner.nextLine();

        // ask the user for the price
        System.out.print("Enter the price for the item (e.g., 10.25€): ");
        String priceInput = scanner.nextLine();

        // Remove € symbol if it is added and trim whitespace
        String priceString = priceInput.replace("€", "").trim();

        // Convert to double
        double price = Double.parseDouble(priceString);

        // Return the item name and price - formatted to 2 decimal places, with surrounding brackets
        // ie "Bread(1.25)"
        return itemName + "(" + String.format("%.2f", price) + ")";
    }

    private static String updateShoppingList(String shoppingList, String itemInput) {
        // The itemInput is in the format "Bread(1.25)" - so xxtract the item name
        String itemName = itemInput.substring(0, itemInput.indexOf('('));
       
        if (shoppingList.isEmpty()) {
            // If the shopping list is empty, return the item input only 
            // as this is the first item in the shopping list
            return itemInput;
        } else if (shoppingList.toLowerCase().contains(itemName.toLowerCase())) {
            // REMOVE DUPLICATES
            // Search the shopping list for the item name - regardless of case
            // if the item is found, split the shopping list into items - delimited by commas
            String[] existingItems = shoppingList.split(",");
            for (int i = 0; i < existingItems.length; i++) {
                // Loop through the items in the shopping list
                if (existingItems[i].toLowerCase().startsWith(itemName.toLowerCase())) {
                    // When the item is found, replace it with the new item
                    existingItems[i] = itemInput;
                }
            }
            // Join the strings back together aadding back in the commas and return the result
            return String.join(",", existingItems);
        } else {
            // If the item is not found, add it to the end of the shopping list, aloing with a comma
            return shoppingList + "," + itemInput;
        } 
    }

    private static void calculateAndDisplayPrices(String shoppingList) {
        // Frist print the shopping list
        System.out.println(shoppingList);

        // Split the shopping list into items - delimited by commas
        String[] items = shoppingList.split(",");

        // Initialise variables to store the total price, max price and most expensive item
        double totalPrice = 0.0;
        double maxPrice = 0.0;
        String mostExpensiveItem = "";

        // Loop through each item in the shopping list
        for (String item : items) { 
            // find the start and end index of the price - based on the brackets "Bread(1.25)"
            int startIndex = item.indexOf('(');
            int endIndex = item.indexOf(')');

            // If the start and end index are found 
            if (startIndex != -1 && endIndex != -1) {
                // Extract the item name 
                String name = item.substring(0, startIndex);
                // Extract the price   
                String priceStr = item.substring(startIndex + 1, endIndex);
                // Convert the price to a double
                double itemPrice = Double.parseDouble(priceStr);
                // Add the price to the total price
                totalPrice += itemPrice;
                // If the price is greater than the max price, update the max price and the most expensive item
                if (itemPrice > maxPrice) {
                    maxPrice = itemPrice;
                    mostExpensiveItem = name;
                } 
            }
        }

        // Print the total price, most expensive item and the price of the most expensive item
        // Format the price to 2 decimal places - and add a € symbol
        System.out.println("Total Price: " + String.format("%.2f", totalPrice) + "€");
        System.out.println("Most expensive item is " + mostExpensiveItem);
        System.out.println("      Price is " + String.format("%.2f", maxPrice) + "€");
    }


    public static void main(String[] args) {
        // Initialise the shopping list string
        String shoppingList = "";
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Initialise a variable to store the user's response to adding an item
        // The while loop will continue to run until the user enters 'n' or 'N'
        String isAdditionalItem;
        do {
            // Ask the user if they want to add an item to the basket
            System.out.print("Do you want to add an item to the basket? [Y/y/N/n]: ");
            isAdditionalItem = scanner.nextLine();

            if(isAdditionalItem.equalsIgnoreCase("y")) {
                // Get the item input from the user
                String itemInput = getItemInput(scanner);
                // Update the shopping list with the entered item
                shoppingList = updateShoppingList(shoppingList, itemInput);
            } else if (isAdditionalItem.equalsIgnoreCase("n")) {
                // Exit the loop
                break;
            } else {
                // Inform the user that the input is invalid
                System.out.println("Invalid input. Please enter Y/y or N/n.");
            }
        } while (true);

        // Print the total price, most expensive item and the price of the most expensive item
        calculateAndDisplayPrices(shoppingList);

        // Close the scanner
        scanner.close();
    }
}
