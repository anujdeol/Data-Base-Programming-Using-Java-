package Assignment1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HESDHFL {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner userInput = new Scanner(System.in);
		String playAgain;
		do {
			// Generate a random word
			char[] word = getWord();
			// Display each letter in the word as an asterisk.
			char[] asterisks = new char[word.length];
			fillAsterisks(asterisks); // number of wrong guesses
			int missed = 0;
			do {
				// Prompt the user to guess one letter
				char guess = askGuess(asterisks);
				// Check if is letter is correct
				if (!isCorrectGuess(word, asterisks, guess))
					missed++;
			} while (!isWordCompleted(asterisks));
			// Print results
			wordExist(word, missed);
			// Ask the user whether to continue to play with another word
			System.out.print("\nDo you want to guess another word? Enter y or n>");
			playAgain = userInput.next();
			System.out.println();
		} while (playAgain.charAt(0) == 'y');
	}

	// getWord() FUNCTION to randomly generates a word from a file
	public static char[] getWord() throws FileNotFoundException {
		// Create File object
		File file = openHangmanFile();
		// Create an ArrayList
		ArrayList<String> words = new ArrayList<>();
		try ( // Create input file
				Scanner input = new Scanner(file)) {
			while (input.hasNext()) {
				words.add(input.next());
			}
		}
		// Pick a random string
		String pick = words.get((int) (Math.random() * words.size()));
		return pick.toCharArray();
	}

	// openHangmanFile() function is used to Open file
	public static File openHangmanFile() {
		File file = new File("/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt");
		// Check if file exists
		if (!file.exists()) {
			System.out.print("File \"" + file.getName() + "\" does not exist");
			System.exit(1);
		}
		// Return File object
		return file;
	}

	// fillAsterisks() function initializes a list with asterisks
	public static void fillAsterisks(char[] list) {
		Arrays.fill(list, '*');
	}

	// isCorrectGuess() function tests if the users guess was correct
	public static boolean isCorrectGuess(char[] word, char[] blanks, char guess) {
		boolean correct = false;
		int message = 2;
		for (int i = 0; i < word.length; i++) {
			if (word[i] == guess) {
				correct = true;
				if (blanks[i] == guess)
					message = 1;
				else {
					// the actual letter is then displayed.
					blanks[i] = guess;
					message = 0;
				}
			}
		}
		if (message > 0)
			wordExist(message, guess);
		return correct;
	}

	// isWordCompleted() function checks if a word is completed or not
	public static boolean isWordCompleted(char[] blanks) {
		for (char e : blanks) {
			if (e == '*')
				return false;
		}
		return true;
	} // wordExist() overloaded

	public static void wordExist(char[] word, int missed) {
		System.out.print("The word is \"");
		System.out.print(word);
		System.out.print("\"");
		System.out.println(" You missed " + missed + (missed > 1 ? " times" : " time"));
	} // wordExist() function to display if already typed or not in list

	public static void wordExist(int m, char guess) {
		System.out.print("\t" + guess);
		switch (m) {
		case 1 -> System.out.println(" is already in the word");
		case 2 -> System.out.println(" is not in the word");
		}
	}

	// askGuess() function prompts the user to guess one letter at a time
	public static char askGuess(char[] asterisks) {
		Scanner userInput = new Scanner(System.in);
		// ask user
		System.out.print("(Guess) Enter a letter in word ");
		System.out.print(asterisks);
		System.out.print(" > ");
		String guess = userInput.next();
		return guess.charAt(0);
	}
}
