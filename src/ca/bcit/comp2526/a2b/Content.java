package ca.bcit.comp2526.a2b;

import java.awt.Color;

/**
 * Abstract class of the types of objects that can be held by a Cell and
 *      can only exist in a Cell.
 * 
 * @author Jeffrey
 * @version 2017-11-04
 *
 */
public abstract class Content implements Edible {

    /**
     * The color associated with this object.
     */
    private Color color;
    
    /**
     * Flag for whether this object has performed an action this turn.
     */
    private boolean actionTaken;
    
    /**
     * The current Cell location this object exists in.
     */
    private Cell location;
    
    /**
     * The type of Content this object is.
     */
    private LifeformType type;

    /**
     * Creates a content object.
     * @param location
     *          The Cell the object initially inhabits.
     */
    public Content(Cell location) {
        this.location = location;
    }

    /**
     * Sets the background colour of the cell.
     */
    abstract void init();

    /**
     * Performs action related to the Content.
     */
    abstract void takeAction();

    /**
     * Returns the colour associated with the Content type.
     * 
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color related to this Content type.
     * 
     * @param color
     *            the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks whether this object has taken an action this turn.
     * 
     * @return the actionTaken
     */
    public boolean isActionTaken() {
        return actionTaken;
    }

    /**
     * Sets the actionTaken flag.
     * 
     * @param actionTaken
     *            the actionTaken to set
     */
    public void setActionTaken(boolean actionTaken) {
        this.actionTaken = actionTaken;
    }

    /**
     * Gets the current Cell that this object exists in.
     * 
     * @return the current Cell location of object
     */
    public Cell getLocation() {
        return location;
    }

    /**
     * Sets the current Cell that this object exists in.
     * 
     * @param location
     *            the location to set
     */
    public void setLocation(Cell location) {
        this.location = location;
    }

    /**
     * Returns the type of content of the object.
     * 
     * @return the type in ContentType enum format.
     */
    public LifeformType getType() {
        return type;
    }

    /**
     * Sets the type of content of the object.
     * 
     * @param type
     *            the type to set
     */
    public void setType(LifeformType type) {
        this.type = type;
    }
}
