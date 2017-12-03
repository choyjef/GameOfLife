package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * A Carnivore type Lifeform that eats Herbivores and Omnivores.
 * 
 * @author Jeffrey
 * @version 2017-11-19
 */
public class Carnivore extends Lifeform {
    
    private static final long serialVersionUID = -8787895711500761383L;

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

    /**
     * Creates a Carnivore object.
     * 
     * @param location
     *          the carnivore's starting position.
     */
    public Carnivore(Cell location) {
        super(location);
        setColor(Color.magenta);
        setHunger(CARNIVORE_HUNGER);
        setType(LifeformType.CARNIVORE);
        setSpaceRequired(CARN_SPACE_REQ);
        setMatesRequired(CARN_MATES_REQ);
        setFoodRequired(CARN_FOOD_REQ);
    }

    @Override
    public boolean isEdible(Lifeform food) {
        LifeformType t = food.getType();
        
        return (t == LifeformType.OMNIVORE || t == LifeformType.HERBIVORE);
    }

    @Override
    Lifeform giveBirth(Cell birthLocation) {
        
        return new Carnivore(birthLocation);
    }

    @Override
    void resetHunger() {
        setHunger(CARNIVORE_HUNGER);
        
    }
}
