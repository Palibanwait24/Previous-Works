/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs260project2;


import java.util.Scanner;

/**
 *
 *
 */
public class LinkedMaze {
    Queue pathFinder;
    Cell entry, exit, maxCell;
    Cell[][] cells;
    int length, width, maxDistance;
    /**
     * constructor of the linkedMaze Class
     * @param length - length of the maze
     * @param width  - width of the maze
     * @param finder - finder queue
     */
    public LinkedMaze(int length, int width, Queue finder){
        this.length = length;
        this.width = width;
        this.pathFinder = finder;
        this.cells = new Cell[length][width];
        // adds cells to a 2d array of cells
        for(int i = 0; i < cells.length; i++){
            for(int j =0; j<cells.length;j++){
                cells[i][j] = new Cell(i,j);
            }
        }
        
        this.entry = cells[0][0];//entry of the cells
        
        this.exit = cells[length -1][width-1]; // exit of the maze
        
    }
    /**
     * gets the maxdistance value
     * @return - returns max distance
     */
    public int getMaxDistance(){
        return maxDistance;
    }
    /**
     * takes in the scanners and connects the maze by setting the directional links
     * @param southScan - takes in a scanner to read south values
     * @param eastScan - takes in a scanner to read east values
     */
    public void connectMaze(Scanner southScan, Scanner eastScan){
        for(int row = 0; row < cells.length; row++){
            for(int column =0; column <cells.length; column++){
                // if south scan is a 0 (passasable) then sett the north link of
                // the cell below it and set the south link of its cell to the cell below it
                if(southScan.nextInt() == 0){
                    cells[row+1][column].setNLink(cells[row][column]);
                    cells[row][column].setSLink(cells[row+1][column]);
                }
                //sets west link of cell to the right of current cell and sets east link of currrent cell
                if(eastScan.nextInt() == 0){
                    cells[row][column+1].setWLink(cells[row][column]);
                    cells[row][column].setELink(cells[row][column + 1 ]);  
                }
            }
        }     
    }
    /**
     * finds the path through the maze
     * @return - if the path was found to the end
     */
    public boolean findPath(){
        
        entry.setVisited(true);
        pathFinder.enqueue(entry);
        boolean end = false;
        Cell current = null;
        while(end == false){
            if (pathFinder.isEmpty()){
                   
                entry = reversePath(current);
                return false;
            }
            
            current = pathFinder.dequeue();
             
            if (Cell.linked(current, exit)){
                
                entry = reversePath(current);
                return true;
            }
            else{
                
                
                if(current.getELink() != null && current.getELink().getVisited() != true){
                    current.setVisited(true);
                    pathFinder.enqueue(current.getELink());
                    current.getELink().setBackLink(current);
                    
                    if( current.getELink().getRow() + current.getELink().getColumn()> maxDistance){
                        maxDistance =current.getELink().getRow() + current.getELink().getColumn();
                        maxCell =current.getELink();
                    }
                }
                if(current.getSLink() != null && current.getSLink().getVisited() != true){
                   current.setVisited(true);
                    pathFinder.enqueue(current.getSLink());
                    current.getSLink().setBackLink(current);
                    if( current.getSLink().getRow() + current.getSLink().getColumn()> maxDistance){
                        maxDistance =current.getSLink().getRow() + current.getSLink().getColumn();
                        maxCell =current.getSLink();
                    }
                    
                }
                if(current.getWLink() != null && current.getWLink().getVisited() != true){
                   current.setVisited(true);
                    pathFinder.enqueue(current.getWLink());
                    current.getWLink().setBackLink(current);
                    
                }
                if(current.getNLink() != null && current.getNLink().getVisited() != true){
                   current.setVisited(true);
                    pathFinder.enqueue(current.getNLink());
                    current.getNLink().setBackLink(current);
                    
                }
                
            }
           
        }
        
        return false;   
        
    }
    /**
     * reverses the path and sets the head to the tail and tail to the head
     * @param current - cell that the findPath stopped at
     * @return - the new cell
     */
    public Cell reversePath(Cell current){
        Cell previousCell=null;  
        Cell nextCell;  
        while(current!=null){  
            nextCell=current.getBackLink();    
            current.setBackLink(previousCell);  
            previousCell=current;  
            current=nextCell;  
        }  
        return previousCell; // returns the first head
        
    }
    /**
     * printed the display of the path
     */
    public void displayPath(){
        Cell myCells = entry; // entry cell
        String[][] myPath = new String[length][width]; // make a string 2d array
        int path = 1; // start path at 1
        
        while(myCells != null){ // while it is not null
            
                myPath[myCells.getRow()][myCells.getColumn()] = path +""; // adds the value
                path++; // increments path
                myCells = myCells.getBackLink(); // cycles next link
        }
        myPath[exit.getRow()][exit.getColumn()] = path +""; // makes the exit the last path value
                
        
        for(int row = 0; row < length; row++){
            for(int column =0; column < width; column++){
                if(myPath[row][column] == null){ // if string is null then print spaces
                    System.out.print("  ");
                }
                else{
                    /// 
                    ///  adds spaces for alighment of text
                    ///
                    if(myPath[row][column].equals("10") || myPath[row][column].equals("25")){
                        System.out.print(myPath[row][column] + "  ");
                    }
                    else if(myPath[row][column].equals("9")){
                        System.out.print(myPath[row][column] + "   ");
                    }
                    else if(myPath[row][column].equals("22") ||
                            myPath[row][column].equals("23") ||
                            myPath[row][column].equals("24")){
                        System.out.print(" " + myPath[row][column] + " ");
                    }
                    else
                        System.out.print(myPath[row][column] + " ");
                }
            }
            System.out.println(); // prints new line when new row
        }
    }
    
}
