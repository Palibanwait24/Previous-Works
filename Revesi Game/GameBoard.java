

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
/**
 * 
 * @author AmritPal
 *
 */
class GameBoard{
	private Rectangle2D[][] grid = new Rectangle2D[8][8];
	/**
	 * game board constructor and creates the grid of rectangles 
	 */
	public GameBoard(){
		int rCord=0;
		int cCord=0;
		for (int r = 0; r< 8; r++){
			for(int c = 0; c < 8; c++){
					grid[r][c]=(new Rectangle2D.Double(rCord,cCord,75,75));
					rCord+=75;
			}
			rCord=0;
			cCord+=75;
		}
	}
	/**
	 * draws the 2d grid of squares
	 * @param g2 takes in the graphics brush and draws it with white color
	 */
	public void draw(Graphics2D g2){
		g2.setColor(Color.WHITE);
		for (int r = 0; r< 8; r++){
			for(int c = 0; c < 8; c++){
					g2.draw(grid[r][c]);
			}
		}
	}
	/**
	 *  fills the square green
	 * @param g2 -- takes in the graphics 2d brush
	 * @param r -- row of the square
	 * @param c -- column of the square
	 */
	public void fill(Graphics2D g2,int r, int c){
		g2.setColor(Color.GREEN); // sets color to green
		g2.fill(grid[r][c]); // fills the square
	}
	
	/**
	 * getter method for the 2d grid
	 * @return -- returns the grid
	 */
	public Rectangle2D[][] getGrid(){
		return grid;
	}
}