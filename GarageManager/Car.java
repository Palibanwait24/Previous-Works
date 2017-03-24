/**
 * 
 */
package banwas01_Project4;

/**
 * @author Amrit Pal Banwait
 *CS160-01 Fall 2014
 *project 4
 *
 *
 */
public class Car {
	
	private long timeIn;
	
	public long getTime(){
		return timeIn;
		
	}
	
	public Car(){
	 timeIn= System.currentTimeMillis();
		
	}

}
