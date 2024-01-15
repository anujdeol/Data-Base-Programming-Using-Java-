package Assignment1;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class copyHangman {
	
	public static void main(String[] args)  throws Exception {


	
	JFrame myFrame;
	 JPanel myPanel;
	 JLabel messageLabel;
	
	 JTextField guessField;
	 JButton guessButton;
	 JLabel resultLabel;
	 JLabel resultLabel2;


		myFrame = new JFrame("Hangman Game");

		myPanel = new JPanel();
		messageLabel = new JLabel("Enter your guess: ");
		
		
		guessField = new JTextField(10);
		guessButton = new JButton("Guess");
		resultLabel = new JLabel(" ");
		resultLabel2 = new JLabel(" ");

		myPanel.add(messageLabel);
		
		
		myPanel.add(guessField);
		myPanel.add(guessButton);
		myPanel.add(resultLabel);
		myPanel.add(resultLabel2);

		myFrame.add(myPanel);

		// Set up the frame

		myFrame.setSize(500, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		
		
		
		// making the object of the file
//				File txtFile = new File("/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt");
//				// Check if file exists
//						if (!txtFile.exists()) {
//							System.out.print("File \"" + txtFile.getName() + "\" does not exist");
//							System.exit(1);
//						}

				// reading the file through scanner

//				Scanner txtFileScanner = new Scanner(txtFile);
//
//				// user input scanner
//				Scanner usc = new Scanner(System.in);

				// making a array list to store the words from the file
				ArrayList<String> wordsList = new ArrayList<>();

				char[] wrongGuessWord = new char[5];
				char temp1 = 0;
				wrongGuessWord
				try (Scanner txtFileScanner = new Scanner(
						new File("/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt"))) {

					while (txtFileScanner.hasNextLine()) {
						wordsList.add(txtFileScanner.nextLine());
					}
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}


				// addiing all the words from text file to list array
//				do {
//					wordsList.add(txtFileScanner.nextLine());
//
//				} while (txtFileScanner.hasNextLine());
//
//				// taking a random word from array list

				String randomWord = wordsList.get((int) (Math.random() * wordsList.size()));
                char[] wordForGuess =randomWord.toCharArray();
				// using the string builder to manipulate the word
//				StringBuilder wordForGuess = new StringBuilder(randomWord);

				// loop till the length of the random word to create astrick
				for (int i = 0; i <((randomWord.length())-1); i++) {

					wordForGuess[i]='*';
					
				}
				resultLabel.setText(String.valueOf(wordForGuess));
				

		

		ActionListener a1 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				int missesCount = 0;
				int f = 0;
			
				while (!wordForGuess.toString().equals(randomWord)) {
					String userInput = guessField.getText();
					// for check the missed attempt
					String txtCheck = wordForGuess.toString();
					// user input
					
					
//		
//					if (userInput.length()!=1) // validating user input
//					{
//							JOptionPane.showMessageDialog(null, "Please enter a valid input!", "Message", JOptionPane.INFORMATION_MESSAGE);
//							guessField.setText("");
//							userInput = guessField.getText();
//							//System.out.println("Please enter VALID character only");
//						}  
					
					// marked for review
					
					try {
					
					for (int i = 0; i <= ((wrongGuessWord.length)-1); i++) {
						    if (userInput.charAt(0) == wrongGuessWord[i]) {
					    	JOptionPane.showMessageDialog(null, "Try another word, you've already tried this letter", "Message", JOptionPane.INFORMATION_MESSAGE);
					    	guessField.setText("");
						        messageLabel.setText("Try another word, you've already tried this letter");
						        userInput = guessField.getText();
						        continue;
					    }
					}
				} catch ( ArrayIndexOutOfBoundsException wee) {
					

					}

				// loop for checking and assigning the user input to guess word
					for (int j = 0; j < randomWord.length(); j++) {

						if (userInput.charAt(0) == randomWord.charAt(j)) {

							wordForGuess[j]=userInput.charAt(0);
//							wordForGuess.setCharAt(j, userInput.charAt(0));

						}

					}
					//System.out.println(wordForGuess);
					// counting missed attempt and storing wrong input word guess
					if (txtCheck.equals(wordForGuess.toString())) {
						messageLabel.setText("Wrong");
						missesCount++;
						// int f=0;
//						wrongGuessWord[f] = userInput.charAt(0);
//			
						f++;
						resultLabel.setText(String.valueOf(wordForGuess));
						guessField.setText("");

					}

				}
				resultLabel.setText(String.valueOf(wordForGuess));
				resultLabel2.setText(Integer.toString(missesCount));


				try {
					FileWriter fw = new FileWriter(
							"/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt", true);
					PrintWriter file1 = new PrintWriter(fw);
					messageLabel.setText(" Do you want to add a new word to file -- Y/N ?");
					guessField.setText("");
					String ans =guessField.getText();
					if (ans.equals("y") || ans.equals("Y")) {
						messageLabel.setText("ENTER THE NEW WORD");
						guessField.setText("");
						String newWord = guessField.getText();
						file1.write("\n" + newWord);
					}

					fw.close();
				} catch (IOException e) {  // input/output exception while writing the file .
					e.printStackTrace();
				}

				
			}
		};
		
		guessButton.addActionListener(a1);
	}
	

	
}



//	    guessButton.addActionListener(new GuessButtonListener());
//
//	   
//    // Initialize the word to guess and the list of correct guesses
//    initializeGame();
//  }
//
//  private void initializeGame() {
//    // Randomly select a word from a list of words
// 
//	  
//class GuessButtonListener implements ActionListener 
//	  
//	  {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//	
//			
//		}

//	  }

//    public void actionPerformed(ActionEvent event) {
//      // Get the user's guess
//      String guess = guessField.getText();
//
//      // Validate the user's guess
//      if (guess.length() != 1) {
//        resultLabel.setText("Please enter a single letter.");
//      } else if (!Character.isLetter(guess.charAt(0))) {
//        resultLabel.setText("Please enter a letter.");
//      } else {
//        // Check if the guess is correct
//        char letter = guess.charAt(0);
//        if (wordToGuess.contains(guess)) {
//          // Update the list of correct guesses
