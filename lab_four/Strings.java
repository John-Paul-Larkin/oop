// John Paul Larkin
// C00001754
// OOP - Lab four - 6/10/24

import java.util.Scanner;  // Import the Scanner class

public class Strings {
    public static void main(String[] args) {
        // Create a Scanner object that reads input from the standard input stream
        Scanner scanner = new Scanner(System.in);

        // Ask the user to input their name
        System.out.println("Enter full name: ");
        String fullName = scanner.nextLine();

        // Create an instance of the Name class
        Name name = new Name(fullName);
        // Calculate and print the outputs
        name.displayNameInfo();

        // Ask for a string to check if it's a palindrome
        System.out.println("Enter a string to check if it is a palindrome: ");
        String palindromeString = scanner.nextLine();

        // Create an instance of the Palindrome class
        Palindrome palindrome = new Palindrome(palindromeString);

        if (palindrome.isPalindrome()) {
            System.out.println(palindromeString + " is a palindrome!");
        } else {
            System.out.println(palindromeString + " is not a palindrome.");
        }

        scanner.close();
    }

}

class Name {
        private final String fullName;

        public Name(String fullName) {
            this.fullName = fullName;
        }

        public void displayNameInfo() {

            // Just the surname (everything after the first space)
            String surname = fullName.substring(fullName.indexOf(' ') + 1) ;

            // Just the first name (everything before the first space)
            String firstName = fullName.substring(0, fullName.indexOf(' '));

            // The number of letters in the full name (excluding spaces and special characters)
            // Use regex to replace all non alphabet chars with empty string ("")
            int numberOfLetters = fullName.replaceAll("[^a-zA-Z]", "").length();

            // Create a variable to hold the count
            int uppercaseCount = 0;
            // Convert the string to an array of chars
            char[] characters = fullName.toCharArray();
            // Loop through each char in the array
            for (char c : characters) {
                // If the char is uppercase increment the count
                if (Character.isUpperCase(c)) {
                    uppercaseCount++;
                }
            }

            // Create a new StringBuilder object to allow in-place mutation of the string
            // Use the reverse() method, then convert it back to a String
            String reversedName = new StringBuilder(fullName).reverse().toString();

            // The number of times the 'a' character appears in the full name
            // .chars() method creates a stream of int representing the Unicode of the chars in the string
            // lambda function filters out any which are not a (upper or lowercase)
            // and returns the count
            long countOfA = fullName.chars().filter(ch -> ch == 'a' || ch == 'A').count();

            // The number of vowels present in the full name
            // as above, but this time filter out any chars which are not a vowel
            // by testing if the current char is found in the string "AEIOUaeiou"
            // and returns the count
            long vowelCount = fullName.chars().filter(ch -> "AEIOUaeiou".indexOf(ch) != -1).count();

            // Display the results
            System.out.println("Surname: " + surname);
            System.out.println("First Name: " + firstName);
            System.out.println("Number of letters in the full name: " + numberOfLetters);
            System.out.println("Number of uppercase characters: " + uppercaseCount);
            System.out.println("Full name reversed: " + reversedName);
            System.out.println("Number of times 'a' appears: " + countOfA);
            System.out.println("Number of vowels: " + vowelCount);

        }
}

class Palindrome {
    private String inputText;

    public Palindrome(String inputText) {
        this.inputText = inputText;
    }

    // Method to check if the string is a palindrome
    public boolean isPalindrome() {
        // Convert the string to lowercase, since it is not case-sensitive
        inputText = inputText.toLowerCase();
        // Create a new StringBuilder object to allow in-place mutation of the string
        // Use the reverse() method, then convert it back to a String
        String reversedText = new StringBuilder(inputText).reverse().toString();
        // returns true is the strings match, else false
        return inputText.equals(reversedText);
    }
}

