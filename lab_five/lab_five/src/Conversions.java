// John Paul Larkin
// C00001754
// OOP - Lab five - 13/10/24
// Task 2

import java.util.Scanner;

public class Conversions {

    // Method to convert Fahrenheit to Celsius
    public static double fahrToCel(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    // Method to convert Celsius to Fahrenheit
    public static double celToFahr(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    // Method to convert Inches to Centimeters
    public static double inchToCent(double inches) {
        return inches * 2.54;
    }

    // Method to convert Centimeters to Inches
    public static double centToInch(double centimeters) {
        return centimeters / 2.54;
    }

    // Method to convert Pounds to Kilograms
    public static double poundToKg(double pounds) {
        return pounds * 0.453592;
    }

    // Method to convert Kilograms to Pounds
    public static double kgToPound(double kilograms) {
        return kilograms / 0.453592;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display the menu
            System.out.println("==================================");
            System.out.println("Please enter your choice:");
            System.out.println("1. Fahrenheit to Celsius");
            System.out.println("2. Celsius to Fahrenheit");
            System.out.println("3. Inches to Centimeters");
            System.out.println("4. Centimeters to Inches");
            System.out.println("5. Pounds to Kilograms");
            System.out.println("6. Kilograms to Pounds");
            System.out.println("7. Exit");
            System.out.print(">> ");

            // Read user choice
            choice = scanner.nextInt();

            // Perform conversion based on user choice
            switch (choice) {
                case 1:
                    System.out.print("Insert the temperature in Fahrenheit: ");
                    double fahrenheit = scanner.nextDouble();
                    System.out.println("The temperature in Celsius is: " + fahrToCel(fahrenheit));
                    break;

                case 2:
                    System.out.print("Insert the temperature in Celsius: ");
                    double celsius = scanner.nextDouble();
                    System.out.println("The temperature in Fahrenheit is: " + celToFahr(celsius));
                    break;

                case 3:
                    System.out.print("Insert the length in Inches: ");
                    double inches = scanner.nextDouble();
                    System.out.println("The length in Centimeters is: " + inchToCent(inches));
                    break;

                case 4:
                    System.out.print("Insert the length in Centimeters: ");
                    double centimeters = scanner.nextDouble();
                    System.out.println("The length in Inches is: " + centToInch(centimeters));
                    break;

                case 5:
                    System.out.print("Insert the weight in Pounds: ");
                    double pounds = scanner.nextDouble();
                    System.out.println("The weight in Kilograms is: " + poundToKg(pounds));
                    break;

                case 6:
                    System.out.print("Insert the weight in Kilograms: ");
                    double kilograms = scanner.nextDouble();
                    System.out.println("The weight in Pounds is: " + kgToPound(kilograms));
                    break;

                case 7:
                    System.out.println("Exiting the program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            // Print an empty line for better readability
            System.out.println();
            // Continue to loop until the user chooses to exit (by entering numnber 7)
        } while (choice != 7);

        scanner.close();  // Close the scanner
    }
}
