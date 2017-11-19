package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Herbivore object that exists only in a Cell, moves, eats Plants and dies.
 * 
 * @author Jeffrey
 * @version 2017-11-04
 */
public class Herbivore extends Lifeform {
    
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
     * Constructs an Herbivore object.
     * @param location
     *          The initial location the Herbivore exists in.
     */
    public Herbivore(Cell location) {
        super(location);
        setColor(Color.yellow);
        setHunger(HERBIVORE_HUNGER);
        setSpaceRequired(HERB_SPACE_REQ);
        setMatesRequired(HERB_MATES_REQ);
        setFoodRequired(HERB_FOOD_REQ);
    }


    /**
     * Moves the Herbivore into an empty neighbouring Cell, preferring Cells
     *      with food (plants), and if there is a plant in the destination Cell
     *      will kill that object. 
     */
    //private void move() {
//        Cell currentLocation = getLocation();
//        Cell destination = findFood(currentLocation.getNeighbours());
//
//        currentLocation.setInhabitant(new Blank(currentLocation));
//        setLocation(destination);
//        destination.setInhabitant(this);
//        destination.init();
   // }

    /**
     * Searches the provided Cell array for Cells containing food and randomly
     *      returns a Cell with food (if any found) or an empty cell.
     * 
     * @param searchArea
     *          array of Cells to search
     * @return
     *          randomly selected Cell with food (if any found) or a randomly
     *              selected empty Cell.
     */
    private Cell findFood(Cell[] searchArea) {


        List<Cell> foodLocations = new ArrayList<Cell>();
        Random numberGenerator = new Random();

        for (int i = 0; i < searchArea.length; i++) {
            if (searchArea[i].getInhabitant().isEdible(this)) {
                foodLocations.add(searchArea[i]);
            }
        }

        if (foodLocations.size() == 0) {
            int randNum = numberGenerator.nextInt(searchArea.length);
            while (!searchArea[randNum].isEmpty()) {
                randNum = numberGenerator.nextInt(searchArea.length);
            }
            setHunger(getHunger() - 1);
            return searchArea[randNum];
        } else {
            int randNum = numberGenerator.nextInt(foodLocations.size());
            setHunger(HERBIVORE_HUNGER);
            return foodLocations.get(randNum);
        }
    }

    @Override
    public boolean isEdible(Herbivore herbivore) {
        return false;
    }

    @Override
    public boolean isEdible(Plant plant) {
        return false;
    }

    @Override
    public boolean isEdible(Omnivore omnivore) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEdible(Carnivore carnivore) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEdible(Edible edible) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    Lifeform giveBirth(Cell location) {
        
        return new Herbivore(location);
    }


    @Override
    void resetHunger() {
        setHunger(HERBIVORE_HUNGER);
        
    }

}
