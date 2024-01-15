package Assignment2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main {
	

	public static void main(String[] args) {
		
		JFrame frame = new Menu();


		frame.setDefaultCloseOperation(3);

		frame.setTitle("Product Main GUI");
		
		JLabel label = new JLabel("Product Management System", SwingConstants.CENTER);
		label.setFont(label.getFont().deriveFont(Font.BOLD, 20));
		frame.add(label, BorderLayout.CENTER);
		frame.setSize(500, 500);
		
		
		frame.setVisible(true);

	}


}
