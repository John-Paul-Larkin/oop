// John Paul Larkin
// C00001754
// OOP - Lab two - 25/9/24

import java.util.Scanner;  // Import the Scanner class

public class Distance {
    public static void main(String[] args) {



        //Create a Scanner object that reads input from the standard input streams
        Scanner scanner = new Scanner(System.in);

        // Read the users input for miles
        System.out.println("Input distance in miles");
        double miles = scanner.nextDouble();

        // Read the users input for hours
        System.out.println("Input time travelled in hours");
        double hours = scanner.nextDouble();

        // Read the users input for gallons
        System.out.println("Input petrol in gallons");
        double gallons = scanner.nextDouble();

        // Make the calculations
        double AverageMilesPerHour = miles / hours;
        double AverageMilesPerGallon = miles / gallons;

        // Print the calculations to standard out
        System.out.println("Average miles per hour: " + AverageMilesPerHour);
        System.out.println("Average miles per gallon: " + AverageMilesPerGallon);
    }
}