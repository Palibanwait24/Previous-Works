/**
 * 
 */
package banwas01_Project3;



/**
 * @author Amrit Pal Banwait
 *CS160-01 Fall 2014
 *project 3
 */
public class Currency {
	private double buyingRate;// data field to store buying rate
	private double sellingRate;// data field to store selling rate
	private String code;//data field to store the country code
	
public double getBuyingRate()
{
	
	return buyingRate;// returns the buying rate
	}
public double getSellingRate()
{
	return sellingRate;// returns the selling rate
	}
public String getCode(){
	return code;// returns the code
}
	
	
public Currency(double buyingRate, double sellingRate, String code)
{
	// constructor method
	this.buyingRate=buyingRate;
	this.sellingRate=sellingRate;
	this.code = code;
}

}
