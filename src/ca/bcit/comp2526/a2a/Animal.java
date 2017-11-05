package ca.bcit.comp2526.a2a;

public abstract class Animal extends Content implements Organism {
    
    private int hunger;

    public Animal(Cell location) {
        super(location);
    }

    /**
     * @return the hunger
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * @param hunger 
     *          the hunger to set
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
    
    public void die() {
        getLocation().setInhabitant(new Blank(getLocation()));
    }

}
