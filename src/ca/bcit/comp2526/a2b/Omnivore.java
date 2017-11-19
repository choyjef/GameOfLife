package ca.bcit.comp2526.a2b;

import java.awt.Color;

public class Omnivore extends Lifeform {
    
    /**
     * The days without eating an Omnivore can exist for.
     */
    private static final int OMNIVORE_HUNGER = 2;
    
    /**
     * The number of adjacent empty spaces required for reproduction.
     */
    private static final int OMNI_SPACE_REQ = 3;
    
    /**
     * The number of adjacent mates required for reproduction.
     */
    private static final int OMNI_MATES_REQ = 1;
    
    /**
     * The number of adjacent spaces containing food required for reproduction.
     */
    private static final int OMNI_FOOD_REQ = 3;

    public Omnivore(Cell location) {
        super(location);
        setColor(Color.blue);
        setHunger(OMNIVORE_HUNGER);
        setSpaceRequired(OMNI_SPACE_REQ);
        setMatesRequired(OMNI_MATES_REQ);
        setFoodRequired(OMNI_FOOD_REQ);
    }

    @Override
    public boolean isEdible(Herbivore herbivore) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEdible(Plant plant) {
        // TODO Auto-generated method stub
        return false;
    }

    

    @Override
    public boolean isEdible(Omnivore omnivore) {
        return false;
    }

    @Override
    public boolean isEdible(Carnivore carnivore) {
        return true;
    }

    @Override
    public boolean isEdible(Edible edible) {
        return false;
    }

    @Override
    Lifeform giveBirth(Cell location) {
        
        return new Omnivore(location);
    }
    
    @Override
    void resetHunger() {
        setHunger(OMNIVORE_HUNGER);
        
    }

}
