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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class test2 {

	public static void main(String[] args) throws Exception {

		JButton btnNewButton1 = new JButton("Play Game");
		String[] wrongGuessWord = new String[10];
		ArrayList<String> wordsList = new ArrayList<>();

		String randomWord = wordsList.get((int) (Math.random() * wordsList.size()));
		StringBuilder wordForGuess = new StringBuilder(randomWord);

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

		myPanel.add(messageLabel);

		myPanel.add(guessField);
		myPanel.add(guessButton);
		myPanel.add(btnNewButton1);
		myPanel.add(resultLabel);

		myFrame.add(myPanel);

		// Set up the frame

		myFrame.setSize(500, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);

		File txtFile = new File("/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt");
		// Check if file exists
				
		class GetWordListener implements ActionListener {
			Scanner txtFileScanner;

			@Override
			public void actionPerformed(ActionEvent e) {

				// making the object of the file
				
				// reading the file through scanner

				try {Scanner txtFileScanner = new Scanner(txtFile);
					if (!txtFile.exists()) {
						System.out.print("File \"" + txtFile.getName() + "\" does not exist");
						System.exit(1);
					}

					
					
				
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// user input scanner
				//Scanner usc = new Scanner(System.in);

				// making a array list to store the words from the file
				ArrayList<String> wordsList = new ArrayList<>();

				String[] wrongGuessWord = new String[10];

				// addiing all the words from text file to list array
				while (txtFileScanner.hasNextLine()) {
				
					wordsList.add(txtFileScanner.nextLine());
				}
				
				// taking a random word from array list

				String randomWord = wordsList.get((int) (Math.random() * wordsList.size()));

				// using the string builder to manipulate the word
				StringBuilder wordForGuess = new StringBuilder(randomWord);

				// loop till the length of the random word to create astrick
				for (int i = 0; i < randomWord.length(); i++) {

					wordForGuess.setCharAt(i, '*');

				}
			}
		}
ActionListener a12 = new GetWordListener();
btnNewButton1.addActionListener(a12);

		ActionListener a1 = new ActionListener() { // created listener object

			// override method to define the action
			@Override
			public void actionPerformed(ActionEvent e) {

				// loop till random and guess word are same
				while (!wordForGuess.toString().equals(randomWord)) {

					// for check the missed attempt
					String txtCheck = wordForGuess.toString();
					// user input

					String userInput = guessField.getText();

					try {
						if (userInput.length() != 1 || userInput.matches(".*\\d.*")) // validating user input
						{
//							System.out.println("Please enter VALID character only");
							JOptionPane.showMessageDialog(null, "Please enter VALID character only", "Message",
									JOptionPane.INFORMATION_MESSAGE);
							guessField.setText("");
							userInput = guessField.getText();
						}

						resultLabel.setText(String.valueOf(wordForGuess));

						for (int i = 0; i < wrongGuessWord.length; i++) {
							// String userInput = usc.next();
							if (wrongGuessWord[i].equals(userInput)) {
								System.out.println("Try another word, you've already tried this letter");
								guessField.setText("");
								userInput = guessField.getText();
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

						resultLabel.setText(String.valueOf(wordForGuess));

					}

					resultLabel.setText(String.valueOf(wordForGuess));

					// counting missed attempt and storing wrong input word guess
					if (txtCheck.equals(wordForGuess.toString())) {
//						System.out.println("Wrong");
						JOptionPane.showMessageDialog(null, "Wrong word", "Message", JOptionPane.INFORMATION_MESSAGE);
						int missesCount = 0;
						int f = 0;

						missesCount++;
						// int f=0;
						wrongGuessWord[f] = userInput;
						f++;
						guessField.setText("");
						userInput = guessField.getText();
//						System.out.println(wordForGuess);

					}

				}
				resultLabel.setText(String.valueOf(wordForGuess));
//				System.out.println(wordForGuess);
//				System.out.println(missesCount);

				try {
					FileWriter fw = new FileWriter(
							"/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt", true);
					PrintWriter file1 = new PrintWriter(fw);
//					System.out.println(" Do you want to add a new word to file -- Y/N ?");
					JOptionPane.showMessageDialog(null, "Do you want to add a new word to file -- Y/N ?", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					guessField.setText("");
					String userInput = guessField.getText();

					String ans = guessField.getText();
					if (ans.equals("y") || ans.equals("Y")) {
//						System.out.println("ENTER THE NEW WORD");
						JOptionPane.showMessageDialog(null, "ENTER THE NEW WORD", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						String newWord = guessField.getText();
						file1.write("\n" + newWord);
					}

					fw.close();
				} catch (IOException e1) { // input/output exception while writing the file .
					e1.printStackTrace();
				}

			}

		};

		guessButton.addActionListener(a1);

	}
}
