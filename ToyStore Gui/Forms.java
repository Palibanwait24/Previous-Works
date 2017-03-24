package banwas01_Project4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * 
 * @author AmritPal
 *main class that creates the frame
 */
public class Forms {
	/**
	 * creates the main frame and makes it visible
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		MyFrame f = new MyFrame(); // creates frame
		f.setVisible(true);//make the frame visible
	}

}
/**
 * loginclass that holds all the dialogbox info
 *
 */
class LoginDialog extends JDialog {
	/**
	 * constructor for the login dialog box. only allows access to 
	 * the program if the login is correct
	 * @param owner - ties it to the main frame 
	 * @param emp - takes in the arraylist of all employee so 
	 * 								the passwords and id can be retrived
	 */
	public LoginDialog(JFrame owner, ArrayList<Employee> emp) {
		super(owner, "Authentication", true);     // super reference
		setResizable(false);  // sets resiable false
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 2)); // sets to gridlayout
		JLabel label1 = new JLabel("login: ");
		centerPanel.add(label1); // adds login label
		final JTextField loginField = new JTextField("", 10);
		centerPanel.add(loginField); // adds login field
		JLabel label2 = new JLabel("passowrd: ");
		centerPanel.add(label2); // adds password label
		final JPasswordField passwordField = new JPasswordField("", 10);
		centerPanel.add(passwordField); // adds password field
		add(centerPanel, BorderLayout.CENTER);// adds panel to the center
		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH); // adds panel to south
		JButton okButton = new JButton("OK");
		southPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] correctPassword=null;
				for(Employee el: emp){
					String pass = el.getPassword();
					 correctPassword = new char[pass.length()];
					 for (int i = 0; i < pass.length() ; i++) {    // seperates string in to characters
					      correctPassword[i] = new Character(pass.charAt(i));
					     
					   }
					
					if (loginField.getText().trim().equals(el.getID()+"")) {  // checks if it is a valid password
						char[] password = passwordField.getPassword();
						if (Arrays.equals(password, correctPassword)) {
							LoginDialog.this.dispose();
						}
					}
				}
				
				
				Arrays.fill(correctPassword, '0');
			}
		});
	}
}

/**
 * main frame class
 *
 */
class MyFrame extends JFrame {
	/**
	 * constructor for the frame
	 */
	
	ArrayList<Person> people = new ArrayList<Person>(); // creates array list of people
	ArrayList<Employee> emp = new ArrayList<Employee>();// creates array list of employee
	ArrayList<Customer> cus = new ArrayList<Customer>();  // creates array list of customers
	Inventory store = new Inventory(); // creates the inventory
	
	public MyFrame() {
		
		Employee admin = new Employee("Admin", "Admin", "Admin", 0.0, "12345"); // creates admin employee
		emp.add(admin);  //adds to emp and people arraylist
		people.add(admin);
		try{
			FileInputStream fileIn = new FileInputStream("bank.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			people = (ArrayList<Person>) in.readObject();
			emp = (ArrayList<Employee>) in.readObject();
			cus = (ArrayList<Customer>) in.readObject();
			store = (Inventory) in.readObject();
			
			in.close();
			fileIn.close();
			} catch(Exception exception){
				
			}
		LoginDialog dialog = new LoginDialog(this,emp); // calls the login dialog
		dialog.setSize(200, 100);
		dialog.setVisible(true);
		
		setSize(600, 600);
		JMenuBar menuBar = new JMenuBar();// menu bar
		setJMenuBar(menuBar);

		JMenu options = new JMenu("Options");
		menuBar.add(options);
		

		
		
		
		
		// new employee
		JMenuItem newEmployee = new JMenuItem("New Employee");
		options.add(newEmployee);
		newEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDialog dialog = new EmployeeDialog(MyFrame.this,people, emp);
				dialog.setSize(350, 200);
				dialog.setVisible(true);
			}
		});

		// new customer
		JMenuItem newCustomer = new JMenuItem("New Customer");
		options.add(newCustomer);

		newCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerDialog dialog = new CustomerDialog(MyFrame.this,
						people, cus);
				dialog.setSize(350, 200);
				dialog.setVisible(true);
			}
		});

		// new product
		JMenuItem newProduct = new JMenuItem("New Product");
		options.add(newProduct);

		newProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDialog dialog = new ProductDialog(MyFrame.this, store);
				dialog.setSize(350, 200);
				dialog.setVisible(true);
			}
		});
		// new sale
		JMenuItem newSale = new JMenuItem("New Sale");
		options.add(newSale);

		newSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewSale dialog = new NewSale(MyFrame.this, people, store, cus,
						emp);
				dialog.setSize(300, 300);
				dialog.setVisible(true);
			}
		});
		// new return
		JMenuItem newReturn = new JMenuItem("New Return ");
		options.add(newReturn);

		newReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewReturn dialog = new NewReturn(MyFrame.this, people, store,
						cus, emp);
				dialog.setSize(300, 300);
				dialog.setVisible(true);
			}
		});
		
		//prints inventory
		JMenuItem printInventory = new JMenuItem("Show Inventory ");
		options.add(printInventory);

		printInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintInventory dialog = new PrintInventory(MyFrame.this, store);
				dialog.setSize(300, 300);
				dialog.setVisible(true);
			}
		});
		
		// saves items
		JMenuItem save = new JMenuItem("Save ");
		options.add(save);

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				FileOutputStream fileOut = new FileOutputStream("bank.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(people); // saves the people arraylist
				out.writeObject(emp); // saves the employee arraylist
				out.writeObject(cus); // saves the customer arraylist
				out.writeObject(store); // saves the inventory
				out.close();  // closes the file
				fileOut.close();
				JOptionPane.showMessageDialog (null,"File has been Saved" , "Message",JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception exception){
					
				}

			}
		});
		

	}
}



