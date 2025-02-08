import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Files {

    public static void main(String[] args) {
        final String filename = "Fruit.txt";
        final String[] fruits = {"Apple", "Pear", "Banana"};
        writeToFile(filename, fruits);

        System.out.println("Current Time: " + getTime());

        manageAmountFile();
    }

    // Task A: Method to write to a file
    public static void writeToFile(String filename, String[] lines) {
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile(); // Create file if it doesn't exist
            }

            FileWriter writer = new FileWriter(file);
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
            System.out.println("File written successfully: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static String getTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH:mm:ss");
        return now.format(formatter);
    }

    public static void manageAmountFile() {
        Scanner scanner = new Scanner(System.in);
        String amountFilename = "Amount.txt";
        File file = new File(amountFilename);

        try {
            if (!file.exists()) {
                file.createNewFile(); // Create file if it doesn't exist
            }

            FileWriter writer = new FileWriter(file, true); // Append mode
            while (true) {
                System.out.print("Enter a new Amount (or 'Q' to quit): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("Q")) {
                    break; // Exit the loop if user enters 'Q'
                }

                try {
                    int amount = Integer.parseInt(input); // Parse the input to ensure it's a number
                    String time = getTime();
                    writer.write("Amount:" + amount + " " + time + " UPDATED\n");
                    System.out.println("Amount added to file.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number or 'Q' to quit.");
                }
            }

            writer.close();
            System.out.println("All entries have been saved to " + amountFilename);
        } catch (IOException e) {
            System.out.println("An error occurred while managing the Amount file.");
            e.printStackTrace();
        }

        scanner.close();
    }
}

