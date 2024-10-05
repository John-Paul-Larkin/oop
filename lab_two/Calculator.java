// John Paul Larkin
// C00001754
// OOP - Lab two - 25/9/24

import java.util.Scanner;  // Import the Scanner class

public class Calculator {
    public static void main(String[] args) {

        //Create a Scanner object that reads input from the standard input stream
        Scanner scanner = new Scanner(System.in);

        // Ask the user for three numbers
        System.out.println("Input a number");
        double firstNumber = scanner.nextDouble();
        System.out.println("And another number");
        double secondNumber = scanner.nextDouble();
        System.out.println("And a third number");
        double thirdNumber = scanner.nextDouble();

        // Calculate the sum, product and average
        double sum = firstNumber + secondNumber + thirdNumber;
        double product = firstNumber * secondNumber * thirdNumber;
        double  average = sum / 3;

        // Print sum, product and average to standard out
        System.out.println("The sum is: " + sum);
        System.out.println("The product is: " + product);
        System.out.println("The average is: " + average);

    }
}