/**
 * 
 *customer dialog box that handels createing a new customer
 */
class CustomerDialog extends JDialog {
	/**
	 * takesin the array of people and the cus arraylists and uses input to create a customer
	 * @param owner -- frame owner
	 * @param people -- people arraylist
	 * @param cus -- customer array list
	 */
	public CustomerDialog(JFrame owner, ArrayList<Person> people,
			ArrayList<Customer> cus) {
		super(owner, "New Customer", true);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();

		JLabel nameLable = new JLabel("Customer Name: ");// creates label,  text field and adds panel
		final JTextField nameField = new JTextField(20);
		p1.add(nameLable);
		p1.add(nameField);

		JLabel phoneLable = new JLabel("Phone Number: ");// creates label,  text field and adds panel
		final JTextField phoneField = new JTextField(20);
		p2.add(phoneLable);
		p2.add(phoneField);

		JLabel addressLable = new JLabel("Address: ");// creates label,  text field and adds panel
		final JTextField addressField = new JTextField(20);
		p3.add(addressLable);
		p3.add(addressField);

		JLabel ageLable = new JLabel("Age: ");// creates label,  text field and adds panel
		final JTextField ageField = new JTextField(20);
		p4.add(ageLable);
		p4.add(ageField);

		JButton okButton = new JButton("Ok");
		p5.add(okButton);
		okButton.addActionListener(new ActionListener() { // ok button
			public void actionPerformed(ActionEvent e) {

				if (!nameField.getText().equals("") // if all fields are filled then continue
						&& !phoneField.getText().equals("")
						&& !addressField.getText().equals("")
						&& !ageField.getText().equals("")) {

					String name;
					String phone;
					String address;
					int age;
					
					name = nameField.getText();// gets field info
					phone = phoneField.getText();// gets field info
					address = addressField.getText();// gets field info
					String age1 = ageField.getText();// gets field info
					age = Integer.parseInt(age1);// converts field info
					Customer customer = new Customer(name, phone, address, age); // makes new customer
					people.add(customer); // adds customer to people array
					cus.add(customer); // adds customer to customer array
					JOptionPane.showMessageDialog (null,customer.toString() , "Message",JOptionPane.INFORMATION_MESSAGE); 
					dispose();// disposes screen

					return;
				}

			}

		});

		setLayout(new GridLayout(5, 1, 2, 2));// adds panels and packs it
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		pack();
	}
}
/**
 * 
 * dialog box that creates new employee
 */
