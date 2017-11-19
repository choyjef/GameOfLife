package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Plant object that exists only in a Cell, and seeds new Cells given certain
 * conditions.
 * 
 * @author Jeffrey
 * @version 2017-11-04
 */
public class Plant extends Lifeform {
    
    /**
     * The requisite amount of surrounding partners for seeding.
     */
    private static final int PARTNER_REQ = 2;
    
    /**
     * The requisite amount of surround empty Cells for seeding.
     */
    private static final int EMPTY_REQ = 3;
    
    
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
    }


    @Override
    public void move() {
        
    }


    @Override
    public boolean isEdible(Herbivore herbivore) {
        return true;
    }

    @Override
    public boolean isEdible(Plant plant) {
        return false;
    }

    @Override
    public boolean isEdible(Omnivore omnivore) {
        return true;
    }

    @Override
    public boolean isEdible(Carnivore carnivore) {
        return false;
    }

    @Override
    public boolean isEdible(Edible edible) {
        return false;
    }

    @Override
    Lifeform giveBirth(Cell location) {
        
        return new Plant(location);
    
    }

    @Override
    void resetHunger() {
        setHunger(1);
        
    }

    @Override
    public void updateHealth() {
        resetHunger();
    }
}
