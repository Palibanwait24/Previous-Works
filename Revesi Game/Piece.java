

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Piece {
	Color color;
	int columnLocation;
	int rowLocation;
	/**
	 * constructor that sets piece color, row location and column location
	 * @param color -- color of the piece- player color
	 * @param rowLocation -- row location
	 * @param columnLocation -- row location
	 */
	public Piece(Color color,int rowLocation , int columnLocation){
// method that draws that takes input of brush - draw and fill--( Color, coordinates)
		this.color = color;
		this.columnLocation = columnLocation;
		this.rowLocation = rowLocation;
	}
	/**
	 * gets color of piece
	 * @return - piece color
	 */
	public Color getColor(){
		return color;
	}
	/**
	 * sets new piece color
	 * @param newColor - sets piece color with the new color
	 */
	public void setNewColor(Color newColor){
		color = newColor;
	}
	/**
	 * get the row cord of the piece
	 * @return rowLocation - gets the row locatin of the piece
	 */
	public int getRowCord(){
		return rowLocation;
	}
	/**
	 * gets the column cord;
	 * @return column Location of the piece
	 */
	public int getColumnCord(){
		return columnLocation;
	}
	/**
	 * draws pieces to panel
	 * colors of piece and fills ellipse
	 * @param g2
	 */
	public void DrawPiece(Graphics2D g2){
		g2.setColor(color);
		Ellipse2D ellipse =new Ellipse2D.Double(columnLocation*75,rowLocation*75,73,73);
		g2.fill(ellipse);
	}
	
}
