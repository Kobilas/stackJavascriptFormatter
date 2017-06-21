package mkobilas.homework.javascriptfomatter;

/**
 * The JSStack class is the singly-linked list that serves as the stack for this program to show in which order objects
 *   are found throughout the file. The class has typical stack methods to provide functionality.
 * @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      Stony Brook ID: 111152838
 *      CSE214-R02
 */
public class JSStack {
    //Reference to the top of the stack, or the first BlockTypeNode in the linked list.
    private BlockTypeNode top;
    /**
     * Constructor for the JSStack object which initiates the top reference to null.
     * @postcondition
     *      Creates a JSStack object with the top reference initiated to null. The JSStack object can then be used to
     *        store BlockTypeNode objects in the provided linked list.
     */
    public JSStack(){
        top = null;
    }
    /**
     * Returns a true/false value on whether or not there are BlocKTypeNode objects in this JSStack.
     * @return
     *      Returns a boolean value on whether or not the top reference is set to null, suggesting that the stack is
     *        empty.
     */
    public boolean isEmpty(){
        return (top ==  null);
    }
    /**
     * Pushes a block type encountered in the parsed file onto the stack in order to keep the file properly sorted. By
     *   keeping track of which blocks are found in the file, the program is able to determine whether or not the 
     *   parentheses and braces are evenly placed, with the same amount on both sides, and to make sure there are no
     *   missing or extra blocks.
     * @param block
     *      BlockType block is the encountered block in the parsed file. It may be of type BlockType.FOR, 
     *        BlockType.PAREN, or BlockType.BRACE.
     */
    public void push(BlockType block){
        BlockTypeNode newNode = new BlockTypeNode(block);
        newNode.setLink(top);
        top = newNode;
    }
    /**
     * Pops the first block type off of the top of the stack, and returns what the BlockType found inside is.
     * @precondition
     *      This JSStack object cannot be empty and must contain some amount of BlockTypeNode objects in it.
     * @postcondition
     *      The first object in this stack is removed from the linked list, and its data value is returned. The next 
     *        BlockTypeNode object in the linked list is set to the top reference.
     * @return
     *      Returns the BlockType data of the BlockTypeNode that was popped off of the top of the stack.
     * @throws EmptyStackException
     *      Throws an exception if this JSStack object is empty and does not have and BlockTypeNode objects in it.
     */
    public BlockType pop() throws EmptyStackException{
        if(isEmpty())
            throw new EmptyStackException("The JSStack does not currently have any BlockTypeNodes in it.");
        BlockType result;
        result = top.getData();
        top = top.getLink();
        return result;
    }
    /**
     * Returns the BlockType data that the top reference is currently referencing. This is used to determine what is 
     *   held in the first object on the stack without popping it off the stack.
     * @return
     *   Returns the BlockType data that the top BlockTypeNode was wrapping, or returns null if this JSStack is empty.
     */
    public BlockType peek(){
        if(isEmpty())
            return null;
        return top.getData();
    }
}
