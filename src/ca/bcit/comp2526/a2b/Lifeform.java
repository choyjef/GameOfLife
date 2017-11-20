package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract class of a Lifeform object.
 * 
 * @author Jeffrey Choy
 * @version 2017-11-19
 */
public abstract class Lifeform implements Edible {

    /**
     * The current Cell location this object exists in.
     */
    private Cell location;
    
    /**
     * The color associated with this lifeform.
     */
    private Color color;
    
    /**
     * The amount of days this Lifeform can exist for without eating.
     */
    private int hunger;
    
    /**
     * Flag for whether a life form has eaten this turn.
     */
    private boolean justAte;
    
    /**
     * Flag for whether this object has performed an action this turn.
     */
    private boolean actionTaken;
    
    /**
     * The number of adjacent empty spaces required for reproduction.
     */
    private int spaceRequired;
    
    /**
     * The number of adjacent mates required for reproduction.
     */
    private int matesRequired;
    
    /**
     * The number of adjacent spaces containing food required for reproduction.
     */
    private int foodRequired;
    
    /**
     * An enum identifier for the type of lifeform this object is.
     */
    private LifeformType type;  
    
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
     * Sets the hunger level of the lifeform back to its default value.
     */
    abstract void resetHunger();
    
    /**
     * Births a lifeform of this lifeform's type at the specified location.
     * 
     * @param birthLocation
     *              the starting location of the new lifeform
     * @return
     *          the new lifeform
     */
    abstract Lifeform giveBirth(Cell birthLocation);
    
    /**
     * If meeting requirements: move, eat, reproduce, and/or die this turn.
     */
    public void takeAction() {
        if (!isDead()) {
            move();
        }
        if (!isDead()) {
            reproduce();
        }
        setActionTaken(true);
        updateHealth();
    }
    
    /**
     * Chooses a location cell and moves into it and eats if possible.
     */
    public void move() {
        Cell currentLocation = location;
        Cell destination = chooseMovePosition();
        
        if (currentLocation == destination) {
            return;
        }
        
        currentLocation.setInhabitant(null);
        currentLocation.init();
        setLocation(destination);
        if (destination.getInhabitant() == null) {
            justAte = false;
        } else if (isEdible(destination.getInhabitant())) {
            eat(destination.getInhabitant());
        }
        destination.setInhabitant(this);
        destination.init(); 
    }
    
    /**
     * Selects a neighbouring Cell for the object to move to, preferring 
     * Cells with food.
     * 
     * @return
     *      the selected cell to move to.
     */
    public Cell chooseMovePosition() {
        
        // collects cells neighbouring current location
        Cell[] searchArea = location.getNeighbours();
        
        // two collections for food cells and empty cells
        List<Cell> foodLocations = new ArrayList<Cell>();
        List<Cell> emptyLocations = new ArrayList<Cell>();
        Random numberGenerator = new Random();
        
        
        // checks if neighbours empty or edible, if so add to emptylocations
        // or foodlocations respectively
        for (int i = 0; i < searchArea.length; i++) {
            Lifeform l = searchArea[i].getInhabitant();
            if (l == null) { 
                emptyLocations.add(searchArea[i]);
            } else if (isEdible(l)) {
                foodLocations.add(searchArea[i]);
            }
        }
        
        // if no food or empty cells nearby, sit still
        if (emptyLocations.isEmpty() && foodLocations.isEmpty()) {
            return location;
        }
        
        // if there is no neighbouring food, wander aimlessly
        if (foodLocations.isEmpty()) {
            int randNum = numberGenerator.nextInt(emptyLocations.size());
            return emptyLocations.get(randNum);
        } else {
            //if there is food, randomly select one
            int randNum = numberGenerator.nextInt(foodLocations.size());
            return foodLocations.get(randNum);
        }
    }
    
    /**
     * Eats the lifeform passed in. Kills the food
     * and sets the eaten flag.
     * 
     * @param food
     *          object to be eaten
     */
    protected void eat(Lifeform food) {
        food.die();
        justAte = true; 
    }
    
    /**
     * Checks whether this Lifeform has died of starvation.
     * 
     * @return
     *          true if lifeform hasn't eaten in time
     */
    protected boolean isDead() {
        return this.hunger <= 0; 
    }

    /**
     * Kills the current Lifeform object.
     */
    public void die() {
        setActionTaken(true);
        location.setInhabitant(null);
        location.init();
        this.location = null;
    }
    
    /**
     * Checks whether the Lifeform has eaten this turn and either
     * resets hunger or decrease the hunger counter and kills the
     * creature if it has died of starvation.
     */
    public void updateHealth() {
        if (justAte) {
            resetHunger();
        } else {
            hunger -= 1;
        }
        
        // reset flag
        this.justAte = false;
        
        // kills lifeform if it has died of starvation
        if (isDead()) {
            die();
        }
    }
    
