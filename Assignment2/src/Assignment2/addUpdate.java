package Assignment2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class addUpdate {

	public Set<Integer> productidcheck = new HashSet<Integer>();
	ArrayList<Product> p=new ArrayList<>();
	public  ObjectOutputStream up;
	int productid;
	String name;
	double unitprice;
	String descp;
	int quantityinhand;
	public static FileInputStream filein;
	public static ObjectInputStream input;
	public static FileOutputStream file1;
	public static ObjectOutputStream oos;
	static {
		try {
			file1 = new FileOutputStream("src/products.dat", true);
			oos = new ObjectOutputStream(file1);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void test111() {
		JTextField textFieldProductID;
		JTextField textFieldName;
		JTextField textFieldQtyInHand;
		JTextField textFieldUnitPrice;

		System.out.println("hello");

		JFrame frame = new JFrame();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel myPanel1 = new JPanel();
		frame.getContentPane().add(myPanel1);

		JLabel prodLabel = new JLabel("ProductID:");
		myPanel1.add(prodLabel);

		textFieldProductID = new JTextField();
		myPanel1.add(textFieldProductID);
		textFieldProductID.setColumns(10);

		JLabel nameLabel = new JLabel("Name");
		myPanel1.add(nameLabel);

		textFieldName = new JTextField();
		myPanel1.add(textFieldName);
		textFieldName.setColumns(10);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);

		JLabel descLabel = new JLabel("Description");
		panel.add(descLabel);

		JTextArea textAreaDesc = new JTextArea();
		textAreaDesc.setRows(5);
		textAreaDesc.setColumns(20);
		panel.add(textAreaDesc);

		JPanel myPanel3 = new JPanel();
		frame.getContentPane().add(myPanel3);

		JLabel QtyLabrl = new JLabel("Quantity in Hand:");
		myPanel3.add(QtyLabrl);

		textFieldQtyInHand = new JTextField();
		myPanel3.add(textFieldQtyInHand);
		textFieldQtyInHand.setColumns(10);

		JLabel unitPriceLabel = new JLabel("Unit Price");
		myPanel3.add(unitPriceLabel);

		textFieldUnitPrice = new JTextField();
		myPanel3.add(textFieldUnitPrice);
		textFieldUnitPrice.setColumns(8);

		JPanel myPanel4 = new JPanel();
		frame.getContentPane().add(myPanel4);

		JButton addBtn = new JButton("Add");
		myPanel4.add(addBtn);

		JButton updateBtn = new JButton("Update");
		myPanel4.add(updateBtn);

		JButton firstBtn = new JButton("First");
		myPanel4.add(firstBtn);

		JButton prevBtn = new JButton("Previous");
		myPanel4.add(prevBtn);

		JButton nextBtn = new JButton("Next");
		myPanel4.add(nextBtn);

		JPanel myPanel5 = new JPanel();
		frame.getContentPane().add(myPanel5);

		JButton lastBtn = new JButton("Last");
		myPanel5.add(lastBtn);
		frame.setVisible(true);

		// add product details
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//
				if (textFieldProductID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter product ID", "Message",

							JOptionPane.INFORMATION_MESSAGE);
					return;
					
				}

				productid = Integer.parseInt(textFieldProductID.getText());
				if (productidcheck.contains(productid)) {
					JOptionPane.showMessageDialog(null, "Product is already exists, Add unique product", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					
				}
				//
				if (textFieldName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter name", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;

				}

				name = textFieldName.getText();

				if (textFieldUnitPrice.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter unit price", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				unitprice = Double.parseDouble(textFieldUnitPrice.getText());
				if (unitprice < 0) {
					JOptionPane.showMessageDialog(null, "Please enter price above 0", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (textAreaDesc.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter product description", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				descp = textAreaDesc.getText();

				if (textFieldQtyInHand.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter quantity in hand", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				quantityinhand = Integer.parseInt(textFieldQtyInHand.getText());
				if (quantityinhand <= 0) {
					JOptionPane.showMessageDialog(null, "Quantity in hand is invalid", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				productidcheck.add(productid);
				Product product = new Product(productid, name, unitprice, descp, quantityinhand);

				try {
					oos.writeObject(product);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				textFieldProductID.setText("");
				textFieldUnitPrice.setText("");
				textFieldQtyInHand.setText("");
				textFieldName.setText("");
				textAreaDesc.setText("");
			}

		});

		// first button shows the first product
		firstBtn.addActionListener(new ActionListener() {
			Product prod;

			public void actionPerformed(ActionEvent e) {

				try {
					filein = new FileInputStream("src/products.dat");
					input = new ObjectInputStream(filein);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				try {
					prod = (Product) input.readObject();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				productid = prod.getProductId();
				unitprice = prod.getUnitPrice();
				quantityinhand = prod.getQuanInHand();
				descp = prod.getDescription();
				name = prod.getName();

				textFieldProductID.setText("" + productid);
				textFieldUnitPrice.setText("" + unitprice);
				textFieldQtyInHand.setText("" + quantityinhand);
				textFieldName.setText("" + name);
				textAreaDesc.setText("" + descp);

			}

		});

		// last button
		lastBtn.addActionListener(new ActionListener() {
			Product prod;

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					filein = new FileInputStream("src/products.dat");
					input = new ObjectInputStream(filein);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				try {
					while (true) {
						prod = (Product) input.readObject();
						productid = prod.getProductId();
						unitprice = prod.getUnitPrice();
						quantityinhand = prod.getQuanInHand();
						descp = prod.getDescription();
						name = prod.getName();
					}
				} catch (ClassNotFoundException | IOException e1) {
					System.out.println(" LAST OBJECT REACHED ");
				}

				textFieldProductID.setText("" + productid);
				textFieldUnitPrice.setText("" + unitprice);
				textFieldQtyInHand.setText("" + quantityinhand);
				textFieldName.setText("" + name);
				textAreaDesc.setText("" + descp);

			}
		});
		
		
		
		//next button
		
		nextBtn.addActionListener(new ActionListener() {
			Product prod;
			int prodid;
      @Override
  public void actionPerformed(ActionEvent e) {	    	  
			
			try {
				filein = new FileInputStream("src/products.dat");
				input = new ObjectInputStream(filein);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			try {
				while (true) {
					
					prod = (Product) input.readObject();
					 prodid = prod.getProductId();
					if(prodid == productid) {
						break;
					}	
				}
				prod = (Product) input.readObject();
				productid= prod.getProductId();
				unitprice = prod.getUnitPrice();
				quantityinhand = prod.getQuanInHand();
				descp = prod.getDescription();
				name = prod.getName();
			} catch (ClassNotFoundException | IOException e1) {
				System.out.println(" next object ");
			}
			
			textFieldProductID.setText("" + productid);
			textFieldUnitPrice.setText("" + unitprice);
			textFieldQtyInHand.setText("" + quantityinhand);
			textFieldName.setText("" + name);
			textAreaDesc.setText("" + descp);

			
			
    	  }
      });
		
		
		//prev button
		
		
		prevBtn.addActionListener(new ActionListener() {
			Product prod;
			int prodid;
			   
      @Override
  public void actionPerformed(ActionEvent e) {	    	  
    	  int count =0;
			try {
				filein = new FileInputStream("src/products.dat");
				input = new ObjectInputStream(filein);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			try {
				while (true) {
					
					prod = (Product) input.readObject();
					count++;
					 prodid = prod.getProductId();
					if(prodid == productid) {
						System.out.println(count);
						break;
					}	
				}
				try {
					filein = new FileInputStream("src/products.dat");
					input = new ObjectInputStream(filein);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
				for(int i = 1;i<count;i++) {
					System.out.println("for loop running");
					
					prod = (Product) input.readObject();
				}
				productid= prod.getProductId();
				unitprice = prod.getUnitPrice();
				quantityinhand = prod.getQuanInHand();
				descp = prod.getDescription();
				name = prod.getName();
			} catch (ClassNotFoundException | IOException e1) {
				System.out.println(" previous object ");
			}
			
			
			textFieldProductID.setText("" + productid);
			textFieldUnitPrice.setText("" + unitprice);
			textFieldQtyInHand.setText("" + quantityinhand);
			textFieldName.setText("" + name);
			textAreaDesc.setText("" + descp);
			
			
    	  }
      });
		
	
		updateBtn.addActionListener(new ActionListener() {
			Product prod;
			
      @Override
  public void actionPerformed(ActionEvent e) {	    	             
			
			try {
				filein = new FileInputStream("src/products.dat");
				input = new ObjectInputStream(filein);
			
				while (true) {
					prod = (Product) input.readObject();
					if(prod == null)
						break;
					
					 p.add(new Product(prod.getProductId(), prod.getName(), prod.getUnitPrice(), prod.getDescription(), prod.getQuanInHand()));
					 
		                 
				}
				
				for(int i=0;i<p.size();i++)
				{
					
					if(p.get(i).getProductId() == Integer.parseInt(textFieldProductID.getText()))
					{
						p.get(i).setProductId(Integer.parseInt(textFieldProductID.getText()));
						p.get(i).setName(textFieldName.getText());
						p.get(i).setUnitPrice(Double.parseDouble(textFieldUnitPrice.getText()));
						p.get(i).setDescription(textAreaDesc.getText());
						p.get(i).setQuanInHand(Integer.parseInt(textFieldQtyInHand.getText()));
						
						
						
						
						
						
					}
				
		                 
			                	for(int k=0;k<p.size();k++) {
			                		
			                		try {
			                    		file1 = new  FileOutputStream("src/products.dat",false);
			                    		up = new  ObjectOutputStream(file1);
			                    		up.writeObject(p);
			                    		
			                    	}catch(IOException e2) {
			                    		System.out.println(e2);
			                    		//System.out.println("check1 ");
			                    	}
			                		
		                		
						}
				} 
		             	
			}
		
			 catch (ClassNotFoundException | IOException e1) {
				System.out.println(e1);
				System.out.println("check2 ");
			}
			
			
		}
      } );

	}
}
