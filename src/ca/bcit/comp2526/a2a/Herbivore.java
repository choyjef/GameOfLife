/**
 * 
 */
package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * @author Jeffrey
 *
 */
public class Herbivore extends Animal implements Content {

    /**
     * @param location
     */
    public Herbivore(Cell location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Color init() {
        // TODO Auto-generated method stub
        return Color.yellow;
    }

}
