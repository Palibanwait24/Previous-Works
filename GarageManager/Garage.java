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
public class Garage {

	private Car[] cars;
	
	public Car[] getCars(){
		
		return cars;
		
	}
	
	public void setCars(Car auto, int index){
		
		cars[index]=auto;
		
	}
	
	public boolean isEmpty(int k){
		return cars[k] == null;
	}
	public void displayState(){
		String status;
		for (int x=0; x< cars.length; x++ )
		{
			System.out.print(x+"  ");
		}
		
		System.out.println();
		
		for (int x=0; x< cars.length; x++ )
		{
			if (cars== null){
				status = "E"; 
			}
			else{
				status = "C";
			}
		System.out.print(status+"  ");
		}
		
	}
	
	public int park(Car auto){
		int nonEmptyBay=0;
		int x =0;
		for(x=0; x<cars.length; x++){
			boolean empty=isEmpty(x);
			if (empty==false){
				nonEmptyBay++;
			} else {
				cars[x]=auto;//Park The Car Here
				break;
			}
		}
		if (nonEmptyBay == cars.length){
			return -1; //Garage Is Full
		}
		
	return x;// returns first available bay for parking
	}
	
	public double remove(int index){
		long currentMillis= System.currentTimeMillis();
		double elapsedTime;
		elapsedTime= currentMillis-cars[index].getTime();
		cars[index]=null;
		return elapsedTime;
		
	}
	
	public int findBayOfCar(int carNumber){
	
		int counter =-1;
		for(counter=-1; counter<carNumber; counter++){
			counter++;
			while (isEmpty(counter)){
				counter++;
				}
			}
		return counter;
	}
	
	public Garage(int capacity)
	{
		cars = new Car[capacity];
	}
	
}
