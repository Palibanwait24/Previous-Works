/**
 * 
 */
package banwas01_Project3;

import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * @author Amrit Pal Banwait
 *CS160-01 Fall 2014
 *project 3m
 *
 *
 */
public class Applications {
	
	//there is file reading in the main

public static void main(String[] args)throws IOException{
			
		//Reference variables to call methods
			
		Currency currency =  null;
		Communication comm = new Communication();
		
		//display the Welcoming message, see Figure 1
		
		JOptionPane.showMessageDialog(null,"Welcome to the Modest International Currency Exchange Services!","Modest International",
				JOptionPane.INFORMATION_MESSAGE);
			
		//run this while loop until new transaction needed
		//call the newTransaction( ) method with the comm prefix
		
		
		while(comm.newTransaction()){
			
			// call the askForCode( ) method of the Communication class and save the return value in the variable
			
			String codeInput = comm.askForCode();
			
			Scanner inFile= new Scanner(new File("dailyrates.txt"));
			
			while(inFile.hasNext()){
			
				String data = inFile.nextLine();
			   	
				Scanner tokens = new Scanner(data);
			   	
				if(tokens.next().equals(codeInput)){
					
					//instantiate currency here, use codeInput as parameter as well as the next two readings buy tokens, both doubles
			   		
					currency = new Currency(tokens.nextDouble(), tokens.nextDouble(),codeInput);
			   		break;
			    }
			   	else
			   		continue;
			}// end while
			
			//call the inputAmount( ) method of the Communication class to obtain the currency amount
			
			double amount = comm.inputAmount();
			// do you buy? call the doYouBuy method of the Communication class
			
			boolean buy = comm.doYouBuy();
			
			
			
			
			//start the process
			
			new ProcessExchange(currency,amount, buy);
			
		}//end while
		
		System.out.println("Exchange is closed for the day.");
		System.exit(0);
}//end main
	}//end class
