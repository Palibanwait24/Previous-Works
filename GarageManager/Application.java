/**
 * 
 */
package banwas01_Project4;

/**
 * @author Amrit Pal Banwait
 *CS160-01 Fall 2014
 *project 4
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int capacity=15;
		int limit=15;
		int counter=0;
		
		Garage garage = new Garage(capacity);
		
		for(int k=0;k<limit;k++){
			boolean x=Math.random()<0.5;
			if (x=true){
				
				counter++;
			}
		}
		Manager manager= new Manager(garage,counter);
		manager.processParking(limit);

	}

}
