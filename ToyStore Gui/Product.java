package banwas01_Project4;

import java.io.Serializable;
import java.text.DecimalFormat;
/**
 * 
 * @author AmritPal
 *product class that creates a product with info
 */
public class Product implements Serializable {
	private DecimalFormat format = new DecimalFormat("$###,###.00");
	private String productName;
	private String description;
	private double price;
	/**
	 * constructor of the product
	 * @param productName-- takes in the name of the product
	 * @param descritpion-- takes in the description of the product given by the user
	 * @param price -- takes in the price of the product given by the user
	 */
	public Product(String productName,String descritpion, double price){
		this.productName=productName;
		this.description=descritpion;
		this.price=price;
	}
	/**
	 * getter method that gives access to private field
	 * @return productName - name of product
	 */
	public String getProductName(){
		return productName;
	}
	/**
	 * getter method that gives access to private field
	 * @return price - price of product
	 */
	public double getPrice(){
		return price;
	}
	/**
	 * assembles to string method with variables so that when a product is created prints info to consol
	 */
	public String toString(){
		return "Product name: " + productName + " Description: " + description + "  " + "Price: " + format.format(price);
	}

}
