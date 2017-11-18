package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract class of an Animal object.
 * 
 * @author Jeffrey Choy
 * @version 2017-11-04
 */
public abstract class Lifeform implements Edible {

    /**
     * The current Cell location this object exists in.
     */
    private Cell location;
    
    /**
     * The color associated with this object.
     */
    private Color color;
    
    /**
     * The amount of days this Lifeform can exist for without eating.
     */
    private int hunger;
    
    /**
     * Flag for whether this object has performed an action this turn.
     */
    private boolean actionTaken;
    
    
    /**
     * Constructs a Lifeform object.
     *  
     * @param location
     *              the current Cell in which this object exists
     */
    public Lifeform(Cell location) {
        this.location = location;
    }

    /**
     * Returns the current hunger level of this Lifeform.
     * 
     * @return the hunger
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Sets the current hunger level of this Lifeform.
     * 
     * @param hunger
     *            the hunger to set
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
    
    private void resetHunger() {
        setHunger(0);
    }
    
    /**
     * Returns the colour associated with the Content type.
     * 
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color related to this Content type.
     * 
     * @param color
     *            the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks whether this object has taken an action this turn.
     * 
     * @return the actionTaken
     */
    public boolean isActionTaken() {
        return actionTaken;
    }

    /**
     * Sets the actionTaken flag.
     * 
     * @param actionTaken
     *            the actionTaken to set
     */
    public void setActionTaken(boolean actionTaken) {
        this.actionTaken = actionTaken;
    }

    /**
     * Gets the current Cell that this object exists in.
     * 
     * @return the current Cell location of object
     */
    public Cell getLocation() {
        return location;
    }

    /**
     * Sets the current Cell that this object exists in.
     * 
     * @param location
     *            the location to set
     */
    public void setLocation(Cell location) {
        this.location = location;
    }
    
    
    
    public void takeAction() {
        if (getHunger() <= 0) {
            die();
            return;
        }
        move();
        setActionTaken(true);
    }

    /**
     * Kills the current Lifeform object.
     */
    public void die() {
        setActionTaken(true);
        getLocation().setInhabitant(null);
    }
    
    public void move() {
        Cell currentLocation = location;
        Cell destination = choosePosition(currentLocation.getNeighbours());

        currentLocation.setInhabitant(null);
        setLocation(destination);
        if (destination.getInhabitant().isEdible(this)) {
            eat(destination.getInhabitant());
        }
        destination.setInhabitant(this);
        destination.init();
        
    }
    
    public void updateHealth() {
        
    }
    
    public void reproduce() {
        
    }
    
    public Cell choosePosition(Cell[] searchArea) {
        
        List<Cell> foodLocations = new ArrayList<Cell>();
        Random numberGenerator = new Random();
        
        //check if neighbours edible, if so add to foodlocations
        for (int i = 0; i < searchArea.length; i++) {
            if (searchArea[i].getInhabitant().isEdible(this)) {
                foodLocations.add(searchArea[i]);
            }
        }
        
        // if there is no neighbouring food, wander aimlessly
        if (foodLocations.size() == 0) {
            int randNum = numberGenerator.nextInt(searchArea.length);
            while (!searchArea[randNum].isEmpty()) {
                randNum = numberGenerator.nextInt(searchArea.length);
            }
            setHunger(getHunger() - 1);
            return searchArea[randNum];
        } else {
            //if there is food, randomly select one
            int randNum = numberGenerator.nextInt(foodLocations.size());
            resetHunger();
            return foodLocations.get(randNum);
        }
    }
    
    protected void eat(Lifeform food) {
        food.die();
        resetHunger(); 
    }
    
    /**
     * Sets the background colour of the cell.
     */
    abstract public void init(); //TODO: possibly may want to provide implementation on this level

}
