package presentation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.Person;
import data.RandomIO;

public class present {
	private static final long serialVersionUID = -1752296510868884441L;

	
	public static void main(String[] args) {
	
        JFrame frame = new JFrame("Random File Processing");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
     
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel myPanel1 = new JPanel();
        JPanel myPanel2 = new JPanel();
        JPanel myPanel3 = new JPanel();
        JPanel myPanel4 = new JPanel();
        JPanel myPanel5 = new JPanel();
        JPanel myPanel6 = new JPanel();

        frame.getContentPane().add(myPanel1);
        frame.getContentPane().add(myPanel2);
        frame.getContentPane().add(myPanel3);
        frame.getContentPane().add(myPanel4);
        frame.getContentPane().add(myPanel5);
        frame.getContentPane().add(myPanel6);

        JLabel prodLabel = new JLabel("Record#:");
        myPanel1.add(prodLabel);

        JTextField recordNumber = new JTextField();
        myPanel1.add(recordNumber);
        recordNumber.setColumns(20);
   

        JLabel firstNameLabel = new JLabel("First Name");
        myPanel2.add(firstNameLabel);

        JTextField textFieldFirstName = new JTextField();
        myPanel2.add(textFieldFirstName);
        textFieldFirstName.setColumns(20);

        JLabel lastNameLabel = new JLabel("Last Name");
        myPanel3.add(lastNameLabel);

        JTextField textFieldLastName = new JTextField();
        myPanel3.add(textFieldLastName);
        textFieldLastName.setColumns(20);

        JLabel ageLabel = new JLabel("Age");
        myPanel4.add(ageLabel);

        JTextField ageTextField = new JTextField();
        myPanel4.add(ageTextField);
        ageTextField.setColumns(20);

        JLabel phoneLabel = new JLabel("Phone");
        myPanel5.add(phoneLabel);

        JTextField phoneTextField = new JTextField();
        myPanel5.add(phoneTextField);
        phoneTextField.setColumns(20);

        JButton addBtn = new JButton("Add");
        myPanel6.add(addBtn);

        JButton updateBtn = new JButton("Find");
        myPanel6.add(updateBtn);

        frame.setVisible(true);
		// Assuming that you have declared the GUI text fields as follows:
		// JTextField firstNameField, lastNameField, phoneField, ageField;

		// Assuming that you have declared the file object as follows:
		// File file = new File("persons.dat");

		// Assuming that you have declared the add button as follows:
		// JButton addButton;

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int recordid = Integer.parseInt(recordNumber.getText());
                    String firstName = textFieldFirstName.getText();
                    String lastName = textFieldLastName.getText();
                    String phone = phoneTextField.getText();
                    int age = Integer.parseInt(ageTextField.getText());
                    if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
                        throw new IllegalArgumentException("Please fill in all required fields");
                    }
                    Person person = new Person(firstName, lastName, phone, age, recordid);
                    RandomIO.addPerson(person);
                    // Show a message or perform other actions to indicate success
                    recordNumber.setText("");
                    textFieldFirstName.setText("");
                    textFieldLastName.setText("");
                    phoneTextField.setText("");
                    ageTextField.setText("");
                } catch (NumberFormatException ex) {
                    // Handle exception if invalid integer input is entered for recordid or age
                	JOptionPane.showMessageDialog(null, "Please enter all filed values", "Message",
							JOptionPane.INFORMATION_MESSAGE);
                   
                } catch (IOException ex) {
                    // Handle exception if an error occurs while writing to the file
                	JOptionPane.showMessageDialog(null, "Error writing to file", "Message",
							JOptionPane.INFORMATION_MESSAGE);
                  
                } catch (IllegalArgumentException ex) {
                    // Handle exception if required fields are not filled in
                    System.err.println(ex.getMessage());
                }
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int recordNum = Integer.parseInt(recordNumber.getText());
                    Person person = RandomIO.findPerson(recordNum);
                    if (person != null) {  
                        recordNumber.setText(""+person.getrecordid());
                        textFieldFirstName.setText(""+person.getFirstName());
                        textFieldLastName.setText(""+person.getLastName());
                        phoneTextField.setText(""+person.getPhone());
                        ageTextField.setText(""+person.getAge());
                    } else {
                    	JOptionPane.showMessageDialog(null, "Record not found", "Message",
    							JOptionPane.INFORMATION_MESSAGE);
                       
                    }
                } catch (NumberFormatException ex) {
                    // Handle exception if invalid integer input is entered for recordNum
                	JOptionPane.showMessageDialog(null, "Invalid integer input", "Message",
							JOptionPane.INFORMATION_MESSAGE);
                   
                   
                }
            }
        });

	}

}
