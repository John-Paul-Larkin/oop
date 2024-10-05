// John Paul Larkin
// C00001754
// OOP - Lab two - 25/9/24

import java.util.Scanner;  // Import the Scanner class

public class Converter {
    public static void main (String[] args) {

        //Create a Scanner object that reads input from the standard input stream
        Scanner scanner = new Scanner(System.in);
        //Create a constant for the conversion
        final double INCHES_TO_CM = 2.54;

        System.out.println("Enter a value in inches");
        // Read the users input for inches
        double inches = scanner.nextDouble();
        // Calculate the value
        double centimeters = inches * INCHES_TO_CM;
        // Print to standard out
        System.out.println(centimeters);
    }
}