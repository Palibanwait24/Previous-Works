import java.util.*;

/**
 * @author Amrit Pal Banwait
 *CS161-01 Spring 2015
 *project 1
 *
 *main class 
 */

public class PigLatin {

	/**Main method that reads keyboard then calls the methods to change the phrase to piglatin
	 * also it iterates through all the words in the phrase and then prints the phrase to the console
	 * @param args not used
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter phrase in english:");
		String phrase = keyboard.nextLine();
		phrase = phrase.toLowerCase();
		ArrayList<StringBuffer> splitPhrase=splitPhrase(phrase); 
		StringBuffer pigLatin = null;
		for (int x=0;x<splitPhrase.size();x++){
			int i=0;
			i=latin(splitPhrase.get(0));
			pigLatin=append(i,splitPhrase.get(0));
			splitPhrase.remove(0);
			splitPhrase.add(pigLatin);
		}
		System.out.println("-------------------");
		System.out.println("Phrase in piglatin:");
		for (int x=0;x<splitPhrase.size();x++){
			System.out.print(splitPhrase.get(x)+" ");
		}
	}
	/**
	 * takes the word and then finds the number of constants before the first vowel
	 * @param word - brings in each word to count for the number of constants
	 * @return numberConstants-all constants before the first vowel
	 */
	public static int latin(StringBuffer word){
		int numberConstants=0;
		for (int x=0;x<word.length();x++){
			if(word.charAt(x)!='a'&&word.charAt(x)!='e'&&word.charAt(x)!='i'&&word.charAt(x)!='o'&&word.charAt(x)!='u'&&word.charAt(x)!='y'){
				numberConstants++;
			}
			else{
				return numberConstants;
			}
		}
		return numberConstants;		
	}
	/**
	 * takes the number of constants and the word and reagranges the letters to the 
	 * correct pigLatin format by adding yay or ay to the end
	 * @param numberConstants- takes the number of constants for the for loop
	 * @param word takes the word and appends it
	 * @return word- the appended version of the word
	 */
	public static StringBuffer append(int numberConstants, StringBuffer word){
		if (numberConstants==0){
			word.append("yay");
		}
		else{
		for (int x=0;x<numberConstants;x++){
			char temp;
			temp=word.charAt(0);
			word.deleteCharAt(0);
			word.append(temp);
		}
		word.append("ay");
		}
		return word;
	}
	/**
	 * splits a string into separate words
	 * @param phrase - takes the phrase and separates it into and array list
	 * @return list -returns the array list version of the phrase
	 */
	public static ArrayList<StringBuffer> splitPhrase(String phrase){
		StringTokenizer st = new StringTokenizer(phrase);
		ArrayList<StringBuffer> list = new ArrayList<StringBuffer>();
		while(st.hasMoreTokens()){
			list.add(new StringBuffer(st.nextToken()));
		}
		return list;
	}
	

}
