package banwas01_Project2;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 
 * @author AmritPal
 *class to create a new employee extends person class
 */
public class Employee extends Person {
	private DecimalFormat format = new DecimalFormat("$###,###.00");
	private double salary;
	private ArrayList<Sales> sold = new ArrayList<>();
	
	/**
	 * takes a series of parameters and creates the employee
	 * @param name - name of employee
	 * @param phoneNumber - number the employee can be reached at
	 * @param address - address the employee can be reached at
	 * @param salary - how much the employee makes a year
	 */
	public Employee(String name, String phoneNumber, String address, 
									double salary){
		super(name,phoneNumber,address);
		this.salary = salary;
		
	}
	/**
	 * finds the total number of items an employee has sold
	 * @return totalSpent- total products an employee has sold 
	 */
	public int itemsSold(){
		int total = 0;
		for(Sales sal: sold){
			double temp = sal.getItemSold();
			 total+= temp;
		}
		return total;
	}
	/**
	 * to string method if need for future revision of program
	 */
	public String toString(){
		return super.toString() + ", gets paid " + format.format(salary) + ", and has sold: " + sold ;
	}
	/**
	 * addes the sales object to the employees arrray list
	 * @param x- takes in the sales object x and adds it to the employee's arraylist of sales sold
	 */
	public void addSale(Sales x){
		sold.add(x);
	}
}
