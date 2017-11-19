package ca.bcit.comp2526.a2b;

import java.awt.Color;

public class Carnivore extends Lifeform {
    
    /**
     * The number of days without eating an Carnivore can exist for.
     */
    private static final int CARNIVORE_HUNGER = 7;
    
    /**
     * The number of adjacent empty spaces required for reproduction.
     */
    private static final int CARN_SPACE_REQ = 2;
    
    /**
     * The number of adjacent mates required for reproduction.
     */
    private static final int CARN_MATES_REQ = 1;
    
    /**
     * The number of adjacent spaces containing food required for reproduction.
     */
    private static final int CARN_FOOD_REQ = 2;

    public Carnivore(Cell location) {
        super(location);
        setColor(Color.magenta);
        setHunger(CARNIVORE_HUNGER);
        setSpaceRequired(CARN_SPACE_REQ);
        setMatesRequired(CARN_MATES_REQ);
        setFoodRequired(CARN_FOOD_REQ);
    }

    @Override
    public boolean isEdible(Herbivore herbivore) {
        return false;
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
        
        return new Carnivore(location);
    }

    @Override
    void resetHunger() {
        setHunger(CARNIVORE_HUNGER);
        
    }
}