class EmployeeDialog extends JDialog {
	/**
	 * employe Dialog constuctor that  adds employee to arrayslists and creates the employe with input
	 * @param owner -- owner frame
	 * @param people -- people array
	 * @param emp -- employee array
	 */
	public EmployeeDialog(JFrame owner, ArrayList<Person> people,
			ArrayList<Employee> emp) {
		super(owner, "New Employee", true);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();

		JLabel nameLable = new JLabel("Employee Name: ");// creates label,  text field and adds panel
		final JTextField nameField = new JTextField(20);
		p1.add(nameLable);
		p1.add(nameField);

		JLabel phoneLable = new JLabel("Phone Number: ");// creates label,  text field and adds panel
		final JTextField phoneField = new JTextField(20);
		p2.add(phoneLable);
		p2.add(phoneField);

		JLabel addressLable = new JLabel("Address: ");// creates label,  text field and adds panel
		final JTextField addressField = new JTextField(20);
		p3.add(addressLable);
		p3.add(addressField);

		JLabel salaryLable = new JLabel("Salary: ");// creates label,  text field and adds panel
		final JTextField salaryField = new JTextField(20);
		p4.add(salaryLable);
		p4.add(salaryField);

		JLabel passLable = new JLabel("Password: ");// creates label,  text field and adds panel
		final JTextField passField = new JTextField(20);
		p5.add(passLable);
		p5.add(passField);

		JButton okButton = new JButton("Ok");
		p6.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!nameField.getText().equals("")// checks to see that all fields are filled
						&& !phoneField.getText().equals("")
						&& !addressField.getText().equals("")
						&& !salaryField.getText().equals("")
						&& !passField.getText().equals("")) {

					String name;
					String phone;
					String address;
					double salary;
					String pass;

					name = nameField.getText();// gets the input from field 
					phone = phoneField.getText();// gets the input from field 
					address = addressField.getText();// gets the input from field 
					String salary1 = salaryField.getText();// gets the input from field 
					salary = Double.parseDouble(salary1);// converts the input from field 
					pass = passField.getText();// gets the input from field 
					Employee emplo = new Employee(name, phone, address, salary,
							pass); // creates new employee
					people.add(emplo);  // adds employee to people
					emp.add(emplo); // adds employee to employee arraylist
					JOptionPane.showMessageDialog (null,emplo.toString() , "Message",JOptionPane.INFORMATION_MESSAGE);
					dispose(); // closes the box
					return;
				}

			}

		});

		setLayout(new GridLayout(6, 1, 0, 0));
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		pack();
	}
}
/**
 * product dialogbox used to create a new product
 *
 */
class ProductDialog extends JDialog {
	/**
	 * takes in parameters and creates fields for input and adds item to inventory
	 * @param owner
	 * @param store
	 */
	public ProductDialog(JFrame owner, Inventory store) {
		super(owner, "New Product", true);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();

		JLabel nameLable = new JLabel("Product Name: ");// creates label,  text field and adds panel
		final JTextField nameField = new JTextField(20);
		p1.add(nameLable);
		p1.add(nameField);

		JLabel discriptionLable = new JLabel("Discription: ");// creates label,  text field and adds panel
		final JTextField discriptionField = new JTextField(20);
		p2.add(discriptionLable);
		p2.add(discriptionField);

		JLabel priceLable = new JLabel("Price: ");// creates label,  text field and adds panel
		final JTextField priceField = new JTextField(20);
		p3.add(priceLable);
		p3.add(priceField);

		JLabel quantityLable = new JLabel("quantity: ");// creates label,  text field and adds panel
		final JTextField quantityField = new JTextField(20);
		p4.add(quantityLable);
		p4.add(quantityField);

		JButton okButton = new JButton("Ok");
		p5.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!nameField.getText().equals("")// checks to see that all fields are filled
						&& !discriptionField.getText().equals("")
						&& !priceField.getText().equals("")
						&& !quantityField.getText().equals("")) {

					String name;
					String discription;
					double price;
					int quantity;
					name = nameField.getText();// gets field info
					discription = discriptionField.getText();// gets field info

					String price1 = priceField.getText();// gets field info
					price = Double.parseDouble(price1);// converts field info
					String quantity1 = quantityField.getText();// gets field info
					quantity = Integer.parseInt(quantity1);// converts field info

					Product s = new Product(name, discription, price); // creates new product
					LineItem x = new LineItem(s, quantity);// creates new line item
					store.addToInventory(x); // adds line item to the inventory
					
					dispose();
					return;
				}

			}

		});

		setLayout(new GridLayout(5, 1, 2, 2));
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		pack();
	}
}
/**
 * handels new sale for when a customer buys an item for the store
 *
 */
