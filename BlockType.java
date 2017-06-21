package mkobilas.homework.javascriptfomatter;

/**
 * The BlockType class is of enum type in order to signify what BlockType is currently on the stack. The BlockType
 *   object can be of types BRACE, for curly braces '{', PAREN, for parentheses '(', or FOR, for the instantiation of a
 *   "for(" loop.
 * @author Matthew Kobilas
 *     matthew.kobilas@stonybrook.edu
 *     Stony Brook ID: 111152838
 *     CSE214-R02
 */
public enum BlockType{
    BRACE, PAREN, FOR;
}
