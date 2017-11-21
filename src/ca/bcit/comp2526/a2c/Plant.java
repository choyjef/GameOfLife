package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * A Plant object that exists only in a Cell, and seeds new Cells given certain
 * conditions.
 * 
 * @author Jeffrey
 * @version 2017-11-19
 */
public class Plant extends Lifeform {
    
    /**
     * The number of adjacent empty spaces required for reproduction.
     */
    private static final int PLANT_SPACE_REQ = 3;
    
    /**
     * The number of adjacent mates required for reproduction.
     */
    private static final int PLANT_MATES_REQ = 2;
    
    /**
     * Constructs a Plant object.
     * 
     * @param location
     *              The initial Cell location the Plant exists in.
     */
    public Plant(Cell location) {
        super(location);
        setColor(Color.green);
        setHunger(1);
        setType(LifeformType.PLANT);
        setSpaceRequired(PLANT_SPACE_REQ);
        setMatesRequired(PLANT_MATES_REQ);
        setFoodRequired(0);
    }

    @Override
    public void move() {
        // plants do not move or eat
    }

    @Override
    Lifeform giveBirth(Cell birthLocation) {
        
        return new Plant(birthLocation);
    
    }

    @Override
    void resetHunger() {
        setHunger(1);
        
    }

    @Override
    public void updateHealth() {
        // plants do not have hunger
        resetHunger();
    }


    @Override
    public boolean isEdible(Lifeform lifeform) {
        return false;
    }
}
