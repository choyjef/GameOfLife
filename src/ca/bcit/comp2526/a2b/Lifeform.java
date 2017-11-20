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
     * Indicates whether the lifeform has just eaten.
     */
    private boolean justAte;
    
    /**
     * Flag for whether this object has performed an action this turn.
     */
    private boolean actionTaken;
    
    private int matesRequired;
    
    private int foodRequired;
    
    private int spaceRequired;
    
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

    abstract void resetHunger();
    
    abstract Lifeform giveBirth(Cell location);
    
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
    
    protected boolean isDead() {
        return this.hunger <= 0; 
    }

    /**
     * Kills the current Lifeform object.
     */
    public void die() {
        setActionTaken(true);
        location.setInhabitant(null);
        location.update();
    }
    
    public void move() {
        Cell currentLocation = location;
        Cell destination = chooseMovePosition();
        
        if (currentLocation == destination) {
            return;
        }
        
        currentLocation.setInhabitant(null);
        currentLocation.update();
        setLocation(destination);
        if (destination.getInhabitant() == null) {
            justAte = false;
        } else if (isEdible(destination.getInhabitant())) {
            eat(destination.getInhabitant());
        }
        destination.setInhabitant(this);
        destination.init(); 
    }
    
    public Cell chooseMovePosition() {
        
        Cell[] searchArea = location.getNeighbours();
        
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
    
    protected void eat(Lifeform food) {
        food.die();
        justAte = true; 
    }
    
    public void updateHealth() {
        if (justAte) {
            resetHunger();
        } else {
            hunger -= 1;
        }
        
        this.justAte = false;
        
        if (isDead()) {
            die();
        }
    }
    
    public void reproduce() {
        if (moodIsRight(getMatesRequired(), getSpaceRequired(), 
                getFoodRequired())) {
            Cell birthDestination = chooseBirthDestination();
            
            Lifeform l = giveBirth(birthDestination);
            birthDestination.setInhabitant(l);
            birthDestination.init();
            birthDestination.getInhabitant().setActionTaken(true);
            
        }
    }
    
    protected boolean moodIsRight(int numberMatesReq, int numberEmptyReq, int numberFoodReq) {
        
        Cell[] searchArea = location.getNeighbours();
        int partnersCount = 0;
        int birthLocationCount = 0;
        int foodCount = 0;

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
    
    protected Cell chooseBirthDestination() {
        Cell[] searchArea = location.getNeighbours();
        List<Cell> birthLocations = new ArrayList<Cell>();
        Random numberGenerator = new Random();
        int randNum;
        
        System.out.print("chooseBirthDestination for cell:");
        location.draw();
        // check if neighbours empty, if so add to list of potential 
        // birth locations
        for (int i = 0; i < searchArea.length; i++) {
            if (searchArea[i].isEmpty()) {
                birthLocations.add(searchArea[i]);
                System.out.print("Added to birthlocations: ");
                searchArea[i].draw();
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
     * @return the spaceRequired
     */
    public int getSpaceRequired() {
        return spaceRequired;
    }

    /**
     * @param spaceRequired the spaceRequired to set
     */
    public void setSpaceRequired(int spaceRequired) {
        this.spaceRequired = spaceRequired;
    }

    /**
     * @return the foodRequired
     */
    public int getFoodRequired() {
        return foodRequired;
    }

    /**
     * @param foodRequired the foodRequired to set
     */
    public void setFoodRequired(int foodRequired) {
        this.foodRequired = foodRequired;
    }

    /**
     * @return the matesRequired
     */
    public int getMatesRequired() {
        return matesRequired;
    }

    /**
     * @param matesRequired the matesRequired to set
     */
    public void setMatesRequired(int matesRequired) {
        this.matesRequired = matesRequired;
    }
    
    public boolean isJustAte() {
        return justAte;
    }

    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }

    /**
     * @return the type
     */
    public LifeformType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(LifeformType type) {
        this.type = type;
    }

}
