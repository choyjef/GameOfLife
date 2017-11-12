package ca.bcit.comp2526.a2b;

import java.awt.Color;

/**
 * A Blank Content object. Used to represent an empty Cell.
 * 
 * @author Jeffrey
 * @version 2017-11-04
 */
public class Blank extends Content {

    /**
     * Creates a Blank object.
     * 
     * @param location
     *          the initial Cell location where this object exists
     */
    public Blank(Cell location) {
        super(location);
        setColor(Color.white);
        setType(ContentType.BLANK);
    }

    @Override
    public void init() {
        getLocation().setColor(Color.white);

    }

    @Override
    public void takeAction() {
        // Blank Cells do nothing for their action.
    }

    @Override
    public boolean isEdible(Herbivore herbivore) {
        return false;
    }

    @Override
    public boolean isEdible(Plant plant) {
        return false;
    }

}
