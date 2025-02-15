// John Paul Larkin
// C00001754
// OOP - Lab eight - 14/2/25

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Hangman game
class Hangman
{
	// Instance variables to store the winning word and masked output
	private String winningWord;
	private char[] outputWord;

	//main method
	public static void main(String[] args)
	{
		Hangman game = new Hangman();
		if (!game.initialiseGame()) {
			// Initialisation has failed - file missing or other error
			return;
		}
		
		// Continue with the rest of the game...
		// For example, you can print out the initial outputWord for confirmation:
		System.out.println("\nCurrent state: " + String.valueOf(game.outputWord));
		
		//amount of guesses
		int guesses = 10;
		
        // Scanner for user input
		Scanner scan = new Scanner(System.in);
		
		//while player still has guesses
		while(guesses > 0)
		{
            //output remaining guesses
			System.out.println("\nRemaining turns: " + guesses );
			System.out.println("Guess a letter" );
			
			try{
				//get user input  
				char input = scan.nextLine().charAt(0);
				//check for found letters
				int found = 0;
				for(int i =0 ; i <game.winningWord.length();i++ )
				{
						//check if the input is in the winning word
						if(input == game.winningWord.charAt(i))
						{
							game.outputWord[i] = game.winningWord.charAt(i);
						}
						
						//check if all the letters have been found
						if(game.outputWord[i] == game.winningWord.charAt(i))
						{
							found++;
						}
				}
				//reduce amount of guesses
				guesses--;
				//output each character in array
				for(char letter : game.outputWord)
				{
						System.out.print(letter);
				}
				//if all the letters are found output they win
				if(found == game.winningWord.length())
				{
					System.out.println("\nYou won!");
					//break out of loop
					guesses = -1;
				}
				//if all the guesses are used up 
				else if(guesses == 0)
				{
					System.out.println("\nOut of guesses. The word was: " + game.winningWord);
				}
			}
			catch(Exception e)
			{
				System.out.println("Not a letter" );
			}
		}
		
		scan.close();
	}

	/**
	 * Initialises the game by reading words from Words.txt, selecting a random winning word,
	 * and initialising the masked word output.
	 *
	 * @return true if the initialisation is successful; false otherwise.
	 */

    // initialise game
		// Read Words.txt file and build a word list
	private boolean initialiseGame() {
        // Crete a list of strings to store the words
		List<String> wordsList = new ArrayList<>();
		try {
            // File to read from
		    File file = new File("Words.txt");  
            // Scanner to read from file
			Scanner fileScanner = new Scanner(file);
            // while there is another line in the file
			while(fileScanner.hasNextLine()){
                // get next line and remove whitespace
				String word = fileScanner.nextLine().trim();
                // If the line is not empty add it to the word list
				if (!word.isEmpty()) { 
					wordsList.add(word);
				}
			}
            // close the file scanner
			fileScanner.close();
		} catch (FileNotFoundException e) {
            // if the file is not found print error message and return false
			System.out.println("Words file not found.");
			return false;
		}
		
        // if the word list is empty print error message and return false
		if(wordsList.isEmpty()){
			System.out.println("No words found in Words.txt");
			return false;
		}
		
		// Choose a random word from the list to be the winning word
		Random random = new Random();
		winningWord = wordsList.get(random.nextInt(wordsList.size())).toLowerCase();
		
		//output length of word
		System.out.println("The word is " +  winningWord.length() + " letters" );
		
		// Create and initialise the outputWord with dashes
		outputWord = new char[winningWord.length()];
		for (int i = 0; i < winningWord.length(); i++)
		{
			outputWord[i] = '-';
		}
		//output each character in array
		for(char letter : outputWord)
		{
			System.out.print(letter);
		}
		
		return true;
	}
}