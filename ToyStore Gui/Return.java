package banwas01_Project4;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * 
 * @author AmritPal
 * class that holds code for refund of a product
 */
public class Return extends Transaction {
	private ArrayList<LineItem> refund = new ArrayList<>();
	private int customerId;
	private int employeeId;
	
	/**
	 * constructor of the class and calls the super class
	 * @param customerId -- customer id given by the user
	 * @param employeeId -- employee id given by the user
	 */
	public Return(int customerId, int employeeId ){
		super();
		this.customerId = customerId;
		this.employeeId = employeeId;
	}
	/**
	 * getter method that gives access to private field
	 * @return refund - gives access to refund array list
	 */
	public ArrayList<LineItem> getRefund(){
		return refund;
	}
	/**
	 * adds a return to the refund array list
	 * @param product - takes a product to create a line item
	 * @param quantity -  takes a quantity to create a line item
	 */
	public void addToReturn(Product product, int quantity){
		refund.add(new LineItem(product, quantity));
	}
	/**
	 * returns the to string of the super class to show that the return was a success 
	 */
	 public String toString() {
	        return super.toString() ; 
	    }
}
