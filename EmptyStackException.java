package mkobilas.homework.javascriptfomatter;

/**
 * Custom exception that is thrown if the JSStack object is empty and has no BlockTypeNode objects in it.
 * @author Matthew Kobilas
 *       matthew.kobilas@stonybrook.edu
 *       Stony Brook ID: 111152838
 *       CSE214-R02
 */
public class EmptyStackException extends Exception{
    private static final long serialVersionUID = 773049630143735328L;
    /**
     * Empty constructor for the EmptyStackException.
     * @postcondition
     *   Creates an empty EmptyStackException object that can be thrown.
     */
    public EmptyStackException(){
    }
    /**
     * Constructor for the EmptyStackException asking for a message to be printed when the printStackTrace() method is
     *   called anywhere in the program.
     * @param message
     *      String message output to the user conveying what went wrong and where.
     * @postcondition
     *      Creates an EmptyStackException with an informative message on what went wrong which can be thrown throughout
     *        the program.
     */
    public EmptyStackException(String message){
        super(message);
    }
    /**
     * Constructor for the EmptyStackException asking for a message to be printed when the printStackTrace() method is 
     *   called as well as a Throwable cause if the exception is being rethrown from a different location in the
     *   program.
     * @param message
     *      String message output to the user conveying what went wrong and where.
     * @param cause
     *      Throwable cause is used to rethrow the exception from a different location in the program if there is a
     *        reason to.
     * @postcondition
     *      Creates an EmptyStackException with an informative message on what went wrong with the ability to have 
     *        information from another thrown exception if the exception from another part of the program is needed to
     *        be rethrown.
     */
    public EmptyStackException(String message, Throwable cause){
        super(message, cause);
    }
}
