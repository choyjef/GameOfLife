package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * An Omnivore type Lifeform that can eat herbivores, carnivores or plants.
 * 
 * @author Jeffrey
 * @version 2017-12-02
 */
public class Omnivore extends Lifeform {
    
    /**
     * Generated SerialVersionUID.
     */
    private static final long serialVersionUID = 5951986305101803485L;

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

    /**
     * Creates an Omnivore type object.
     * 
     * @param location
     *              the Omnivore's starting location
     */
    public Omnivore(Cell location) {
        super(location);
        setColor(Color.blue);
        setHunger(OMNIVORE_HUNGER);
        setType(LifeformType.OMNIVORE);
        setSpaceRequired(OMNI_SPACE_REQ);
        setMatesRequired(OMNI_MATES_REQ);
        setFoodRequired(OMNI_FOOD_REQ);
    }

    @Override
    public boolean isEdible(Lifeform food) {
        LifeformType t = food.getType();
        
        return (t == LifeformType.PLANT || t == LifeformType.HERBIVORE
                || t == LifeformType.CARNIVORE);
    }

    @Override
    Lifeform giveBirth(Cell birthLocation) {
        
        return new Omnivore(birthLocation);
    }
    
    @Override
    void resetHunger() {
        setHunger(OMNIVORE_HUNGER);
        
    }

}
