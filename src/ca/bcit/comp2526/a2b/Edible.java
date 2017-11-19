package ca.bcit.comp2526.a2b;

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
    
    /**
     * Checks whether this object is edible by an omnivore.
     * @param omnivore
     *          The object attempting to eat this object.
     * @return
     *          True if this object is edible by the omnivore parameter
     *          object.
     */
    boolean isEdible(Omnivore omnivore);
    
    /**
     * Checks whether this object is edible by a carnivore.
     * @param carnivore
     *          The object attempting to eat this object.
     * @return
     *          True if this object is edible by the carnivore parameter
     *          object.
     */
    boolean isEdible(Carnivore carnivore);
    
 //   boolean isEdible(Edible edible);
    
}
