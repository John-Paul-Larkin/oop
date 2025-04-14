// John Paul Larkin
// C00001754
// OOP - Lab twelve - 13/4/25

import java.util.Arrays;

public class Stack {

    private String[] myStack;
    private int size = 0;
    private int capacity = 0;

    public Stack(int capacity) {
        this.capacity = capacity;
        myStack = new String[capacity];
    }

    public boolean stackEmpty() {
        return size == 0;
    }

    public boolean stackFull() {
        return size == capacity;
    }

    // Deletion worst time complexity :    O(1)
    public String pop() {
        //check if empty, return null
        if (stackEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }

        //remove last item in myStack;
        String poppedElement = myStack[size - 1];

        // set the last item to null - even though size will decrease
        myStack[size - 1] = null;
        //if not decrease size
        size--;
        //return the popped element
        return poppedElement;
    }


    // Insertion worst time complexity :     O(1)
    public void push(String x) {
        //check if stack is already full, print error if it is
        if (stackFull()) {
            System.out.println("Stack is full");
            return;
        }
        //increase size
        size++;
        // add item to myStack
        myStack[size-1] = x;
    }

    // Worst Time Complexity :              O(n^2) 
    public void bubbleSort()
    {
        //sort myStack using a bubbleSort
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                // compare the current element with the adjacent element
                if (myStack[j].compareTo(myStack[j + 1]) > 0) {
                    // swap the elements - using a temp variable
                    String temp = myStack[j];
                    myStack[j] = myStack[j + 1];
                    myStack[j + 1] = temp;
                }
            }
        }
    }

    //Worst Time Complexity :               0(n^2)         
    public void selectionSort()
    {
        //sort myStack using a selection sort
  
        for(int i = 0; i < size; i++){
            // set the minimum element position to i to start
            int minElementPos = i;
            if(this.myStack[minElementPos] != null){
                // compare the current element with the adjacent element
                for(int j = i+1; j < size; j++){
                    // compare the current element with the adjacent element
                    if (myStack[minElementPos].compareTo(myStack[j]) > 0) {
                        // we found a new minimum element
                        minElementPos = j;
                    }
                }
                // swap the minimum element with the current element position [i]
                String temp = myStack[minElementPos];
                myStack[minElementPos] = myStack[i];
                myStack[i] = temp;
            }
        }
    }


    // Worst Time Complexity :               O(n^2)
    public void insertionSort()
    {
        //sort myStack using an insertion sort  
        for (int i = 1; i < size; i++) {
            // set the key to the current element
            String key = myStack[i];
            // set the index to the previous element
            int j = i - 1;

            // Shift elements 
            // that are greater than key to one 
            // position ahead of their current position
            while (j >= 0 && myStack[j].compareTo(key) > 0) {
                myStack[j + 1] = myStack[j];
                j--;
            }
            myStack[j + 1] = key;
        }
    }

    //Worst Time Complexity :                0(n)            
    public int linearSearch(String x)
    {
        // int index = 0;
        // look for the index of x using a linear search
        // remember to use size as the max not myArray.length or you'll search null values

        // simply loop and return the index if found
        for(int i=0; i<size; i++){
            if(myStack[i].equals(x)){
                return i;
            }
        }

        //return -1 if not found;
        return -1;
    }

    // Worst Time Complexity :                  O(log n)               
    public int binarySearch(String x)
    {
        //look for the index of x using a binary search
        //make sure its sorted first, use one of the sorting methods
        insertionSort();

        // create three pointers, low, high 
        int low = 0;
        int high = size;
        // calculate the middle index
        int mid = low + ((high-low)/2);

        // loop until the middle index is not equal to the low index
        while(mid != low){
            // if the middle index is equal to the x, return the middle index
            if(myStack[mid].equals(x)){
                return mid;
            } else if(x.compareTo(myStack[mid]) > 0 ){
                // if the x is greater than the middle index, set the low to the middle index
                low = mid;
            } else {
                // if the x is less than the middle index, set the high to the middle index
                high = mid;
            }
            // calculate the new middle index
            mid = low + ((high-low)/2);
        }
        // check again once more after the while loop
        if(myStack[mid].equals(x)){
            return mid;
        }
        // if the x is not found, return -1
        return -1;

    }

    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(myStack, 0, size));}

    public void randomise() {
        // randomise the stack order
        // If stack is empty or has only one element, no need to shuffle
        if (size <= 1) {
            return;
        }
        
        // shuffle algorithm
        for (int i = size - 1; i > 0; i--) {
            // Generate random index between 0 and i (inclusive)
            int randomIndex = (int) (Math.random() * (i + 1));
            
            // Swap elements at randomIndex and i using a temp variable
            String temp = myStack[i];
            myStack[i] = myStack[randomIndex];
            myStack[randomIndex] = temp;
        }
    }
}



