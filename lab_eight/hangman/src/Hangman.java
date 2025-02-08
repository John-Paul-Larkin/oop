import java.util.Scanner;

//Hangman game
class Hangman
{
    //main method
    public static void main(String[] args)
    {
        String winningWord = "hello";
        //output length of word
        System.out.println("The word is " +  winningWord.length() + " letters" );

        char[] outputWord = {'-','-','-','-','-'};
        //output each character in array
        for(char letter : outputWord)
        {
            System.out.print(letter);
        }

        //amount of guesses
        int guesses = 10;

        //while player still has guesses
        while(guesses > 0)
        {

            System.out.println("\nRemaining turns " + guesses );
            System.out.println("Guess a letter" );
            Scanner scan = new Scanner(System.in);
            //error checking
            try{
                //get user input
                char input = scan.nextLine().charAt(0);
                //check for found letters
                int found = 0;
                for(int i =0 ; i <winningWord.length();i++ )
                {
                    //check if the input is in the winning word
                    if(input == winningWord.charAt(i))
                    {
                        outputWord[i] = winningWord.charAt(i);
                    }

                    //check if all the letters have been found
                    if(outputWord[i] == winningWord.charAt(i))
                    {
                        found++;
                    }
                }
                //reduce amount of guesses
                guesses--;
                //output each character in array
                for(char letter : outputWord)
                {
                    System.out.print(letter);
                }
                //if all the letters are found output they win
                if(found == winningWord.length())
                {
                    System.out.println("\nYou won");
                    //break out of loop
                    guesses = -1;
                }
                //if all the guesses are used up
                else if(guesses == 0)
                {
                    System.out.println("\nOut of guesses");
                }
            }
            catch(Exception e)
            {
                System.out.println("Not a letter" );
            }
        }
    }
}