class NewSale extends JDialog {
	/**
	 * handles the sale and changes the quantity 
	 * @param owner -- main frame
	 * @param people -- takes in all the people 
	 * @param store -- takes in all of the inventory to see if a product exists and to update inventory
	 * @param cus -- takes in all of the customers for the combo boxes
	 * @param emp -- takes in all of the employeeies for the combo boxes
	 */
	double due=0;
	public NewSale(JFrame owner, ArrayList<Person> people, Inventory store,
			ArrayList<Customer> cus, ArrayList<Employee> emp) {
		super(owner, "New Sale", true);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();

		int[] em = new int[emp.size()];  // gets employee id values
		for (int x = 0; x < emp.size(); x++) {
			em[x] = emp.get(x).getID();
		}
		int[] cu = new int[cus.size()];  // gets customer id values
		for (int x = 0; x < cus.size(); x++) {
			cu[x] = cus.get(x).getID();
		}

		JLabel employee1 = new JLabel("Employee ID: ");// adds id values to combo box and adds to panel
		final JComboBox employeeBox = new JComboBox();
		for (int x = 0; x < em.length; x++) {
			employeeBox.addItem(em[x]);
		}
		p1.add(employee1);
		p1.add(employeeBox);

		JLabel customer1 = new JLabel("Customer ID: ");// adds id values to combo box and adds to panel
		final JComboBox customerBox = new JComboBox();
		
		for (int x = 0; x < cu.length; x++) {
			customerBox.addItem(cu[x]);
		}
		p2.add(customer1);
		p2.add(customerBox);

		JLabel nameLable = new JLabel("Product Name: ");// creates label,  text field and adds panel
		final JTextField nameField = new JTextField(20);
		p3.add(nameLable);
		p3.add(nameField);

		JLabel quantityLable = new JLabel("quantity: ");// creates label,  text field and adds panel
		final JTextField quantityField = new JTextField(20);
		p4.add(quantityLable);
		p4.add(quantityField);
		
		JButton okButton = new JButton("Ok");// ok button and action listener
		p5.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int e1;
				int c1;
				e1 = ((Integer) employeeBox.getSelectedItem());// converts combo choice to int
				c1 = ((Integer) customerBox.getSelectedItem());// converts combo choice to int

				Sales so = new Sales(c1, e1); // creates new sales object
				boolean productExist = store.findproduct(nameField.getText()); // finds if product exists
				int amount1 = 0;
				if (!nameField.getText().equals("")) {// checks to see that all fields are filled

					if (productExist == false) { // if product doesn't exist sets the fields to ""
						nameField.setText("");
						quantityField.setText("");
					}
					if (productExist == true) {

						amount1 = store.findQuanity(nameField.getText());// finds the quantity
						if (amount1 <= 0) {

							quantityField.setText(""); // sets field to "" if invalid quantity
						}

					}
				}

				if (!nameField.getText().equals("")// checks to see that all fields are filled
						&& !quantityField.getText().equals("")) {

					String name;
					int quantity;

					name = nameField.getText();// gets the input from field 

					String quantity1 = quantityField.getText();// gets the input from field 
					quantity = Integer.parseInt(quantity1);//converts the input from field 
					if (quantity <= amount1) {

						so.addToSale(store.getProduct(name), quantity); // adds to sales
						store.changeQuanity(name, quantity);  // changes quantiy
						due = due + (quantity*store.getProduct(name).getPrice());
						for (Person el : people) {  // adds transaction  sales
							if (e1 == el.getID()) {
								if (el.getClass().equals(Employee.class)) {
									Employee em = (Employee) el;
									em.addSale(so);
								}
							}
						}
						for (Person el : people) {
							if (c1 == el.getID()) {
								if (el.getClass().equals(Customer.class)) {
									Customer cu = (Customer) el;
									cu.addPurchase(so);// adds transaction  sales
								}
							}
						}
						JOptionPane.showMessageDialog (null,"\nTransaction was succesful! "
								+ "\nThe customer owes: $" + due, "Message",JOptionPane.INFORMATION_MESSAGE);
						dispose();// closes frame
					}

				}

			}

		});
		JButton addproduct = new JButton("Buy another product"); 
		p5.add(addproduct);
		addproduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // does same as ok button but does not dispose window
															// sets the boxes to ""
				int e1;
				int c1;
				e1 = ((Integer) employeeBox.getSelectedItem());
				c1 = ((Integer) customerBox.getSelectedItem());

				Sales so = new Sales(c1, e1);
				boolean productExist = store.findproduct(nameField.getText());
				int amount = 0;
				if (!nameField.getText().equals("")) {

					if (productExist == false) {
						nameField.setText("");
						quantityField.setText("");
					}
					if (productExist == true) {

						amount = store.findQuanity(nameField.getText());
						if (amount <= 0) {

							quantityField.setText("");
						}

					}
				}
				if (!nameField.getText().equals("")// checks to see that all fields are filled
						&& !quantityField.getText().equals("")) {

					String name;
					int quantity;

					name = nameField.getText();// gets field info

					String quantity1 = quantityField.getText();// gets field info
					quantity = Integer.parseInt(quantity1);// convertsfield info
					if (quantity <= amount) {
						so.addToSale(store.getProduct(name), quantity); // creates new sale
						store.changeQuanity(name, quantity);// changes the quanity
						due = due + (quantity*store.getProduct(name).getPrice());
						nameField.setText("");// gets field info
						quantityField.setText("");// gets field info
						
					}

				}

			}

		});

		setLayout(new GridLayout(5, 1, 2, 2));
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		pack();
	}
}
/**
 * new return class that handles the return of a product
 *
 */