    /**
     * Checks conditions for reproduction and if passed creates
     * a new Lifeform object at random potential location.
     */
    public void reproduce() {
        // check conditions for reproduction
        if (moodIsRight(getMatesRequired(), getSpaceRequired(), 
                getFoodRequired())) {
            Cell birthDestination = chooseBirthDestination();
            
            // polymorphism!
            Lifeform l = giveBirth(birthDestination);
            birthDestination.setInhabitant(l);
            birthDestination.init();
            
        }
    }
    
    /**
     * Checks conditions of current location for reproduction.
     * 
     * @param numberMatesReq
     *          number of nearby mating partners required to reproduce
     * @param numberEmptyReq
     *          number of neighbouring empty cells required to reproduce
     * @param numberFoodReq
     *          number of neighbouring food cells required  to reproduce
     * @return
     *          true if conditions met for reproduction
     */
    protected boolean moodIsRight(int numberMatesReq, int numberEmptyReq, 
            int numberFoodReq) {
        
        Cell[] searchArea = location.getNeighbours();
        int partnersCount = 0;
        int birthLocationCount = 0;
        int foodCount = 0;

        // searches Cells for empty cells, cells with food and partners
        for (int i = 0; i < searchArea.length; i++) {
            Lifeform l = searchArea[i].getInhabitant();
            
            if (searchArea[i].isEmpty()) {
                birthLocationCount++;
            } else if (this.type == l.getType()) {
                partnersCount++;
            } else if (isEdible(l)) {
                foodCount++;
            }
        }
        
        return (partnersCount >= numberMatesReq 
                && birthLocationCount >= numberEmptyReq 
                && foodCount >= numberFoodReq);
        
    }
    
    /**
     * Select a random neighbouring cell to birth a new Lifeform.
     * 
     * @return
     *          the selected cell for the new Lifeform object
     */
    protected Cell chooseBirthDestination() {
        Cell[] searchArea = location.getNeighbours();
        List<Cell> birthLocations = new ArrayList<Cell>();
        Random numberGenerator = new Random();
        int randNum;
        
        // check if neighbours empty, if so add to list of potential 
        // birth locations
        for (int i = 0; i < searchArea.length; i++) {
            if (searchArea[i].isEmpty()) {
                birthLocations.add(searchArea[i]);
            }
        }
        
        // randomly select from potential birth locations
        randNum = numberGenerator.nextInt(birthLocations.size());
        return birthLocations.get(randNum);
    } 
    
    /**
     * Sets the background colour of the cell.
     */
    public void init() {
        location.setColor(this.color);
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
     * Gets the number of neighbouring empty cells required
     * for this Lifeform to reproduce.
     * @return the spaceRequired
     */
    public int getSpaceRequired() {
        return spaceRequired;
    }

    /**
     * Sets the number of neighbouring empty cells required
     * for this Lifeform to reproduce.
     * 
     * @param spaceRequired the spaceRequired to set
     */
    public void setSpaceRequired(int spaceRequired) {
        this.spaceRequired = spaceRequired;
    }

    /**
     * Gets the number of neighbouring cells with food required
     * for this Lifeform to reproduce.
     * 
     * @return the foodRequired
     */
    public int getFoodRequired() {
        return foodRequired;
    }

    /**
     * Sets the number of neighbouring cells with food required
     * for this Lifeform to reproduce.
     * 
     * @param foodRequired the foodRequired to set
     */
    public void setFoodRequired(int foodRequired) {
        this.foodRequired = foodRequired;
    }

    /**
     * Gets the number of neighbouring mates required
     * for this Lifeform to reproduce.
     * 
     * @return the matesRequired
     */
    public int getMatesRequired() {
        return matesRequired;
    }

    /**
     * Sets the number of neighbouring mates required
     * for this Lifeform to reproduce.
     * 
     * @param matesRequired the matesRequired to set
     */
    public void setMatesRequired(int matesRequired) {
        this.matesRequired = matesRequired;
    }
    
    /**
     * Checks whether this Lifeform has eaten this turn.
     * @return
     *      true if this Lifeform has eaten an object this turn.
     */
    public boolean isJustAte() {
        return justAte;
    }

    /**
     * Sets whether this Lifeform has eaten this turn.
     * 
     * @param justAte
     *          true if eaten, false if resetting
     */
    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }

    /**
     * The enumerated type identifier for this type of Lifeform.
     * 
     * @return the type
     */
    public LifeformType getType() {
        return type;
    }

    /**
     * Set the enumerated type identifier for this type of Lifeform.
     * 
     * @param type the type to set
     */
    public void setType(LifeformType type) {
        this.type = type;
    }

}
