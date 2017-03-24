package banwas01_Project2;

import java.util.ArrayList;

/**
 * 
 * @author AmritPal
 *inventory class handles the store's inventory of products
 */
public class Inventory {
	private ArrayList<LineItem> storeInventory = new ArrayList<>();;
	
	/**
	 * empty Constructor
	 */
	public Inventory() {
	}
	/**
	 * adds a line item to the store's inventory 
	 * @param x -takes the lineitem and adds it the araylist of lineitems called storeInventory
	 */
	public void addToInventory(LineItem x){
		storeInventory.add(x);
	}
	/**
	 * returns the array list 
	 * @return storeInventory so inventory can be accessed since it is a private field
	 */
	public ArrayList<LineItem> getInventory(){
		return storeInventory;
	}
	/**
	 * gets a product from the store's inventory
	 * @param name - take the name and checks if the name equals the products name
	 * @return the product by calling the element with the getProduct method
	 */
	public Product getProduct(String name){
		for(LineItem el: storeInventory){
			if(name.equals(el.getProductName()))
			return el.getProduct();
		}
		return null;
	}
	/**
	 * checks if the product exists in the store inventory
	 * @param name - takes the name of a requested product and checks if it exists in the inventory
	 * @return true if it exists
	 * @return false if does not exist
	 */
	public boolean findproduct(String name){
		for(LineItem el: storeInventory){
			if(el.getProductName().toLowerCase().equals(name.toLowerCase())){
			return true;
		}
	}
		return false;
}
	/**
	 * finds the quantity of a product by checking if it exists then getting the quantity
	 * @param name checks if the product is the correct product requested
	 * @return the correct string
	 */
	public String findQuanity(String name){
		for(LineItem el: storeInventory){
			if(el.getProductName().toLowerCase().equals(name.toLowerCase())){
				if (el.getQuantity() ==0){
					return "the store is now out of that product";
				}
				else{
		
			return "the store has " + el.getQuantity() + " of that product in stock" ;
			}
		}
	}
		return "that product is not in stock";
	
}
	/**
	 *changes the quantity when a product is bought
	 * @param name - finds the correct line item
	 * @param amount changes the quantity to the new quantity
	 */
	public void changeQuanity(String name,int amount){
		for(LineItem el: storeInventory){
			if(el.getProductName().toLowerCase().equals(name.toLowerCase())){
				int currentQuantity = el.getQuantity();
				if(!(amount>=currentQuantity) && currentQuantity !=0){
					el.changeQuantity(amount);
				}
			}
		}
	}

		
	
}
