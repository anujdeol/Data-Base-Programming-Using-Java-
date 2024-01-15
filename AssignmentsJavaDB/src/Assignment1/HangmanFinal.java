//Student Name: Fahad Bin Rashid
//Student ID: N01577657
//Section: 0NB

//Student Name: Sukhmandeep Singh
//Student ID: N01544654
//Section: 0NB

//Student Name: Anuj Deol 
//Student ID: N01550000
//Section: 0NB


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

public class HangmanFinal {

	public static void main(String[] args) throws Exception {

		JButton btnNewButton1 = new JButton("Play Game");
		String[] wrongGuessWord = new String[10];
		ArrayList<String> wordsList = new ArrayList<>();
		File txtFile = new File("/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt");
		// Check if file exists
		if (!txtFile.exists()) {

			JOptionPane.showMessageDialog(null, "Please enter VALID file", "Message", JOptionPane.INFORMATION_MESSAGE);
			System.exit(1);
		}
		
		//scanning the file
		Scanner txtFileScanner = new Scanner(txtFile);
//add the file words to wordsList array
		do {
			wordsList.add(txtFileScanner.nextLine());

		} while (txtFileScanner.hasNextLine());

		//generating random word
		String randomWord = wordsList.get((int) (Math.random() * wordsList.size()));

		// using the string builder to manipulate the word
		StringBuilder wordForGuess = new StringBuilder(randomWord);
//creating simple GUI
		JFrame myFrame;
		JPanel myPanel;
		JLabel messageLabel;

		JTextField guessField;
		JButton guessButton;
		JButton playButton;
		JButton insertButton;
		JButton addWordFile;
		JLabel resultLabel;
		JLabel resultLabel2;

		myFrame = new JFrame("Hangman Game");

		myPanel = new JPanel();
		messageLabel = new JLabel("Enter your guess: ");

		guessField = new JTextField(20);
		guessButton = new JButton("Guess");
		playButton = new JButton("Play");
		addWordFile = new JButton("Add Word");
		insertButton = new JButton("Insert");
		resultLabel2 = new JLabel(" ");
		resultLabel = new JLabel(" ");

		myPanel.add(messageLabel);

		myPanel.add(guessField);
		myPanel.add(playButton);
		myPanel.add(guessButton);
		myPanel.add(insertButton);
		myPanel.add(addWordFile);
		myPanel.add(resultLabel);
		myPanel.add(resultLabel2);
		myFrame.add(myPanel);

		// Set up the frame

		myFrame.setSize(500, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);

		// getting astrick word form this class 

		class GetWordListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
//creating word with astrick
				for (int i = 0; i < (randomWord.length()); i++) {

					wordForGuess.setCharAt(i, '*');

				}
				
				resultLabel.setText(String.valueOf(wordForGuess)); // storing and showing the result

				int missesCount = 0;
				int f = 0;

			}
		}
		
		//registering the playButton with action listener 
		ActionListener aa = new GetWordListener();
		playButton.addActionListener(aa);
		int missesCount = 0;
		int f = 0;

		class guessWords implements ActionListener {
			int missesCount = 0;
			int f = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
//running the loop tilll you guessed the correct answer
				while (!wordForGuess.toString().equals(randomWord)) {

					// for check the missed attempt
					String txtCheck = wordForGuess.toString();
					// user input

					String userInput = guessField.getText();
					try {
						//handelling null pointer exception
//storing wrong choice of words in to string array
						for (int i = 0; i < wrongGuessWord.length; i++) {
							if (wrongGuessWord[i].equals(userInput)) {

								JOptionPane.showMessageDialog(null,
										"Try another word, you've already tried this letter", "Message",
										JOptionPane.INFORMATION_MESSAGE);
//								

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

					}
					resultLabel.setText(String.valueOf(wordForGuess));//showing the guessed word 
					// counting missed attempt and storing wrong input word guess
					if (txtCheck.equals(wordForGuess.toString())) {
						JOptionPane.showMessageDialog(null, "Wrong Word", "Message", JOptionPane.INFORMATION_MESSAGE);

						missesCount++;//counting the misses attempt
						wrongGuessWord[f] = userInput;//assigning wrong choice char to wrongGuessWord array
						f++;

					}
					guessField.setText("");
					userInput = guessField.getText();

				}
//				showing the result after the loop or guessing the corrextr word
				resultLabel.setText(String.valueOf("Correct answer:" + wordForGuess));
				resultLabel2.setText(String.valueOf("    You missed " + missesCount + "times."));
				JOptionPane.showMessageDialog(null, " CONGRATULATIONS!! YOU GUESS THE CORRECT WORD", "Message",
						JOptionPane.INFORMATION_MESSAGE);

				guessField.setText("");
				JOptionPane.showMessageDialog(null, "Do you want to add a new word to file -- Y/N ?", "Message",
						JOptionPane.INFORMATION_MESSAGE);

			}

		}
		ActionListener ab = new guessWords();
		guessButton.addActionListener(ab);
//class for getting the user decsion to add the word to file
		class addWord implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				String ans = guessField.getText();

				if (ans.equals("y") || ans.equals("Y")) {
					guessField.setText("");
					JOptionPane.showMessageDialog(null, "ENTER THE NEW WORD", "Message",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}

		}
		ActionListener abc = new addWord();
		addWordFile.addActionListener(abc);
//button class to add the word to file
		class insertWord implements ActionListener {

			@Override

			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter fw = new FileWriter(
							"/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt", true);

					PrintWriter file1 = new PrintWriter(fw);

					// guessField.setText("");
					String newWord = guessField.getText();
					file1.write("\n" + newWord);
					JOptionPane.showMessageDialog(null, " NEW WORD ADDED", "Message", JOptionPane.INFORMATION_MESSAGE);
					fw.close();
					System.exit(1);

				} catch (IOException e1) { // input/output exception while writing the file .
					e1.printStackTrace();
				}

			}
		}

		ActionListener abcd = new insertWord();
		insertButton.addActionListener(abcd);

	}
}
