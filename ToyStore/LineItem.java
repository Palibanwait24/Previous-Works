package banwas01_Project2;

/**
 * 
 * @author AmritPal
 *
 *lineitem class gets a product and a quantity
 */
public class LineItem {
	private Product product;
	private int quantity;
	
	/**
	 * constructor creates a line item
	 * @param product - gets a product 
	 * @param quantity - gets a quantity of the product
	 */
	public LineItem(Product product, int quantity){
		this.product=product;
		this.quantity=quantity;
	}
	/** 
	 * used to update the quantity after each sale and return 
	 * @param newQuantity- quantity that should be subtracted from the current quantity
	 */
	public void changeQuantity(int newQuantity){
		quantity-= newQuantity;
	}
	/**
	 * getter method to access the private field product
	 * @return the product
	 */
	public Product getProduct(){
		return this.product;
	}
	/**
	 * getter method to access the private field product name
	 * @return the product name
	 */
	public String getProductName(){
		
		return product.getProductName();
	}
	/**
	 * getter method to access the private field quantity
	 * @return the quantity of a product
	 */
	public int getQuantity(){
		return this.quantity;
	}
	/**
	 * to string method that lists the quantity and the product
	 * @return the string
	 */
	public String toString(){
		String stock = "Quantity: " + quantity +" "+ this.product+" has been added to the inventory";
		
		return stock;
	}
}
