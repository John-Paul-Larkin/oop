// John Paul Larkin
// C00001754
// OOP - Lab two - 25/9/24

import java.util.Scanner;  // Import the Scanner class

public class Loan{
        public static void main (String[] args){

            //Create a Scanner object that reads input from the standard input stream
            Scanner scanner = new Scanner(System.in);

            // Read the users input for loan amount
            System.out.println("Input loan amount");
            double loanAmount = scanner.nextDouble();

            // Read the users input for interest rate
            System.out.println("Input interest rate");
            double interestRate = scanner.nextDouble();

            // Read the users input for number of years
            System.out.println("Number of years");
            double numberYears = scanner.nextDouble();

            // Calculate the interest
            double totalInterest =   (loanAmount/100) * interestRate * numberYears;

            // Add the interest to the loan amount
            double totalAmountRepaid = loanAmount + totalInterest;

            // Print the calculation to standard out
            System.out.println("Total amount repaid: " + totalAmountRepaid);
        }
}