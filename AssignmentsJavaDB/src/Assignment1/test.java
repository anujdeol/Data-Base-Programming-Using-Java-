package Assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
	
	

	public static void main(String[] args) throws Exception {
		
		String[] wrongGuessWord = new String[10];
//		
//			// addiing all the words from text file to list array
//			while (txtFileScanner.hasNextLine());
//		
//				wordsList.add(txtFileScanner.nextLine());
//
//			
			Scanner usc = new Scanner(System.in );
			
			ArrayList<String> wordsList = new ArrayList<>();

	        try (Scanner txtFileScanner = new Scanner(new File("/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt"))) {
	            while (txtFileScanner.hasNextLine()) {
	            	wordsList.add(txtFileScanner.nextLine());
	            }
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        }

			// taking a random word from array list

			String randomWord = wordsList.get((int) (Math.random() * wordsList.size()));

			// using the string builder to manipulate the word
			StringBuilder wordForGuess = new StringBuilder(randomWord);

			// loop till the length of the random word to create astrick
			for (int i = 0; i < randomWord.length(); i++) {

				wordForGuess.setCharAt(i, '*');

			}
			int missesCount = 0;
			int f = 0;

			// loop till random and guess word are same
			while (!wordForGuess.toString().equals(randomWord)) {

				// for check the missed attempt
				String txtCheck = wordForGuess.toString();
				// user input
				String userInput = usc.nextLine();

				try {
					if (userInput.length()!=1 || userInput.matches(".*\\d.*")) // validating user input
					{
						System.out.println("Please enter VALID character only");
						
					}
					

					for (int i = 0; i < wrongGuessWord.length; i++) {
						// String userInput = usc.next();
						if (wrongGuessWord[i].equals(userInput)) {
							System.out.println("Try another word, you've already tried this letter");

							userInput = usc.nextLine();
							continue;
												}
					}
				} catch (NullPointerException e1) {

				}

				// loop for checking and assigning the user input to guess word
				for (int j = 0; j < randomWord.length(); j++) {

					if (userInput.charAt(0) == randomWord.charAt(j)) {

						wordForGuess.setCharAt(j, userInput.charAt(0));

					}

				}
				System.out.println(wordForGuess);
				// counting missed attempt and storing wrong input word guess
				if (txtCheck.equals(wordForGuess.toString())) {
					System.out.println("Wrong");
					missesCount++;
					// int f=0;
					wrongGuessWord[f] = userInput;
					f++;
					System.out.println(wordForGuess.toString());

				}

			}
			System.out.println(wordForGuess.toString());
			System.out.println(Integer.toString(missesCount));

			try {
				FileWriter fw = new FileWriter(
						"/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt", true);
				PrintWriter file1 = new PrintWriter(fw);
				System.out.println(" Do you want to add a new word to file -- Y/N ?");
				String ans =  usc.nextLine();;
				if (ans.equals("y") || ans.equals("Y")) {
					System.out.println("ENTER THE NEW WORD");
					String newWord = usc.nextLine();
					file1.write("\n" + newWord);
				}

				fw.close();
			} catch (IOException e1) {  // input/output exception while writing the file .
				e1.printStackTrace();
			}

			
	
		
}
}