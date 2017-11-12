package ca.bcit.comp2526.a2b;

/**
 * Abstract class of an Animal object.
 * 
 * @author Jeffrey Choy
 * @version 2017-11-04
 */
public abstract class Animal extends Content implements Organism {

    /**
     * The amount of days this Animal can exist for without eating.
     */
    private int hunger;

    /**
     * Constructs an Animal object.
     *  
     * @param location
     *              the current Cell in which this object exists
     */
    public Animal(Cell location) {
        super(location);
    }

    /**
     * Returns the current hunger level of this Animal.
     * 
     * @return the hunger
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Sets the current hunger level of this Animal.
     * 
     * @param hunger
     *            the hunger to set
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    /**
     * Kills the current Animal object.
     */
    public void die() {
        getLocation().setInhabitant(new Blank(getLocation()));
    }

}