class NewReturn extends JDialog {
	/**
	 * Constructor that handles everything
	 * @param owner - takes in the main frame
	 * @param people - takes in all the people 
	 * @param store - takes in all the inventory so items can be added back 
	 * @param cus - takes in all of the employees so they can be added to combo box
	 * @param emp- takes in all of the customers so they can be added to combo box
	 */
	public NewReturn(JFrame owner, ArrayList<Person> people, Inventory store,
			ArrayList<Customer> cus, ArrayList<Employee> emp) {
		super(owner, "New Return", true);
		JPanel p1 = new JPanel();// creates panels
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();

		int[] em = new int[emp.size()];  // creates int array so that the
											//id numbers can be added to combo boxes
		for (int x = 0; x < emp.size(); x++) {
			em[x] = emp.get(x).getID();
		}
		int[] cu = new int[cus.size()];// creates int array so that the
										//id numbers can be added to combo boxes
		for (int x = 0; x < cus.size(); x++) {
			cu[x] = cus.get(x).getID();
		}

		JLabel employee1 = new JLabel("Employee ID: ");// creates label and adds to combo boxes and adds panel
		final JComboBox employeeBox = new JComboBox();
		for (int x = 0; x < em.length; x++) {
			employeeBox.addItem(em[x]);
		}
		p1.add(employee1);
		p1.add(employeeBox);

		JLabel customer1 = new JLabel("Customer ID: ");// creates label and adds to combo boxes and adds panel
		final JComboBox customerBox = new JComboBox();
		for (int x = 0; x < em.length; x++) {
			customerBox.addItem(cu[x]);
		}
		p2.add(customer1);
		p2.add(customerBox);

		JLabel nameLable = new JLabel("Product Name: ");// creates label,  text field and adds panel
		final JTextField nameField = new JTextField(20);
		p3.add(nameLable);
		p3.add(nameField);

		JLabel quantityLable = new JLabel("quantity: ");// creates label,  text field and adds panel
		final JTextField quantityField = new JTextField(20);
		p4.add(quantityLable);
		p4.add(quantityField);

		JButton okButton = new JButton("Ok"); // creats button, adds it, and adds action listiener
		p5.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int e1;
				int c1;
				e1 = ((Integer) employeeBox.getSelectedItem()); // converts combo choice to int
				c1 = ((Integer) customerBox.getSelectedItem());// converts combo choice to int

				Return rf = new Return(c1, e1); // creates return object with the employee and customer ids

				if (!nameField.getText().equals("")) { // check if the product exists
					boolean productExist = store.findproduct(nameField
							.getText());
					if (productExist == false) {
						nameField.setText("");
					}
				}

				if (!nameField.getText().equals("") // check if the boxes are filled
						&& !quantityField.getText().equals("")) {

					String name;
					int quantity;

					name = nameField.getText(); //gets field input

					String quantity1 = quantityField.getText();//gets field input
					quantity = Integer.parseInt(quantity1);//converts field input

					Product pro = store.getProduct(name);// creates product
					rf.addToReturn(pro, quantity);

					quantity = 0 - quantity;// changes quantity to negative

					store.changeQuanity(name, quantity); // changes the quantity
					JOptionPane.showMessageDialog (null,"\nReturn was succesful! "
							+ "\nThe customer is owed: $" + pro.getPrice()*quantity, "Message",JOptionPane.INFORMATION_MESSAGE);
					dispose();// closes the dialog box
					return;
				}

			}

		});
		// sets the layout and adds the panels
		setLayout(new GridLayout(5, 1, 2, 2));
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		pack();
	}
}
/**
 * 
 *Print inventory dialog box
 */
class PrintInventory extends JDialog {
	/**
	 * constructor
	 * @param owner- main frame
	 * @param store - takes in the inventory
	 */
	public PrintInventory(JFrame owner, Inventory store) {
		super(owner, "Print Inventory ", true);
		
		JPanel p1 = new JPanel();// creates 2 panels
		JPanel p2 = new JPanel();
		int count = 0;
		for (LineItem in : store.getInventory()) {// finds out how many lineItems in inventory
			count++;
		}
		p1.setLayout(new GridLayout(count, 1, 2, 2)); // sets the grid layout 
		for (LineItem in : store.getInventory()) {  // adds the inventory to the panel
			JLabel label = new JLabel(in.toString());
			p1.add(label);
		}

		JButton okButton = new JButton("Ok"); // closes the frame when pressed
		p2.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		pack();

	}

}
