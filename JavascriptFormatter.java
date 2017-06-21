package mkobilas.homework.javascriptfomatter;

import java.io.*;

/**
 * The JavascriptFormatter class contains the actual code that is used to format the input file, as well as containing
 *   the main JSStack object that the parsed objects are placed into for reference on what the order of them is 
 *   throughout the file. The JavascriptFormatter is turned into an object that is used in the main driver class later
 *   in the program.
 * @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      Stony Brook ID: 111152838
 *      CSE214-R02
 */
public class JavascriptFormatter {
    //FileReader object that processes the file that is input.
    private FileReader toRead;
    //Main BufferedReader object that reads the file or String one character at a time in order to construct a properly
      //formatted String or to translate the file into a String object first.
    private BufferedReader reader;
    //StringReader object which processes the file that was converted into a String.
    private StringReader fileString;
    //StringBuilder object which builds the String object out of the input file's text.
    private StringBuilder fileStringBuilder;
    //StringBuilder object which builds the formatted file's String that is then returned once the program finishes
      //execution.
    private StringBuilder formatStringBuilder;
    //The main JSStack used to store the block types found throughout the parsed input file.
    private JSStack stack;
    //int object indentLevel used to keep track of how much the formatted file should be indented.
    //int object temp is used to keep track of the number value of the next char read from the file or String by the
      //main BufferedReader above.
    private int indentLevel = 0, temp;
    //String object worked is the temporary String object used to move the input file's text throughout the class.
    //String object tempLine is the temporary String object used to hold the read lines from the file.
    private String worked, tempLine = null;
    //boolean object fSwitch, oSwitch, and rSwitch are used to determine whether or not a "for(" block was found in the
      //program.
    //boolean object toIndent is used to figure out whether or not a reason to indent was found once the previous 
      //character in the file or String was read by the BufferedReader.
    private boolean fSwitch = false, oSwitch = false, rSwitch = false, toIndent = false;
    /**
     * Constructor for the JavascriptFormatter in order to allow use in other classes. The constructor also initializes
     *   the JSStack object used throughout this class.
     * @postcondition
     *      Creates a JavascriptFormatter containing an empty JSStack object.
     */
    public JavascriptFormatter(){
        stack = new JSStack();
    }
    /**
     * Returns the completely formatted file that was input when the program was first run.
     * @param input
     *      String input is the file or path to the input file's location in the current computer. This is used by the
     *        fileReader object to then find the file for use throughout the program.
     * @precondition
     *      String input must be a valid file path location to the desired input file.
     * @return
     *      Returns the String that contains the input file's text, except formatted to certain specifications listed
     *        below.
     * @throws FileNotFoundException
     *      Throws an exception if the file path or file entered was not found in the computer.
     * @throws IOException
     *      Throws an exception if an error occurs while reading the file or String with the BufferedReader.
     */
    public String format(String input) throws FileNotFoundException, IOException{
        /*
         * Specifications are such that the formatted file will be the input file, except with new lines after every
         *   curly brace found, as well as after every semicolon. The program keeps track of the number of curly braces
         *   found and appends a tab of white space in order to keep blocks of code organized for reading purposes.
         */
        formatStringBuilder = new StringBuilder();
        //Reads the input file to the FileReader, then converts it to a String object.
        worked = fileToString(input);
        //Uses the created String object in another method to remove any erroneous white space so that the file is able
          //to be formatted from a fresh start.
        worked = removeWhiteSpace(worked);
        //Places the file's text without any white space into a StringReader to be processed in order to be read by the
          //main BufferedReader.
        fileString = new StringReader(worked);
        //Places the file's text into the BufferedReader in order for it to be read one character at a time.
        reader = new BufferedReader(fileString);
        //Counter instantiated that is used to insert indents throughout the file's formatted String.
        int i;
        //stackLoop is a while loop which loops continuously until the end of the file is reached.
        stackLoop: while((temp = (reader.read())) != (-1)){
            //If there is a reason to indent, then the loop used to append indents is run.
            if(toIndent){
                if((char)temp ==  '}')
                    indentLevel--;
                for(i = 0; i < indentLevel; i++ ){
                    formatStringBuilder.append("\t");
                }
                toIndent = false;
            }
            //Appends the character found in the file to the StringBuilder that is being created.
            formatStringBuilder.append((char)temp);
            //Switch block for determining what character is reached in the file.
            switch((char)temp){
                //Pushes a BlockType.BRACE onto this JavascriptFormatter's JSStack object and then creates a new line
                  //as well as telling the indentation loop to run.
                case '{':{
                    indentLevel++ ;
                    formatStringBuilder.append("\n");
                    stack.push(BlockType.BRACE);
                    toIndent = true;
                    continue;
                }
                //Pops a BlockTypeNode from the top of the JSStack, and if it is not of the correct type, breaks the 
                 //loop, stops operation of the program, and prints an error message.
                case '}':{
                    try{
                        if(stack.pop() != BlockType.BRACE){
                            formatStringBuilder.append("\n//ERROR: extra closing curly brace found.\n");
                            break stackLoop;
                        }
                    }
                    catch(EmptyStackException err){
                        formatStringBuilder.append("\n//ERROR: extra closing curly brace found.\n");
                        break stackLoop;
                    }
                    formatStringBuilder.append("\n");
                    toIndent = true;
                    continue;
                }
                //Creates a new line after the semicolon if not contained in for block, or prints an error message if 
                  //placed after a parenthesis that has not yet been closed.
                case ';':{
                    if(stack.peek() ==  BlockType.FOR)
                        continue;
                    if(stack.peek() ==  BlockType.PAREN){
                        formatStringBuilder.append("\n//ERROR: no closing parenthesis found.\n");
                        break stackLoop;
                    }
                    formatStringBuilder.append("\n");
                    toIndent = true;
                    continue;
                }
                //Pushes a BlockType.PAREN onto the stack, or a BlockType.FOR onto the stack if the for loop switches
                  //all ended up being true.
                case '(':{
                    if(rSwitch){
                        fSwitch = false;
                        oSwitch = false;
                        rSwitch = false;
                        stack.push(BlockType.FOR);
                        continue;
                    }
                    stack.push(BlockType.PAREN);
                    continue;
                }
                //Pops a BlockTypeNode from the stack and if it does not match the type, an error messages is printed, 
                  //the stackLoop breaks, and the program halts operation.
                case ')':{
                    try{
                        if(stack.pop() ==  BlockType.BRACE){
                            formatStringBuilder.append("\n//ERROR: extra closing parenthesis found.\n");
                            break stackLoop;
                        }
                    }
                    catch(EmptyStackException err){
                        formatStringBuilder.append("\n//ERROR: extra closing parenthesis found.\n");
                        break stackLoop;
                    }
                    continue;
                }
                //Sets the fSwitch to true if an 'f' character is found in order to check if a for block has been found
                  //in the file.
                case 'f':{
                    fSwitch = true;
                    continue;
                }
                //Sets the oSwitch to true if an 'o' character is found in order to check if a for block has been found
                  //in the file. If the fSwitch has not been toggled, then it sets previous switches to false.
                case 'o':{
                    if(fSwitch)
                        oSwitch = true;
                    else
                        fSwitch = false;
                    continue;
                }
                //Sets the rSwitch to true if an 'r' character is found in order to check if a for block has been found
                  //in the file. If the oSwitch has not been toggled, then it sets previous switches to false.
                case 'r':{
                    if(oSwitch)
                        rSwitch = true;
                    else{
                        fSwitch = false;
                        oSwitch = false;
                    }
                    continue;
                }
                //Sets the for loop block switches to false if a character not in the switch is found.
                default:{
                    fSwitch = false;
                    oSwitch = false;
                    rSwitch = false;
                    continue;
                }
            }
        }
        //If the stack is not empty, then there is a block type left in it and an error message is printed.
        if(!(stack.isEmpty())){
            if(stack.peek() ==  BlockType.BRACE)
                formatStringBuilder.append("\n//ERROR: missing closing curly brace.\n");
        }
        //Returns the StringBuilder with the formatted file with toString().
        return formatStringBuilder.toString();
    }
    /**
     * fileToString creates a String object out of the input file's text.
     * @param input
     *      String input is the file path to the input file.
     * @precondition
     *      String input must be a valid file path to the desired input file.
     * @return
     *      Returns the String of the input file's text.
     * @throws FileNotFoundException
     *      Throws an exception if the input file is not found based on the information entered by the user.
     * @throws IOException
     *      Throws an exception if the BufferedReader encounters a problem while reading characters from the file.
     */
    public String fileToString(String input) throws FileNotFoundException, IOException{
        toRead = new FileReader(input);
        reader = new BufferedReader(toRead);
        fileStringBuilder = new StringBuilder();
        //Reads the file line by line, appending the lines to the StringBuilder that is later returned as a String.
        while((tempLine = reader.readLine()) != null){
            fileStringBuilder.append(tempLine);
        }
        return fileStringBuilder.toString();
    }
    /**
     * removeWhiteSpace removes all possibly unneeded or erroneous white space from the file's text that was sent to the
     *   String object.
     * @param input
     *      String input is the input file's text.
     * @return
     *      Returns a String of the input file's text without any tabs or new lines.
     */
    public String removeWhiteSpace(String input){
        input = input.replaceAll("\n", "");
        input = input.replaceAll("\t", "");
        return input;
    }
}
