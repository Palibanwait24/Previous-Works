/**
 *
 */
package banwas01_Project1;

/**
 * @author Amrit Pal Banwait CS160-01 Fall 2014 Project 1
 */
import java.util.Scanner;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class FederalTax {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        double taxTotal = 0; // calculated tax value
        String message; // output message
        String income; // to store income (input)
        String name;  // user's name
        String status; // filling status

        Double income2;// yearly income

        System.out.println("Enter the tax payer's name:");
        name = keyboard.nextLine();// solicits name on console

        System.out.println("Enter the filing status:"
                + "\nWrite si for single, mj for married filing jointly");
        status = keyboard.nextLine(); // solicits filing status on console

        keyboard.close();// closes input on console

        income = JOptionPane.showInputDialog(null, "Enter the taxable income:",
                "Tax Calculator", JOptionPane.QUESTION_MESSAGE);// solicits income

        // named constants to store upper limits for single filing brackets
        final int SI1 = 9075;
        final int SI2 = 36900;
        final int SI3 = 89350;
        final int SI4 = 186350;
        final int SI5 = 405100;
        final int SI6 = 406750;

		// named constants to store upper limits for married jointly brackets
        final int MJ1 = 18150;
        final int MJ2 = 73800;
        final int MJ3 = 148850;
        final int MJ4 = 226850;
        final int MJ5 = 405100;
        final int MJ6 = 457600;
        // named constants to store tax rates
        final double TR1 = 0.10;
        final double TR2 = 0.15;
        final double TR3 = 0.25;
        final double TR4 = 0.28;
        final double TR5 = 0.33;
        final double TR6 = 0.35;
        final double TR7 = 0.396;

        if (income == null || income.equals(""))// If the income box comes in empty 
        //	or null the program follows this step
        {
            System.out.println("There is no value for income!"
                    + "\n\tRestart the program!");
            System.exit(0);// stops the program if income null or empty
        } else // if not empty or null this step will start
        {
            income2 = Double.parseDouble(income);// converts income, which is a string, to income 2, which is double  

            switch (status) {
                // if filing status is single, these steps will be executed
                case "si":
                case "SI":
                    if (income2 <= SI1) {
                        taxTotal = income2 * TR1;
                    } else {
                        if (income2 <= SI2) {
                            taxTotal = income2 * TR2;
                        } else {
                            if (income2 <= SI3) {
                                taxTotal = income2 * TR3;
                            } else {
                                if (income2 <= SI4) {
                                    taxTotal = income2 * TR4;
                                } else {
                                    if (income2 <= SI5) {
                                        taxTotal = income2 * TR5;
                                    } else {
                                        if (income2 <= SI6) {
                                            taxTotal = income2 * TR6;
                                        } else {
                                            taxTotal = income2 * TR7;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;
                // if filing status is married filling jointly, these steps will be executed
                case "mj":
                case "MJ":
                    if (income2 <= MJ1) {
                        taxTotal = income2 * TR1;
                    } else {
                        if (income2 <= MJ2) {
                            taxTotal = income2 * TR2;
                        } else {
                            if (income2 <= MJ3) {
                                taxTotal = income2 * TR3;
                            } else {
                                if (income2 <= MJ4) {
                                    taxTotal = income2 * TR4;
                                } else {
                                    if (income2 <= MJ5) {
                                        taxTotal = income2 * TR5;
                                    } else {
                                        if (income2 <= MJ6) {
                                            taxTotal = income2 * TR6;
                                        } else {
                                            taxTotal = income2 * TR7;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                // if filing status is anything else this steps will be executed
                default: {
                    System.out.println("Filing status not specified correctly!"
                            + "\nRestart the program!");
                    System.exit(0);
                }
                break;
            }
            //round taxTotal to two decimals
            DecimalFormat formatter = new DecimalFormat("#,##0.00");

            // stores the message to the variable message. and rounds taxTotal to two decimal points	
            message = "Name: " + name + "\nIncome: " + income
                    + "\nFiling status: " + status + "\nTax calculated: " + formatter.format(taxTotal);

            //displays the information
            JOptionPane.showMessageDialog(null, message,
                    "Tax Calculator",
                    JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);// terminates the program
		/*
             * six tests with result
             *  ---- Single filing
             * - income is  $50,000 and the tax rate is 15%. Tax due is $7,500.
             * - income is  $200,000 and the tax rate is 25%. Tax due is $50,000.
             * - income is  $300,000 and the tax rate is 33%. Tax due is $99,000.
             *  
             * ---- Married filing 
             * - income is $100,000 and the tax rate is 15%. Tax due is $15,000.
             * - income is $400,000 and the tax rate is 25%. Tax due is $100,000.
             * - income is $600,000 and the tax rate is 33%. Tax due is $198,000.
             */
        }
    }
}
