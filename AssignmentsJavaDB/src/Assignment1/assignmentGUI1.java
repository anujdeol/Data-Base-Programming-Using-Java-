package Assignment1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class assignmentGUI1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	protected Scanner txtFileScanner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					assignmentGUI1 frame = new assignmentGUI1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public assignmentGUI1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter your guess:");
		lblNewLabel.setBounds(24, 20, 117, 16);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(169, 15, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(78, 94, 300, 170);
		contentPane.add(textArea);

		JButton btnNewButton = new JButton("GUESS");
		JButton btnNewButton1 = new JButton("Play Game");
		String[] wrongGuessWord = new String[10];
		ArrayList<String> wordsList = new ArrayList<>();

		String randomWord = wordsList.get((int) (Math.random() * wordsList.size()));
		StringBuilder wordForGuess = new StringBuilder(randomWord);
		ActionListener a12 = new ActionListener() {
//			btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae1) {

				
//						
//							// addiing all the words from text file to list array
//							while (txtFileScanner.hasNextLine());
//						
//								wordsList.add(txtFileScanner.nextLine());
				//
//							


				try (Scanner txtFileScanner = new Scanner(
						new File("/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt"))) {

					while (txtFileScanner.hasNextLine()) {
						wordsList.add(txtFileScanner.nextLine());
					}
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}

				// taking a random word from array list

				

				// using the string builder to manipulate the word
				

				// loop till the length of the random word to create astrick
				for (int i = 0; i < randomWord.length(); i++) {

					wordForGuess.setCharAt(i, '*');

				}
				textArea.setText(wordForGuess.toString());
				// loop till random and guess word are same

			}
		};

		btnNewButton1.addActionListener(a12);
		btnNewButton1.setBounds(105, 53, 117, 29);
		contentPane.add(btnNewButton1);
	

	ActionListener a11 = new ActionListener() {
//	btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				while (!wordForGuess.toString().equals(randomWord)) {

					// for check the missed attempt
					String txtCheck = wordForGuess.toString();
					// user input
					String userInput = textField.getText();

					try {
						if (userInput == "") // validating user input
						{
							textArea.setText("Please enter VALID character only");
							textField.setText("");

						}

						for (int i = 0; i < wrongGuessWord.length; i++) {
							// String userInput = usc.next();
							//String temp = wrongGuessWord[i];
							if (wrongGuessWord[i].equals(userInput)) {
								textArea.setText("Try another word, you've already tried this letter");
								textField.setText("");
								userInput = textField.getText();
								//continue;
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
					//System.out.println(wordForGuess);
					int missesCount = 0;
					int f = 0;
					// counting missed attempt and storing wrong input word guess
					if (txtCheck.equals(wordForGuess.toString())) {
						textArea.setText("Wrong");
						textField.setText("");
						missesCount++;
						// int f=0;
						wrongGuessWord[f] = userInput;
						f++;
						textArea.setText(wordForGuess.toString());

					}
					textArea.setText(wordForGuess.toString());
					textArea.setText(Integer.toString(missesCount));

				}

				try {
					FileWriter fw = new FileWriter(
							"/Users/anujdeol/eclipse-workspace/AssignmentsJavaDB/src/Assignment1/abc.txt", true);
					PrintWriter file1 = new PrintWriter(fw);
					textArea.setText(" Do you want to add a new word to file -- Y/N ?");
					textField.setText("");
					String ans = textField.getText();
					if (ans.equals("y") || ans.equals("Y")) {
						textArea.setText("ENTER THE NEW WORD");
						textField.setText("");
						String newWord = textField.getText();
						file1.write("\n" + newWord);
					}

					fw.close();
				} catch (IOException e1) { // input/output exception while writing the file .
					e1.printStackTrace();
				}

			}
		};
		btnNewButton.addActionListener(a11);
		btnNewButton.setBounds(105, 53, 117, 29);
		contentPane.add(btnNewButton);

}}