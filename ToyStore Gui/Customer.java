package banwas01_Project4;
import java.io.Serializable;
import java.util.ArrayList;

/** 
 * @author AmritPal
 *
 *class that holds the age and arraylist of sales object for the customer. also references the person class
 */
public class Customer extends Person implements Serializable{
		private int age;
		private ArrayList<Sales> bought = new ArrayList<>();
		/**
		 * constructor of the customer class using the parameters
		 * @param name- name of the customer
		 * @param phoneNumber - phone number where the customer can be reached at
		 * @param address - address of the customer
		 * @param age - age of the customer
		 */
		public Customer(String name, String phoneNumber, String address, 
										int age){
			super(name,phoneNumber,address);
			this.age = age;
			
		}
		
		/**
		 * goes through all the sales objects in the arraylist bought and finds the total spent of the customer
		 * @return totalSpent - total spent by the customer 
		 */
		public double totalAmountSpent(){
			double totalSpent = 0;
			for(Sales sal: bought){
				double temp = sal.getAmount();
				 totalSpent+= temp;
			}
			return totalSpent;
		}
		/**
		 * determines the number of a product sold
		 * @param name- takes in the name of the customer
		 * @return amount- amount of a product sold
		 */
		public int totalBought(String name){
			int amount = 0;
			for(Sales sal: bought){
				double temp = sal.getAmountSold(name);
				 amount+= temp;
			}
			
			return amount;
		}
		/**
		 * adds the sales object to the array list 
		 * @param x - takes the sales object and adds it to the bought arraylist
		 */
		public void addPurchase(Sales x){
			bought.add(x);
		}
		/**
		 * customer to string
		 */
		public String toString(){
			return super.toString() + "\nAge: " + age ;
		}
}
