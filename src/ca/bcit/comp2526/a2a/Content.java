package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * @author Jeffrey
 * @version 2017-11-04
 *
 */
public abstract class Content implements Edible {
    
    private Color color;
    private boolean actionTaken;
    private Cell location;
    private ContentType type;
    
    public Content(Cell location) {
        this.location = location;
    }
    
    
    abstract void init();
    
    abstract void takeAction();

    
    
    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color 
     *          the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the actionTaken
     */
    public boolean isActionTaken() {
        return actionTaken;
    }

    /**
     * @param actionTaken the actionTaken to set
     */
    public void setActionTaken(boolean actionTaken) {
        this.actionTaken = actionTaken;
    }

    /**
     * @return the location
     */
    public Cell getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Cell location) {
        this.location = location;
    }


    /**
     * @return the type
     */
    public ContentType getType() {
        return type;
    }


    /**
     * @param type the type to set
     */
    public void setType(ContentType type) {
        this.type = type;
    }
}
