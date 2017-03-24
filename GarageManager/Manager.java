/**
 * 
 */
package banwas01_Project4;

import java.util.Random;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

/**
 * @author Amrit Pal Banwait
 *CS160-01 Fall 2014
 *project 4
 *
 *
 */
public class Manager {

	private Garage garage;
	private double FEE_PER_HOUR=1.50;
	private double feeTotal;
	private int manyCars;
	
	public void parkACar(){
		
		int index= garage.park(new Car());
		
		if (!(index==-1)){
			JOptionPane.showMessageDialog (null, "A car is arriving at bay #"+ index+
					" Garage displayed on console.", "Message",JOptionPane.INFORMATION_MESSAGE);
			manyCars++;
			garage.displayState();
		}
		else if (index==-1){
			
			DecimalFormat formatter = new DecimalFormat("#,##0.00");
			
			JOptionPane.showMessageDialog (null, "The garage is full.\nThe parking process terminates", 
					"Parking Management",JOptionPane.WARNING_MESSAGE);
			System.out.println("Total parking fee collected is $"+ formatter.format(feeTotal));
			System.exit(0);
		}
	}
	public void chooseACarToLeave(){
		Random rd = new Random();
		if (manyCars==0){
			JOptionPane.showMessageDialog (null, "The garage is empty.\nThe parking process terminates", 
					"Parking Management",JOptionPane.WARNING_MESSAGE);
			System.out.println("After " + + " parking operations the process is terminated.");
			System.exit(0);
		}
		
		else {
			int number = rd.nextInt(manyCars);
			int index =garage.findBayOfCar(number);
			double time= garage.remove(index);
			
			DecimalFormat formatter = new DecimalFormat("#,##0.00");
			
			feeTotal= FEE_PER_HOUR * time /1000.00;
			
			JOptionPane.showMessageDialog (null, "The car from Bay#"+index+" is leaving the garage.\n"+
					"Parking fee paid: $"+formatter.format(feeTotal), 
					"Message",JOptionPane.INFORMATION_MESSAGE);
			
			garage.displayState();
			manyCars--;
		}
	}
	
	public void processParking(int limit){
		Random rd = new Random();
		
		JOptionPane.showMessageDialog (null, "Welcom to the Parking simulation! \nSee the initial state of the garage on the console.", 
				"Parking Management",JOptionPane.WARNING_MESSAGE);
		
		garage.displayState();
		
		for(int k=0; k<limit; k++){
			int x = rd.nextInt(2);
			if (x==1){
				parkACar();
			}
			else if (x==0){
				chooseACarToLeave();
			}
		}
		System.out.println("Total parking fee collected is $"+feeTotal);
		System.out.println("After " +  + " parking operations the process is terminated.");
		
	}
	public Manager(Garage gar, int many){
		garage=gar;
		manyCars=many;
	}
}