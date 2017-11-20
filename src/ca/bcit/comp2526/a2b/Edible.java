package ca.bcit.comp2526.a2b;

/**
 * An interface to determine whether an object can be eaten
 * by another object.
 * 
 * @author Jeffrey
 * @version 2017-11-19
 *
 */
public interface Edible {
    
    
    /**
     * Checks whether the object passed as the food parameter
     * is edible according to this creature.
     * 
     * @param food
     *          lifeform this object is trying to eat
     * @return
     *          true if the object is edible
     */
    boolean isEdible(Lifeform food);
    
}
