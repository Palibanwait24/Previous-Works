/**
 * 
 */
package banwas01_Project2;

import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * @author Amrit Pal Banwait
 *CS160-01 Fall 2014
 *Project 2
 *
 */
public class ExamStatistics {

	/**
	 * @param args
	 */
	
	final static int lowerA = 85;
	final static int lowerB= 75;
	final static int lowerC=65;
	final static int lowerD=55;
	
	public static void main(String[] args) throws IOException
	{
	
		// variables for counting
		int count1=0; // total
		int countA=0;// total A's
		int countB=0;// total B's
		int countC=0;// Total C's
		int countD=0;// total D's
		int countF=0;// total F's
		
		
		// variables for minScore, maxScore, and score
		int maxScore = 0;
		int minScore = 100;
		
		
		// temporary int
		int next;
		
		// variables for sum, average, psd
		double sum = 0;
		double average=0;
		double psd=0;
		
		// two string variables to store output and file name
		String output;
		String inputFileName;
		
		// create a Scanner object for  input
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the file name:");
		inputFileName = keyboard.nextLine();
		
		File fileName = new File(inputFileName); // file object
	
		
		while(!fileName.exists())// cheeks to see that the file exists
			{
			System.out.println("The file does not exist!");
			System.out.println("Please enter the file name:");
			inputFileName = keyboard.nextLine();
			fileName = new File(inputFileName);
			
			}
			
		Scanner reader = new Scanner(fileName);// new scanner object 
		
		while(reader.hasNext())
		{
			next= reader.nextInt();// reads values
			
		if(next>=0 && next<=100)// validates that values are acceptable
			{
				count1++;// counts the total values
					
				if(next>maxScore)// checks max score
				{	
				maxScore=next;
				}
				
				if(next<minScore)// checks min score
				{
					minScore=next;
				}
				
				if(next>=lowerA)// counts how many a b c d f are in the data
					countA++;
				
				else if(next>=lowerB)
					countB++;
				
				else if(next>=lowerC)
					countC++;
				
				else if(next>=lowerD)
					countD++;
				
				else if(next<lowerD)
					countF++;
				
				sum= sum +next;// adds the total sum of data
				
			}
			
		}	// ends while loop
		
			average=sum/count1;// finds average
			
			 reader = new Scanner(fileName);// re-instantiates file reader Scanner Object
			 
			 while(reader.hasNext())
				{
					next= reader.nextInt();// reads values
					
					if(next>=0 && next<=100)// validates that values are acceptable
					{	
						psd += Math.pow((average - next), 2);
					}
						
				}	
			 psd = Math.sqrt(psd /count1);
			 
				//round taxTotal to two decimals
			 DecimalFormat formatter = new DecimalFormat("#,##0.00");
			 
			 
			 //builds output message
			 output="Exam Statistics\n\nTotal: "+ count1+ "\nAverage score: "+ formatter.format(average)+
					 "\nPopulation standard deviation of the scores: "+ formatter.format(psd)+"\n\n"+ 
					 "#of A, 85-100:\t"+ countA+"\t"+ formatter.format((countA*1.0/count1)*100)+"%"+
					 "\n#of B, 75--84:\t"+ countB+"\t"+ formatter.format((countB*1.0/count1)*100)+"%"+
					 "\n#of C, 65--74\t"+ countC+"\t"+ formatter.format((countC*1.0/count1)*100)+"%"+
					 "\n#of D, 55--64:\t"+ countD+"\t"+ formatter.format((countD*1.0/count1)*100)+"%"+
					 "\n#of F, 00--54:\t"+ countF+"\t"+ formatter.format((countF*1.0/count1)*100)+"%"+
			 		 "\n\nMinimum score: " + minScore +"\nMax Score: "+ maxScore;
			 System.out.println(output);
			 
			 
			 reader.close();// closes file
					
				 // writes data to file name scoreStatistics.txt
				 PrintWriter outputFile = new PrintWriter("ScoreStatistics.txt");
				 outputFile.println(output);
				 outputFile.close();
				 
				 
			
			
	}

}
