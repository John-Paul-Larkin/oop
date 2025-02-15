// John Paul Larkin
// C00001754
// OOP - Lab eight - 14/2/25

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Files {

    // Method to write to a file
    public static void writeToFile(String filename, String[] lines) {
        // Define the file, using the passed filename
        File file = new File(filename);
        try {
            // Check if the file exists
            if (!file.exists()) {
                // Create file if it doesn't exist
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            // Write a line for each fruit in the array
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
            System.out.println("File written successfully: " + filename);
        } catch (IOException e) {
            // Print an error message if the file cannot be written to
            System.out.println("An error occurred while writing to the file.");
        }
    }

    // Method to get the current time
    public static String getTime() {
        // Get the current time 
        LocalDateTime now = LocalDateTime.now();
        // Format the time - dd-MM HH:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH:ss");
        // Return the formatted time
        return now.format(formatter);
    }

    // Method to manage the Amount file
    public static void manageAmountFile() {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        // Define the filename
        String amountFilename = "Amount.txt";
        // Define the file
        File file = new File(amountFilename);

        try {
            if (!file.exists()) {
                // Create file if it doesn't exist
                file.createNewFile(); 
            }

            // PAssing true as second argument to append to the file - rather than overwrite
            FileWriter writer = new FileWriter(file, true); 

            // Loop until the user enters 'Q'
            while (true) {
                // Prompt the user to enter an amount
                System.out.print("Enter a new Amount (or 'Q' to quit): ");
                // Read the user's input
                String input = scanner.nextLine();

                // Exit the loop if the user enters 'Q'
                if (input.equalsIgnoreCase("Q")) {
                    break; 
                }

                try {
                    // Parse the input to ensure it's a number
                    int amount = Integer.parseInt(input); 
                    // Call getTime to get the Fomatted time
                    String time = getTime();
                    // Write the amount and time to the file
                    writer.write("Amount:" + amount + " " + time + " UPDATED\n");
                    // Print a message to the console
                    System.out.println("Amount added to file.");
                } catch (NumberFormatException e) {
                    // Print an error message if the input is not a number
                    System.out.println("Invalid input. Please enter a valid number or 'Q' to quit.");
                }
            }

            // Close the writer
            writer.close();
            // Print a message to the console
            System.out.println("All entries have been saved to " + amountFilename);
        } catch (IOException e) {
            System.out.println("An error occurred while managing the Amount file.");
            e.printStackTrace();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Write to a file
        // Define the filename 
        final String filename = "Fruit.txt";
        // and the array of fruits
        final String[] fruits = {"Apple", "Pear", "Banana"};
        // Call the method to write to the file
        writeToFile(filename, fruits);
        // Print the current time
        System.out.println("Current Time: " + getTime());

        // Manage the Amount file
        manageAmountFile();
    }
}

