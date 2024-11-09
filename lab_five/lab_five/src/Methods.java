// John Paul Larkin
// C00001754
// OOP - Lab five - 13/10/24
// Task 1

public class Methods {

    // Method to check if a number is even
    public static boolean isEven(int number) {
        // Returns true if the number is divisible by 2 (even), otherwise false
        return number % 2 == 0;
    }

    // Method to check if a character is alphabetic
    public static boolean isAlpha(char character) {
        // Returns true if in the range 'A-Z' OR 'a-z', otherwise false
        return (character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z');
    }

    // Alluded to in the brief, so I implemented it
    // Method to count the alphabetic characters in a string
    public static int alphaCount(String input) {
        int count = 0;  // Initialize a counter

        // Loop through each character in the string
        for (int i = 0; i < input.length(); i++) {
            // Get the character at the current index
            char character = input.charAt(i);

            // Check if the character is alphabetic using the isAlpha method
            if (isAlpha(character)) {
                // Increment the counter if it's alphabetic
                count++;
            }
        }
        // Return the total count of alphabetic characters
        return count;
    }

    // Method to convert percentage grade to a string
    public static String award(int grade){
        // If the number is outside the range 0-100 return invalid
        if(grade<0 || grade>100){
            return "Invalid grade";
        }
        // If the number is greater than 70 return Distinction etc...
        if(grade>=70){
            return "Distinction";
        }
        if(grade>=63){
            return "Merit1";
        }
        if(grade>=55){
            return "Merit2";
        }
        if(grade>=40){
            return "Pass";
        }
        // if we get here it must be a fail
        return "Fail";
    }

    public static void main(String[] args) {
        System.out.println("21 is even: " + isEven(21));
        System.out.println("12 is even: " + isEven(12));
        System.out.println("A is alphabetic: " + isAlpha('A'));
        System.out.println("% is alphabetic: " + isAlpha('%'));
        System.out.println("The amount of alphabetic characters in Word: " + alphaCount("Word"));
        System.out.println("The amount of alphabetic characters in £100.07: " + alphaCount("£100.07"));
        System.out.println("97 is a : " + award(97));
        System.out.println("42 is a : " + award(42));
    }
}
