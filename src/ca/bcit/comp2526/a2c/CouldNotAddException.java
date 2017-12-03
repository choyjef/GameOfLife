package ca.bcit.comp2526.a2c;

/**
 * An exception indicating an ability to add data to
 * a collection.
 * 
 * @author Jeffrey
 * @version 2017-12-02
 */
public class CouldNotAddException extends Exception {

    private static final long serialVersionUID = -937603809942485279L;

    /**
     * Instantiates a CouldNotAddException object.
     */
    public CouldNotAddException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Instantiates a CouldNotAddException allowing
     * a string message.
     * 
     * @param arg0
     *          String message to be printed.
     */
    public CouldNotAddException(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }


}
