// John Paul Larkin
// C00001754
// OOP - Lab one - 15/9/24

import java.util.Scanner;  // Import the Scanner class

public class Welcome {
    public static void main(String [ ] args)
    {
        System.out.println("Hi, I'm JP");

        //Create a Scanner object that reads input from the standard input stream
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome");

        // Input the first number
        System.out.print("Enter the first number: ");
        int num1 = scanner.nextInt();

        // Input the second number
        System.out.print("Enter the second number: ");
        int num2 = scanner.nextInt();

        // Print out both numbers
        System.out.println("Number one is : " + num1);
        System.out.println("Number two is : " + num2);

        // This converts the integers to strings and concatenates them
        // rather than adding the integers as one might suspect
        // System.out.println("The sum is " + num1 + num2 );

        System.out.println("Output the Sum, Product and difference");
        // The brackets ensure the integers are treated as such
        System.out.println("Your sum is " + (num1 + num2));
        System.out.println("Your product is " + (num1 * num2));
        System.out.println("Your difference is " + (num1 - num2));


    }
}

