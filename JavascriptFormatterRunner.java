package mkobilas.homework.javascriptfomatter;

import java.io.*;
import java.util.Scanner;

/**
 * Main driver class for the program which prompts the user for input as to where the desired Javascript or Text file
 *   is located. The file is then returned formatted as with the specifications outlined in the JavascriptFormatter
 *   class.
 * @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      Stony Brook ID: 111152838
 *      CSE214-R02
 */
public class JavascriptFormatterRunner{
    //Scanner keyboard is used to read input from the user as to where the file is located.
    private static Scanner keyboard = new Scanner(System.in);
    //String file is the file path entered by the user.
    //String result is the resultant Javascript or Text file fully formatted.
    private static String file, result;
    //JavascriptFormatter object used to format the input file according to specifications.
    private static JavascriptFormatter formatter = new JavascriptFormatter();
    /**
     * The main method is used to give the user prompts and output errors found throughout the program. It also prompts
     *   the user for input whenever needed, as in the case of inputting the file path to the file that is to be
     *   formatted.
     * @param args
     *      String[] args is not used in this class, it is simply used to help the program interpret the method as a
     *        main method.
     * @precondition
     *      A valid file path must be entered for the program to properly output the formatted file.
     * @postcondition
     *      The input file, except properly formatted.
     */
    public static void main(String[] args){
        System.out.println("Welcome to the Javascript Formatter.");
        while(true){
            System.out.print("Please enter a filename: ");
            file = keyboard.nextLine();
            try{
                //Formats the file using the JavascriptFormatter object.
                result = formatter.format(file);
                break;
            }
            catch(FileNotFoundException err){
                System.out.println("\nFile " + file + " was not found. Please try again.");
            }
            catch(IOException err){
                System.out.println("\nError reading file.");
            }
        }
        //Prints the resulting formatted program and then quits the program gracefully.
        System.out.println("-------- Properly formatted program --------\n");
        System.out.println(result);
        System.out.println("-------- Thank you for making your code readable! --------");
        keyboard.close();
        System.exit(0);
    }
}
