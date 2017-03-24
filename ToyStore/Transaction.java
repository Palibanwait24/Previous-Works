package banwas01_Project2;

import java.util.Date;


/**
 * 
 * @author AmritPal
 * super class that is abstract
 */
abstract class Transaction {
	private Date date;
	private static int idValue = 30;
	private int transactionIDNumber;
	
	/**
	 * constructor of the super class 
	 */
	public Transaction(){
		transactionIDNumber = idValue;
		date =  new Date();
		idValue++;
	}
	/**
	 * getter method that gives access to private field
	 * @return transactionIDnumber - id of a transaction
	 */
	public int getTransactionID(){
		return this.transactionIDNumber;
	}
	/**
	 * if a transaction was succesful returns the message 
	 */
	 public String toString() {
	        return "Transaction was succesful! ";
	    }
	
}
