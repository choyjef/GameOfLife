package ca.bcit.comp2526.a2c;

/**
 * An exception that when thrown indicates an inability to
 * add data to a collection.
 * 
 * @author Jeffrey
 * @version 2017-12-02
 */
public class CouldNotRemoveException extends Exception {

    private static final long serialVersionUID = 9009475499681954213L;

    /**
     * Instantiates a CouldNotRemoveException object.
     */
    public CouldNotRemoveException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Instantiates a CouldNotAddException allowing
     * a string message.
     * @param arg0
     *          message string to be printed
     */
    public CouldNotRemoveException(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

}
