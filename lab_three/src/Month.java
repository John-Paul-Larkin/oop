// John Paul Larkin
// C00001754
// OOP - Lab three - 5/10/24

import java.util.Scanner;  // Import the Scanner class

public class Month {
    public static void main (String[] args) {
        // Create a Scanner object that reads input from the standard input stream
        Scanner scanner = new Scanner(System.in);

        // Create a constant array with the months of the year
        final String[] MONTHS = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        int month;

        // Wile true creates an infinite loop. Break is used to exit when the user clicks 0
        while (true) {
            // Ask user for a month number
            System.out.println("Enter the month (1-12): Enter 0 to quit");
            month = scanner.nextInt();

            // Check if the number is within the valid range
            if(month < 0 || month > 12) {
                System.out.println("Invalid number entered");
            } else if (month == 0) {
                // If the number id 0, quit the loop after informing the user
                System.out.println("Exiting");
                break;
            } else {
                // Print the month - take 1 from the number entered to get correct index
                System.out.println(MONTHS[month - 1]);
            }
        }

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}