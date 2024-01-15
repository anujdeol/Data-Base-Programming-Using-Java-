package Assignment2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class findDisplay {
	
	int productid;
	String name;
	double unitprice;
	String descp;
	int quantityinhand;
	int value = 1;

	double doubleto = 1.0;
	double doublefrom = 1.0;
	public static FileInputStream file1;
	public static ObjectInputStream input;

	private JFrame frame;
	private JTextField textFieldFrom;
	private JTextField textFieldToo;
	private JTextField textFieldKeyword;

	public void test122() {
		JFrame frame = new JFrame();
		ButtonGroup group = new ButtonGroup();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel1 = new JPanel();
		
		frame.getContentPane().add(panel1);
		
		JRadioButton radioBtnPriceRange = new JRadioButton("Price Range");
		panel1.add(radioBtnPriceRange);
        group.add(radioBtnPriceRange);
		
		textFieldFrom = new JTextField();
		panel1.add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		textFieldToo = new JTextField();
		panel1.add(textFieldToo);
		textFieldToo.setColumns(10);
	
		
		JButton findDisplayBtn = new JButton("Find");
		panel1.add(findDisplayBtn);
		
		
		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2);
		
		JRadioButton radioKeyword = new JRadioButton("Keyword");
		panel2.add(radioKeyword);
		group.add(radioKeyword);
		
		textFieldKeyword = new JTextField();
		panel2.add(textFieldKeyword);
		textFieldKeyword.setColumns(10);
		
		JPanel panel3 = new JPanel();
		frame.getContentPane().add(panel3);
		
		JRadioButton radioAll = new JRadioButton("All");
		panel3.add(radioAll);
		group.add(radioAll);
		
		JTextArea textAreaDesc = new JTextArea();
		textAreaDesc.setRows(20);
		textAreaDesc.setColumns(15);
		panel3.add(textAreaDesc);
		frame.setVisible(true);
		
		

		findDisplayBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					file1 = new FileInputStream("src/products.dat");
					input = new ObjectInputStream(file1);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Product prod;

				if (radioAll.isSelected()) {
					try {
						while (true) {
							prod = (Product) input.readObject();
							System.out.println(prod);
							textAreaDesc.setText(textAreaDesc.getText() + prod);

						}
					} catch (ClassNotFoundException | IOException e1) {
						System.out.println(" END OF FILE");
					}

				}

				if (radioKeyword.isSelected()) {

					try {
						while (true) {
							prod = (Product) input.readObject();

							String name = prod.getName();
							System.out.println(name);
							if (name.contains(textFieldKeyword.getText())) {
								textAreaDesc.setText(textAreaDesc.getText() + prod);
							}

						}
					} catch (ClassNotFoundException | IOException e1) {

						System.out.println(" END OF FILE");
					}

				}
				if (radioBtnPriceRange.isSelected()) {
					try {
						while (true) {

							prod = (Product) input.readObject();
							unitprice = prod.getUnitPrice();
							if (unitprice <= Double.parseDouble(textFieldToo.getText())
									&& unitprice >= Double.parseDouble(textFieldFrom.getText())) {

								textAreaDesc.setText(textAreaDesc.getText() + prod);

							}
						}
					} catch (ClassNotFoundException | IOException e1) {

						System.out.println(" END OF FILE");
					}

				}
			}
		});
		
		
	}
}
	



