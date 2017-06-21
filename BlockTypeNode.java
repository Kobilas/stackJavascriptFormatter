package mkobilas.homework.javascriptfomatter;

/**
 * The BlockTypeNode class is the wrapper for BlockType objects so that they are able to then be fitted into a linked list
 *   for uses elsewhere such as in stacks. The BlockTypeNode object features data in the form of a BlockType, as well
 *   as a link to the next BlockTypeNode in the linked list.
 * @author Matthew Kobilas
 *     matthew.kobilas@stonybrook.edu
 *     Stony Brook ID: 111152838
 *     CSE214-R02
 */
public class BlockTypeNode {
    //BlockType data contains the BlockType enum object for the type of item that was found in the parsed file.
    private BlockType data;
    //BlockTypeNode link is the next BlockTypeNode object in the linked list.
    private BlockTypeNode link;
    /**
     * A constructor for BlockTypeNode objects which takes a BlockType as a parameter to show what was found in the
     *   file.
     * @param encounter
     *      The BlockType that was found in the parsed file. This parameter cannot be null.
     * @precondition
     *      BlockType encounter cannot be null.
     * @postcondition
     *      Creates a BlockTypeNode object wrapping a BlockType encounter in the object for use in linked lists.
     * @throws IllegalArgumentException
     *      Exception thrown if parameter BlockType encounter is null.
     */
    public BlockTypeNode(BlockType encounter){
        if(encounter ==  null)
            throw new IllegalArgumentException("Argument BlockType encounter cannot be null.");
        data = encounter;
        link = null;
    }
    /**
     * Accessor method for the BlockType data in this BlockTypeNode.
     * @return
     *      Returns this BlockTypeNode's BlockType data.
     */
    public BlockType getData(){
        return data;
    }
    /**
     * Mutator method for the BlockType data in this BlockTypeNode
     * @param newData
     *      BlockType newData that the BlockType data object in this class will be changed to. This cannot be null.
     * @precondition
     *      BlockType newData cannot be null.
     * @postcondition
     *      BlockType data is set to newData.
     * @throws IllegalArgumentException
     *      Exception thrown if BlockType newData is null.
     */
    public void setData(BlockType newData){
        if(newData ==  null)
            throw new IllegalArgumentException("Argument BlockType newData cannot be null.");
        data = newData;
    }
    /**
     * Accessor method for the BlockTypeNode link in this BlockTypeNode.
     * @return link
     *      Returns this objects BlockTypeNode link.
     */
    public BlockTypeNode getLink(){
        return link;
    }
    /**
     * Mutator method for the BlockTypeNode link in this BlockTypeNode.
     * @param newPrev
     *      BlockTypeNode newPrev that the BlockType link object in this class will be changed to. This can be null to 
     *        symbolize that the linked list is empty or to remove this BlockTypeNode from the linked list.
     * @postcondition
     *      BlockTypeNode link will be set to BlockTypeNode newPrev specified here.
     */
    public void setLink(BlockTypeNode newPrev){
        link = newPrev;
    }
}
