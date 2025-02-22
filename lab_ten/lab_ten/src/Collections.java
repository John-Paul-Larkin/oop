// John Paul Larkin
// C00001754
// OOP - Lab ten - 22/2/25

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;


// Note the Recipe class and the Student class are direct copies from lab seven

public class Collections {

    public static void main(String[] args) {
        // main method - create a scanner object and call the three methods
        Scanner scanner = new Scanner(System.in);
        // Task 1
        match(scanner);
        // Task 2
        recipes(scanner);
        // Task 3
        students(scanner);

        scanner.close();
    }

    // Task 1 - match method - guess the number
    public static void match(Scanner scanner) {
        Random random = new Random();
        // random between 0 and 20 - inclusive
        int randomNumber = random.nextInt(21);

        // HashSet to store the users guesses - no duplicates
        HashSet<Integer> guesses = new HashSet<>();

        System.out.println("I have chosen a number between 0 and 20.");

        // loop until the user guesses the correct number - break when the user guesses the correct number
        while (true) {
            System.out.print("Enter your guess: ");
            // Get user input as a string
            String userGuessString = scanner.next();
            // Declare userGuess as an integer, since it needs to be accessed outside the try block
            int userGuess;
            try {
                // Parse the user's guess as an integer
                userGuess = Integer.parseInt(userGuessString);
            } catch (NumberFormatException e) {
                // If the user's guess is not a valid integer, catch the exception
                // print an error message and continue the while loop
                System.out.println("That's not a valid number! Please enter a whole number between 0 and 20.");
                continue;
            }

            // Validate input is within the valid range
            if (userGuess < 0 || userGuess > 20) {
                // If the user's guess is not within the range, print an error message and continue the while loop
                System.out.println("Not within range! Please enter a number between 0 and 20.");
                continue;
            }

            // Check if the user input is the correct random number
            if (userGuess == randomNumber) {
                // If the user input is the correct random number, print a congratulatory message and break the loop
                System.out.println("Congratulations! You guessed the correct number!");
                break;
            }

            // Check if this guess has been made before - already in the HashSet
            if (guesses.contains(userGuess)) {
                System.out.println("You've already tried that number! Please enter a different number.");
            } else {
                // If the user input is not the correct random number, and has not been guessed before, add the guess to the HashSet
                guesses.add(userGuess);
            }

            // Print out all previous guesses and loop again
            System.out.println("Your previous guesses: " + guesses);
        }

    }

    // Task 2 - recipes method - add recipes to an ArrayList
    public static void recipes(Scanner scanner) {
        // Create an ArrayList of Recipe objects - variable length 
        ArrayList<Recipe> recipes = new ArrayList<>();

        // loop until the user quits
        while (true) {
            System.out.println("Enter a recipe name, or q to quit: ");
            // Get user input as a string
            String recipeName = scanner.next();
            // if the user quits, break the loop
            if (recipeName.equalsIgnoreCase("quit") || recipeName.equalsIgnoreCase("q")) {
                break;
            }

            // Call the doesRecipeContain helper method to return the boolean values for dairy, meat and gluten
            boolean dairyBool = doesRecipeContain(scanner, "Does the recipe contain dairy? (y/n): ");
            boolean meatBool = doesRecipeContain(scanner, "Does the recipe contain meat? (y/n): ");
            boolean glutenBool = doesRecipeContain(scanner, "Does the recipe contain gluten? (y/n): ");

            // Create a new Recipe object with the user's input
            Recipe recipe = new Recipe(recipeName, dairyBool, meatBool, glutenBool);
            // Add the new recipe to the ArrayList
            recipes.add(recipe);
        }

        // Print out all the recipes in the ArrayList
        System.out.println("Recipes: ");
        for (Recipe recipe : recipes) { 
            // Print out the recipe name, and the boolean values for dairy, meat and gluten
            System.out.println(recipe.getName());
            System.out.println("Contains dairy: " + recipe.hasDairy());
            System.out.println("Contains meat: " + recipe.hasMeat());
            System.out.println("Contains gluten: " + recipe.hasGluten());
            // Print a separator between recipes
            System.out.println("--------------------------------");
        }

    }

    // Helper method to get the boolean values for dairy, meat and gluten
    private static boolean doesRecipeContain(Scanner scanner, String prompt) {
        // loop until the user enters a valid input
        while (true) {
            System.out.println(prompt);
            // Get user input as a string
            String input = scanner.next();
            // If the user enters y, return true
            if (input.equalsIgnoreCase("y")) {
                return true;
            }
            // If the user enters n, return false
            if (input.equalsIgnoreCase("n")) {
                return false;
            }
            // Invalid input - print an error message and loop again
            System.out.println("Invalid input! Please enter y or n.");
        }
    }


    // Task 3 - students method - add students to a HashMap
    public static void students(Scanner scanner) {
        // Create a HashMap of Student objects - key is the student ID, value is the Student object
        HashMap<Integer, Student> students = new HashMap<>();

        // Add some students to the HashMap
        students.put(2001465, new Student("John", 2001465, "Math"));
        students.put(2001466, new Student("Tom", 2001466, "Science"));
        students.put(2001467, new Student("mary", 2001467, "English"));

        // Loop through the HashMap and print out the student ID (The key)
        students.forEach((key, student) -> System.out.println("Student ID: " + key));

        while (true) {
            System.out.println("Enter a student ID to search for, or q to quit: ");
            // Get user input as a string
            String studentID = scanner.next();
            // If the user quits, break the loop
            if (studentID.equalsIgnoreCase("q")||studentID.equalsIgnoreCase("quit")) {
                break;
            }

            int studentIDInt;
            try {
                // Parse the user's input as an integer
                studentIDInt = Integer.parseInt(studentID);
            } catch (NumberFormatException e) {
                // If the user's input is not a valid integer, print an error message and continue the loop
                System.out.println("Invalid input! Please enter a valid student ID.");
                continue;
            }

            // Check if the student ID is in the HashMap
            if (students.containsKey(studentIDInt)) {
                // If the student ID is in the HashMap, print the student's name
                System.out.println("Student found: " + students.get(studentIDInt).getName());
                break;
            } else {
                // If the student ID is not in the HashMap, print a message and continue the loop
                System.out.println("Student not found. Try another ID.");
                continue;
            }
        }
    
    }
}
