/**
 * 
 */
package banwas01_Project3;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
/**
 * @author Amrit Pal Banwait
 *CS160-01 Fall 2014
 *project 3
 *
 */
public class ProcessExchange {
	
	private final double commission = 5.5;// private data field holds 5.5 for commission rate
	private Currency cy;// private data field 
	private DecimalFormat df = new DecimalFormat("0.00");//A DecimalFormat reference variable, instantiated with “0.00”
	
public double exchange(double amount, boolean buy){
	double finalDollar=0;// double variable finalDollar initialized to 0
	
	double dollarValue=0;// double variable dollarValue initialized to 0
	
	double commision2 = dollarValue*(commission/100);// double commision2 is dollarValue times the decimal commission rate 
	
	if(buy==true)// if buy equals true then dollarValue is the amount inputed times the buying rate
	{
	dollarValue = amount * cy.getBuyingRate();
	}
	else if (buy==false) // if buy equals false then dollarValue is the amount inputed times the selling rate
	{
	dollarValue = amount * cy.getSellingRate();
	}
	
	
	if (buy==true)// if buy = true then the variable final dollar = DollarValue added the commision2 variable
	{
	 finalDollar = dollarValue +commision2;
	}
	else // else the variable final dollar = DollarValue subtracted the commision2 variable
	{
	 finalDollar = dollarValue-commision2;
	}
	
	return finalDollar;// returns the finalDollar
	
}	

public String composeReport(double money, boolean buy){
	
	String part1 = null;// string part1 initialized to null
	
	if (buy==true){// if buy equals true a currency code controlled 
					//switch construction part of the output
		
		switch(cy.getCode()){
		case "CD":
			part1 = "buys " + df.format(money) +" CD";
			break;
		case "MP":
			part1 = "buys " + df.format(money) +" MP";
			break;
		case "EU":
			part1 = "buys " + df.format(money)+" EU";
			break;
		default:
			part1=null;
		}				
	}
		
	else if (buy==false) {// if buy equals false a currency code controlled 
		//switch construction part of the output
		
		switch(cy.getCode()){
		
		case "CD":
			part1 = "sells " + df.format(money) +" CD";
			break;
		case "MP":
			part1 = "sells " + df.format(money) +" MP";
			break;
		case "EU":
			part1 = "sells " + df.format(money) +" EU";
			break;
		default:
			part1=null;
			
		}
	}
	
	 
	return part1;// returns part1
	
}
public void displayResult(double amount, boolean buy){
	
	String report = composeReport(amount, buy);// string report equals the return value from the method composeReport()
	double doll = exchange(amount,buy);// double doll equals the return value from the method exchange()
	
	if (report.equals(null))// validates that there is is input in report. if null then then prints message to console and exits program
	{
		System.out.println("No input, transaction aborted");
		System.exit(0);
	}
	else// if report is not null then message is printed to a JOptionPane window
	{
		JOptionPane.showMessageDialog (null, "Customer "+report+" in exchange of "+ df.format(doll) +
				" dollars \nWe appreciate your business!", "Payment",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
public  ProcessExchange(Currency currency, double amount, boolean buy){
	 cy = currency;// cy is initialized to currency
	displayResult(amount,buy);// calls the displayResult method
}
}