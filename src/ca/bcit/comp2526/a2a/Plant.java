/**
 * 
 */
package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * @author Jeffrey
 *
 */
public class Plant extends Organism implements Content {

    /**
     * @param location
     */
    public Plant(Cell location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Color init() {
        // TODO Auto-generated method stub
        return Color.green;
    }

}
