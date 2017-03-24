/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs260project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author AmritPal
 */
public class Cs260Project2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File file1 = new File("eastWest.txt"); // reads file
        File file2 = new File("northSouth.txt"); // reads file
        Scanner ewScan = null; // create scanners
        Scanner nsScan = null;
        int row = 0; // row and column
        int column = 0;
        try {
            ewScan = new Scanner(file1); // initializes scanners
            nsScan = new Scanner(file2);
            row = ewScan.nextInt(); // gets the row
            column = ewScan.nextInt(); // gets the coumn
        }   
        catch (FileNotFoundException e) { // thows exception and ends program if no file found
            System.out.println("files not found");
            System.exit(0);
        }
        
        Queue myQueue = new Queue(20); // instantiates a queue
        
        LinkedMaze myMaze = new LinkedMaze(row,column,myQueue); // creates a linkedMaze object
        
        myMaze.connectMaze(nsScan, ewScan); // calls the connectMaze method
        
        boolean flag = myMaze.findPath(); // creates a boolean flag
       
        if(flag){ // if flag is true then dispay the path
            myMaze.displayPath();
        }
        else{
            myMaze.displayPath(); // display path and print how far it went
            System.out.println("the farthest the maze went was " +
                    myMaze.getMaxDistance() + " cells");
        }
        
    }
    
}
