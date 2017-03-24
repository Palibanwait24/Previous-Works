/**
 * 
 */
package banwas01_Project3;

import javax.swing.JOptionPane;

/**
 * @author Amrit Pal Banwait
 *CS160-01 Fall 2014
 *project 3
 *
 */
public class Communication {
	
public String askForCode(){
	
	String inputCode=null;
	
	while ( true)
	{
		 inputCode=JOptionPane.showInputDialog (null, "We exchange the folloing currencies:"
				+ "\nCD (Canadian Dollar)\nMP (Mexican Peso)\nEU (Euro)\nEnter the currency code:", "Modest International",
				JOptionPane.QUESTION_MESSAGE);//asks for input of country code
		
		if(inputCode==null)
		{
			inputCode =JOptionPane.showInputDialog (null, "I did not recieve input code. Please try again.", "Modest International",
					JOptionPane.QUESTION_MESSAGE);// if not valid input it asks again
			continue;
		}
		else if(!inputCode.equals("CD") && (!inputCode.equals("MP")) 
			&& (!inputCode.equals("EU")))// validates input is CD, MP, or EU
	{
		inputCode =JOptionPane.showInputDialog (null, "I did not recieve input code. Please try again.", "Modest International",
				JOptionPane.QUESTION_MESSAGE);// if not valid input it asks again
		continue;
	}	
		else{
			break;
		}
	
	}
	return inputCode;
}
public boolean doYouBuy(){
	int buySell= JOptionPane.showConfirmDialog (null, "Please click 'YES' if you want to buy the specified amount.\n"+
			" Click 'NO' if you sell.", "Modest International",
			JOptionPane.YES_NO_OPTION);// yes or no JOptionPane box that asks if you want to sell or buy
	
	final int yes = JOptionPane.YES_OPTION;// yes variable is the yes option
	final int no= JOptionPane.NO_OPTION; // no variable is the no option
	boolean buy = false;// buy is initialized to buy
	
	
	if (buySell==yes)// if yes option is clicked the variable buy equals true
	{
	buy= true;
	}
	else if (buySell==no) // if not buy = false
	{
		buy=false;
	}
	
	return buy;// returns buy or sell
	
}
public double inputAmount(){
	double amount=0;// initialized to zero
	
	String x = JOptionPane.showInputDialog (null, "Please enter the amount of currency for your deal:", "Modest International",
			JOptionPane.QUESTION_MESSAGE);// solicits the amount for the deal
	
	if (x==null || x=="" )
	{
		System.out.println("No input, transaction aborted");
		amount=-1;
		
		
	}
	else 
	{
		amount = Double.parseDouble(x);// assigns x to amount after it is parsed
		
	}
	return amount;// returns amount
}
public boolean newTransaction(){
	
	int newTransaction= JOptionPane.showConfirmDialog (null, "Would you like to make a transaction?", "Modest Internatinal ",
			JOptionPane.YES_NO_OPTION);// yes or no JOptionPane box that asks if you want to sell or buy
	
	final int yes = JOptionPane.YES_OPTION;// yes option = yes
	final int no= JOptionPane.NO_OPTION; // no option = no
	
	boolean again = false;// again gets initialized to false
	
	if (newTransaction==yes)// if new transaction equals the yes variable then again = true
	{
	again= true;
	}
	else if (newTransaction==no) 
	{
		again=false; // if new transaction equals the no variable then again = false
	}
	
	return again;// returns again variable
}
}
