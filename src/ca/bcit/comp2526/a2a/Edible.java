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
    
    boolean isEdible(Herbivore herbivore);

    boolean isEdible(Plant plant);
}
