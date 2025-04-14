// John Paul Larkin
// C00001754
// OOP - Lab twelve - 13/4/25

public class Test {




    public static void main(String[] args) {
        Stack stack = new Stack(5);

        stack.push("45");
        stack.push("26");
        stack.push("75");
        stack.push("83");
        stack.push("28");

        System.out.println(stack);

        stack.pop();
        System.out.println("Popped - 28 should be gone");
        System.out.println(stack);

        System.out.println("Linear search for 28 : should be -1");
        System.out.println(stack.linearSearch("28"));

        stack.push("44");
        stack.randomise();

        System.out.println("Linear search for 44 : should be found");
        System.out.println(stack.linearSearch("44"));

        stack.bubbleSort();
        System.out.println("Bubble sort - should include 44");
        System.out.println(stack);
        stack.randomise();

        stack.selectionSort();
        System.out.println("Selection sort:");
        System.out.println(stack);
        stack.randomise();

        stack.insertionSort();
        System.out.println("Insertion sort");
        System.out.println(stack);
        
    }
}