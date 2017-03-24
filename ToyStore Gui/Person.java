package banwas01_Project4;

import java.io.Serializable;

/**
 * 
 * @author AmritPal
 *abstract super class that creates the person
 */
public abstract class Person implements Serializable {
	private String name;
	private String phoneNumber;
	private String address;
	private static int x = 10;
	private int id;
	/**
	 * takes the parameters and creates a person with a unique id number
	 * @param name - name of person
	 * @param phoneNumber - phone number of person
	 * @param address - address of person
	 */
	public Person(String name, String phoneNumber, String address){
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.address=address;
		this.id= x;
		x++;
	}
	/**
	 * getter method that allows access to the field
	 * @return name of person
	 */
	public String getName(){
		return name;
		}
	/**
	 * getter method that allows access to the field
	 * @return phoneNumber of person
	 */
	public String getPhoneNumber(){
		return phoneNumber;
		}
	/**
	 * getter method that allows access to the field
	 * @return address of person
	 */
	public String getAddress(){
		return address;
		}
	/**
	 * getter method that allows access to the field
	 * @return id number of  of person
	 */
	public int getID(){
		return id;
	}
	/**
	 * to string method if needed for later revision of project 
	 */
	public String toString(){
		return "Name: " + name + "\nPhone Number: " + phoneNumber +"\nAddress: " + phoneNumber
				+"\nId number: " + id;
		}
}
