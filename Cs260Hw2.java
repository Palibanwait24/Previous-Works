
import static java.lang.Math.pow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AmritPal
 */
public class Cs260Hw2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // double base = 7;
       // int num = 2;
      //  System.out.println(find(base, num));
        String[] data = {"a","b","c","d","e"};
        int manyItems =5 ;
    String myString = "";
        int position = 0;
        for(int i = 0; i < manyItems; i++){
            myString = myString + "\n Row: " + i;
           
            
        }
        System.out.println(myString);
       
    }

    static double find(double base, int num) {
        if (num == 0) {
            return 1;
        } else if (num < 0) {
            return 1 / pow(base, -num);
        } else {
            double value = pow(base, num / 2);
            if (num % 2 == 0) {
                return value * value;
            } else {
                return value * value * base;
            }
        }//end else		
    }//end method

}
