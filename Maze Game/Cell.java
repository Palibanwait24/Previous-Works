/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs260project2;

/**
 *
 * @author AmritPal
 */
public class Cell {
    private int row ; // row location
    private int column; // column location
    private Cell nLink = null; // links to the other cells
    private Cell eLink = null;
    private Cell sLink = null;
    private Cell wLink = null;
    private Cell backLink = null;
    private boolean visited = false; //  has the cell been visited
    
    /**
     * constructor to the cell
     * @param row - row of the cell
     * @param column - column of the cell
     */
    public Cell(int row, int column){
        this.row = row;
        this.column = column;
    } 
    /**
     * returns the row value
     * @return - the row value
     */
    public int getRow(){
        return row;
    }
    /**
     * return the column value
     * @return - column location
     */
    public int getColumn(){
        return column;
    }
    /**
     * gets the north link value
     * @return - the north link cell
     */
    public Cell getNLink(){
        return nLink;
    }
    /**
     * gets the east link value
     * @return - the east link cell
     */
    public Cell getELink(){
        return eLink;
    }
    /**
     * gets the south link value
     * @return - the south link cell
     */
    public Cell getSLink(){
        return sLink;
    }
    /**
     * gets the west link value
     * @return - the west link cell
     */
    public Cell getWLink(){
        return wLink;
    }
    /**
     * gets the back link value
     * @return - the back link cell
     */
    public Cell getBackLink(){
        return backLink;
    }
    /**
     * gets the visited value
     * @return - visited value
     */
    public boolean getVisited(){
        return visited;
    }
    /**
     * sets the back link
     * @param link - new link
     */
     public void setBackLink(Cell link){
        this.backLink = link;
    }
     /**
      * sets the new value
      * @param value - true or false value
      */
      public void setVisited(boolean value){
        this.visited = value;
    }
    /**
     * sets the link
     * @param link - takes the link and sets the directional link
     */
    public void setNLink(Cell link){
        this.nLink = link;
    }
    /**
     * sets the link
     * @param link - takes the link and sets the directional link
     */
    public void setELink(Cell link){
        this.eLink = link;
    }
    /**
     * sets the link
     * @param link - takes the link and sets the directional link
     */
    public void setSLink(Cell link){
        this.sLink = link;
    }
    /**
     * sets the link
     * @param link - takes the link and sets the directional link
     */
    public void setWLink(Cell link){
        this.wLink = link;
    }
    /**
     * takes two cells and checks if the nextCell is a neighbor of the startcell
     * @param startCell - start cell
     * @param nextCell- nextCell
     * @return - boolean value if it is related
     */
    public static boolean linked(Cell startCell, Cell nextCell){
        return(startCell.nLink == nextCell || startCell.eLink == nextCell ||
                startCell.sLink == nextCell || startCell.wLink == nextCell);
    }
    /**
     * returns a string  
     * @return 
     */
    public String toString(){
        return "Row = " + row + ", Column = " + column;
    }
    /**
     * sums the row and column
     * @return - returns the sum
     */
    public int sum(){
        return row + column;
    }
}
