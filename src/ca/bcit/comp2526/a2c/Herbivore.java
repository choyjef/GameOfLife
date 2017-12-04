package ca.bcit.comp2526.a2c;

import java.awt.Color;


/**
 * A Herbivore type Lifeform that eats Plants and dies.
 * 
 * @author Jeffrey
 * @version 2017-12-02
 */
public class Herbivore extends Lifeform {
    
    /**
     * Generated SerialVersionUID.
     */
    private static final long serialVersionUID = 8041465461861396386L;

    /**
     * The number of days without eating an Herbivore can exist for.
     */
    private static final int HERBIVORE_HUNGER = 10;
    
    /**
     * The number of adjacent empty spaces required for reproduction.
     */
    private static final int HERB_SPACE_REQ = 1;
    
    /**
     * The number of adjacent mates required for reproduction.
     */
    private static final int HERB_MATES_REQ = 2;
    
    /**
     * The number of adjacent spaces containing food required for reproduction.
     */
    private static final int HERB_FOOD_REQ = 2;

    /**
     * Creates an Herbivore object.
     * 
     * @param location
     *          the herbivore's starting position.
     */
    public Herbivore(Cell location) {
        super(location);
        setColor(Color.yellow);
        setType(LifeformType.HERBIVORE);
        setHunger(HERBIVORE_HUNGER);
        setSpaceRequired(HERB_SPACE_REQ);
        setMatesRequired(HERB_MATES_REQ);
        setFoodRequired(HERB_FOOD_REQ);
    }

    @Override
    public boolean isEdible(Lifeform food) {
        LifeformType t = food.getType();
        
        return (t == LifeformType.PLANT);
        
    }

    @Override
    Lifeform giveBirth(Cell birthLocation) {
        
        return new Herbivore(birthLocation);
    }


    @Override
    void resetHunger() {
        setHunger(HERBIVORE_HUNGER);
        
    }

}
