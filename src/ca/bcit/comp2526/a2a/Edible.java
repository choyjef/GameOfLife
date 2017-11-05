package ca.bcit.comp2526.a2a;

/**
 * An interface to determine whether an object can be eaten
 * by another object.
 * 
 * @author Jeffrey
 * @version 2017-11-04
 *
 */
public interface Edible {
    
    /**
     * Checks whether this object is edible by a Herbivore.
     * @param herbivore
     *          The object attempting to eat this object.
     * @return
     *          True if this object is edible by the herbivore parameter
     *          object.
     */
    boolean isEdible(Herbivore herbivore);
    
    /**
     * Checks whether this object is edible by a plant.
     * @param plant
     *          The object attempting to eat this object.
     * @return
     *          True if this object is edible by the plant parameter
     *          object.
     */
    boolean isEdible(Plant plant);
}
