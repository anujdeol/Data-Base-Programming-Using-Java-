package Assignment2;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JFrame  {
	private static final int FRAME_WIDTH = 300;
	private static final int HEIGTH = 400;
	private static final int FRAME_HEIGTH = 500;

	public JLabel displayLabel;
	private String type;
	private String size;

	public Menu() {
		setSize(FRAME_WIDTH, FRAME_HEIGTH);
		displayLabel = new JLabel("");
		add(displayLabel, BorderLayout.NORTH);

//		create the menu bar

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

//		create the "file" menu on the bar(JMenu) 
		menuBar.add(createFileMenu());
		menuBar.add(createProductMenu());

	}

	public JMenu createFileMenu() {
		JMenu menuFile = new JMenu("File");

		menuFile.add(createFileMenuItem());
		return menuFile;
	}
//creating the menu items
	public JMenuItem createFileMenuItem() {
		JMenuItem itemExit = new JMenuItem("Exit");
		class MenuItemListner implements ActionListener {

			public void actionPerformed(ActionEvent event) {
				System.exit(0);

			}

		}
		ActionListener listener = new MenuItemListner();
		itemExit.addActionListener(listener);
		return (itemExit);

	}

	public JMenu createProductMenu() {
		JMenu productOption = new JMenu("Product");
		productOption.add(createAddUpdate());
		productOption.add(createFindDisplay());
		return productOption;

	}

	private JMenuItem createAddUpdate() {
		//on click event listener to open the page
		JMenuItem addUpdate = new JMenuItem("Add/Update");
		addUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Code to switch to page add update
		    	 addUpdate myObject = new addUpdate();
		         myObject.test111();
		    }
		});
		return addUpdate;
	

   
	}
	

	private JMenuItem createFindDisplay() {
		JMenuItem findDisplay = new JMenuItem("FindDisplay");
		//on click event listener to open the page
		findDisplay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Code to switch to page find and display
		    	 findDisplay myObject1 = new findDisplay();
		         myObject1.test122();
		    }
		});
		return findDisplay;
	}
}


	
