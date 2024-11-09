// John Paul Larkin
// C00001754
// OOP - Lab six - 9/11/24

import java.util.Scanner;

public class Lists {
    public static int difference(int[] arr) {
        // If array is empty or null, return 0 as default
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // Originally did it this way- but decided to try tp do it in one pass
        //    Arrays.sort(arr);
        //    return arr[arr.length - 1] - arr[0];

        // Initialize max and min to first array element
        int max = arr[0];
        int min = arr[0];

        // For each number in arr, compare it to current max/min
        // If number is greater than max, update max
        // If number is less than min, update min
        for (int num : arr) {
            if (num > max) {
                max = num; // new maximum
            }
            if (num < min) {
                min = num; // new minimum
            }
        }
        return max - min;
    }

    public static void match(){
        // Create array to store 5 random numbers
        int[] numbers = new int[5];
        
        // Fill array with random numbers between 0-10
        for (int i = 0; i < numbers.length; i++) {
            // Math.random() * 11 returns 0-10.99.....
            // Rounds down to 10
            numbers[i] = (int)(Math.random() * 11);
        }
    
        // Create Scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number between 0 and 10: ");
        int userNumber = scanner.nextInt();
    
        // Check for match
        boolean found = false;
        // If the number is in the array change found to true and break out of the loop
        for (int num : numbers) {
            if (num == userNumber) {
                found = true;
                break;
            }
        }
        // Print result
        if (found) {
            System.out.println("Matched!");
        } else {
            System.out.println("Not matched!");
        }
    
        // Print the stored numbers
        System.out.print("The random numbers were: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        scanner.close();
    }

    public static void asciiBox() {
        int[][] vals = new int[][]{
                {32,32,95,95,95,95},
                {32,47,92,95,95,95,92},
                {47,92,32,92,95,95,95,92},
                {92,32,92,47,32,47,32,47},
                {32,92,47,95,47,95,47}
        };

        for(int i = 0 ; i < vals.length; i++)
        {
            for(int j = 0 ; j < vals[i].length; j++)
            {
                // char here casts the numeric value to an ascii equivalent
                System.out.print((char)(vals[i][j]));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] numbers = {56, 12, 78, 54, 90, 3};
        int diff = difference(numbers);
        System.out.println(diff);

        match();
        asciiBox();
    }
}






















