package banwas01_Project2;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author AmritPal
 *sales class that handles the sales 
 */
public class Sales extends Transaction{
	private ArrayList<LineItem> sale = new ArrayList<>();
	private int customerId;
	private int employeeId;
	/**
	 * sales constructor that creates a sale
	 * @param customerId - takes a reference to the customer
	 * @param employeeId - takes a reference to a employee
	 */
	public Sales(int customerId, int employeeId ){
		super();
		this.customerId = customerId;
		this.employeeId = employeeId;
	}
/**
 * gets the amount spent buy a customer by going through the sale arrayList and getting the quantity and the price of a product
 * @return the dollar amount the customer has spent
 */
	public double getAmount(){
		int quantity=0;
		double price = 0;
		double amount =0;
		for(LineItem get: sale){
			quantity = get.getQuantity();
			Product product = get.getProduct();
			price = product.getPrice();
			amount+= price*quantity;
			
		}
		return amount;
	}
	/**
	 * gets the number of a products in the sales arraylist
	 * @return amount of the product
	 */
	public int getItemSold(){
		int amount = 0;
		for(LineItem get: sale){
			Product product = get.getProduct();
				amount+= get.getQuantity();
			}
		return amount;
	}
	/**
	 * returns the sales arraylist
	 * @return returns the sales arraylist
	 */
	public ArrayList<LineItem> getArrayList(){
		return sale;
		
	}
	/**
	 * goes through the sales array list and gets the quantity of a specific product
	 * @param name- takes the name and checks if the name is equal to a specific product
	 * @return - amount of that item 
	 */
	public int getAmountSold(String name){
		int amount = 0;
		for(LineItem get: sale){
			Product product = get.getProduct();
			String x = product.getProductName();
			if (x.equals(name)){
				amount+= get.getQuantity();
				
			}
		}
		return amount;
	}
	/**
	 * adds the line item to the sales array
	 * @param product- takes in the product the customer buys and the employee sells
	 * @param quantity - takes in the quantity of the product
	 */
	public void addToSale(Product product, int quantity){
		sale.add(new LineItem(product, quantity));
	}
	/**
	 * gets the string from transaction
	 * @return the super string of transaction
	 */
	public String ToString(){
		return super.toString();
	}
}
