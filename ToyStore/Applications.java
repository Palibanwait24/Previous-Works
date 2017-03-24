/**
 * 
 */
package banwas01_Project2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * @author AmritPal
 *
 *Main class
 */
public class Applications {
	private DecimalFormat format = new DecimalFormat("$###,###.00");
	/**Main method that runs the entire program. this goes through a do while loop and prints the menu for
	 * the user to run the program and input data and choose an option.
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Person> people = new ArrayList<Person>();
		
		Scanner keyboard = new Scanner(System.in);// scanner for string
		Scanner kbDouble = new Scanner(System.in); // scanner for double
		Scanner kbInt = new Scanner(System.in);// scanner for int
		int option =0;
		Inventory store = new Inventory();
		do{
		printMenu();
		int quan = 0;
		System.out.println("Please choose an option.");
		option = kbInt.nextInt();
		switch (option) {
		case 1:// new employee
			System.out.println("Creating a new employee!");
			System.out.println("------------------------");
			
			
			System.out.println("Please enter the new employee's name.");
			String name = keyboard.nextLine();
			
			System.out.println("Please enter the new employee's phone number.");
			String phone = keyboard.nextLine();
			
			System.out.println("Please enter the new employee's address.");
			String address = keyboard.nextLine();
			
			System.out.println("Please enter the new employee's Salary.");
			double salary = kbDouble.nextDouble();
			Employee emplo = new Employee(name,phone,address,salary);
			people.add(emplo );
			System.out.println("The ID number for this person is: " + emplo.getID());
			break;
		case 2:// new customer
			System.out.println("Creating a new customer!");
			System.out.println("------------------------");
			System.out.println("Please enter the new customer's name.");
			name = keyboard.nextLine();
			System.out.println("Please enter the new customer's phone number.");
			phone = keyboard.nextLine();
			System.out.println("Please enter the new Cusomer's address.");
			address = keyboard.nextLine();
			
			System.out.println("Please enter the new employee's age.");
			int age = kbInt.nextInt();
	
			Customer customer = new Customer(name,phone,address,age);
			people.add(customer);
			System.out.println("The ID number for this person is: " + customer.getID());
			break;
		case 3:// add product to inventory
			System.out.println("Name of Product:");
			String productName = keyboard.nextLine();
			
			System.out.println("Description of Product:");
			String description = keyboard.nextLine();
			
			System.out.println("Price:");
			double price = kbDouble.nextDouble();
			
			System.out.println("Quantity:");
			int quantity = kbInt.nextInt();
			Product s = new Product(productName, description, price);
			LineItem x = new LineItem(s,quantity);
			//Inventory store = new Inventory();
			store.addToInventory(x);
			LineItem c = new LineItem(s,quantity);
			System.out.println(c.toString());
			break;
		case 4:// New sale
			System.out.println("Please enter the employee ID involved in the sale");
			int eid = kbInt.nextInt();
			System.out.println("Please enter the customer ID involved in the sale");
			int cid = kbInt.nextInt();
			String findName;
			
			String answer="yes";
			String choice;
			Sales so = new Sales(cid,eid);
			do {
			System.out.println("Enter the name of the product");
			findName = keyboard.nextLine();
			findName = findName.toLowerCase();
			boolean productExist = store.findproduct(findName);// does product exist in inventory
			if (productExist==false){
				System.out.println("That Product is not in the store inventory");
			}
			else{
				System.out.println(store.findQuanity(findName));
				System.out.println("How much of the product does the customer want");
				quan = kbInt.nextInt();
				store.changeQuanity(findName, quan);
				so.addToSale(store.getProduct(findName), quan);
			    System.out.println("Would you like to buy another product? If yes, enter yes. else enter no");
				choice= keyboard.nextLine();
				store.changeQuanity(findName, quan);
				if (!choice.equals("yes")){
					answer="no";
				}
			}
				}while(answer.equals("yes"));
			
				for(Person el: people){
					if (eid == el.getID()){
						if(el.getClass().equals(Employee.class)){
						Employee em = (Employee) el ;
						em.addSale(so);
						}
					}
				}
				for(Person el: people){
					if (cid == el.getID()){
						if(el.getClass().equals(Customer.class)){
						Customer cu = (Customer) el ;
						cu.addPurchase(so);
						}
					}
				}
				

				
			break;
		case 5:// Return item
			System.out.println("Please enter the employee ID involved in the refund");
			eid = kbInt.nextInt();
			System.out.println("Please enter the customer ID involved in the refund");
			cid = kbInt.nextInt();
			answer="yes";
			Return rf = new Return(cid,eid);
			double amountOwed = 0;
			do {
			System.out.println("Enter the name of the product");
			findName = keyboard.nextLine();
			findName.toLowerCase();
			boolean productExist = store.findproduct(findName);// does product exist in inventory
			if (productExist==false){
				System.out.println("That Product is not in the store inventory");
			}
			else{
				System.out.println(store.findQuanity(findName));
				System.out.println("How much of the product does the customer want to return");
				quan = kbInt.nextInt();
				
				Product pro = store.getProduct(findName);
				rf.addToReturn(pro, quan);
				amountOwed += quan * pro.getPrice() ; 
				quan = 0-quan;
				rf.toString();
			    System.out.println("Would you like to return another product? If yes, enter yes. else enter no");
				System.out.println("the customer is owed: " + amountOwed);
				choice= keyboard.nextLine();
				store.changeQuanity(findName, quan);
				if (!choice.equals("yes")){
					answer="no";
				}
			}
				}while(answer.equals("yes"));
			
			break;
		case 6:// prints the total amount spent by a customer
			System.out.println("Enter Customer ID");
			int spentID = kbInt.nextInt();
			for(Person el: people){
				if (spentID == el.getID()){
					if(el.getClass().equals(Customer.class)){
					Customer cu = (Customer) el ;
					System.out.println("Customer " + spentID + " has spent " 
							+ cu.totalAmountSpent() + " at the store.");
					}
				}
			}
			break;
		case 7:// total number of sales for a product
			System.out.println("Enter the name of the product");
		    findName = keyboard.nextLine();
		    findName.toLowerCase();
			boolean productExist = store.findproduct(findName);// does product exist in inventory
			if (productExist==false){
				System.out.println("That Product is not in the store inventory");
			}
			else{
				int bought =0;
				for(Person el: people){
					if(el.getClass().equals(Customer.class)){
						Customer cu = (Customer) el ;
						bought = cu.totalBought(findName);
						System.out.println("this item was bought: " + bought + " times");
				}
			}
		}
			break;
		case 8:// how many items a employee has sold
			System.out.println("Enter Employee ID");
			int employID = kbInt.nextInt();
			for(Person el: people){
				if (employID == el.getID()){
					if(el.getClass().equals(Employee.class)){
					Employee em = (Employee) el ;
					System.out.println("Employee " + employID + " has sold " 
							+ em.itemsSold() + " at the store.");
					}
				}
			}
			
			break;
		case 9:
			for(LineItem in: store.getInventory()){
				System.out.println(in.toString());
			}
		}
		
			
	} while (option != 10);
		
		
		
		System.out.println("Program Terminated!");
		System.exit(0);
}
	/**
	 * this method prints the menu for the program.
	 */
	public static void printMenu() {
		System.out.println("1. New Employee");
		System.out.println("2. New Customer");
		System.out.println("3. Add a Product");
		System.out.println("4. New Sale");
		System.out.println("5. Return an Item");
		System.out.println("6. Amount spent by customer");
		System.out.println("7. Quantity of a product sold");
		System.out.println("8. Number of items sold by Employee");
		System.out.println("9. get inventory");
		System.out.println("10. Exit Program");
	}
	


}
