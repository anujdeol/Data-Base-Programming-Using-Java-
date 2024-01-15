package Assignment2;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

class Product implements Serializable {
	public Set<Integer> productidcheck = new HashSet<Integer>();
	private int productid ;
	private String name;
	private double unitPrice;
	private String description;
	private int quanInHand;

	public int getProductId() {
		return productid;
	}

	public void setProductId(int productid) {
//		if (productidcheck.contains(productid)) {
//			JOptionPane.showMessageDialog(null,
//					"Try another word, you've already tried this letter", "Message",
//					JOptionPane.INFORMATION_MESSAGE);
//        } else {
//        	productidcheck.add(productid);
//		
//		this.productid=productid;
//	}
//	}
		this.productid=productid;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		if (unitPrice <= 0) {
			JOptionPane.showMessageDialog(null, "Please enter value above 0", "Message", JOptionPane.INFORMATION_MESSAGE);
	    }
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuanInHand() {
//		if (quanInHand <= 0) {
//			JOptionPane.showMessageDialog(null, "Please enter value above 0", "Message", JOptionPane.INFORMATION_MESSAGE);
//	    }
		return quanInHand;
	}

	@Override
	public String toString() {
		return "Product productID=" + productid + "\n name=" + name + "\n unitPrice=" + unitPrice + "\n description="
				+ description + "\n quanInHand=" + quanInHand +"\n\n";
	}

	public void setQuanInHand(int quanInHand) {
		this.quanInHand = quanInHand;
	}

	public Product(int productid, String name, double unitPrice, String description, int quanInHand) {
		this.productid = productid;
		this.name = name;
		this.unitPrice = unitPrice;
		this.description = description;
		this.quanInHand = quanInHand;
	}

}