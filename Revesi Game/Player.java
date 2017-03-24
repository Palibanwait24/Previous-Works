
import java.awt.Color;
import java.awt.geom.Rectangle2D;
/**
 * 
 * @author AmritPal
 *class that creates a player with a color 
 */
public class Player {
	
	private Color color;// color variable
	/**
	 * constructor that creates a player
	 * @param color - takes a color to make a color
	 */
	public Player(Color color){
		
		this.color = color;
	}
	/**
	 * gets the color for the player
	 * @return color - get the color for the player
	 */
	public Color getColor(){
		return color;
	}
	
}
