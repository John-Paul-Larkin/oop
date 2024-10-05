// John Paul Larkin
// C00001754
// OOP - Lab three - 5/10/24

import java.util.Scanner;  // Import the Scanner class
import java.util.Arrays;   // Import the Arrays utility class

public class Compare {
    public static void main (String[] args) {
        //Create a Scanner object that reads input from the standard input stream
        Scanner scanner = new Scanner(System.in);

        // Create an empty array of length 3
        double[] numbers = new double[3];

        // Read the first number and assign it to the first element of the array
        System.out.println("Enter a number");
        numbers[0]  = scanner.nextDouble();

        // Read the second number and assign it to the second element of the array
        System.out.println("Enter another number");
        numbers[1]  = scanner.nextDouble();

        // Read the third number and assign it to the third element of the array
        System.out.println("Enter a final number");
        numbers[2] = scanner.nextDouble();

        // Convert the array into a stream
        // Using Arrays.stream to find the minimum value
        // .getAsDouble() is required as Array.stream() returns an optional double, as stream could be empty
        double min = Arrays.stream(numbers).min().getAsDouble();

        // print the result
        System.out.println("The minimum number is: " + min);

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}
