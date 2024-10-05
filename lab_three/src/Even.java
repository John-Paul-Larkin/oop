// John Paul Larkin
// C00001754
// OOP - Lab three - 5/10/24

import java.util.Scanner;  // Import the Scanner class


public class Even {
    public static void main (String[] args) {
        // Create a Scanner object that reads input from the standard input stream
        Scanner scanner = new Scanner(System.in);

        // Ask user for a number
        System.out.println("Enter a number");
        int number = scanner.nextInt();

        System.out.println("Even numbers between 0 and " + number + " are");

        // Loop from 0 to the number entered
        for(int i = 0; i <= number; i++) {
            // Check if the current number is even (i % 2 == 0 means no remainder, so it is even
            if(i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}
