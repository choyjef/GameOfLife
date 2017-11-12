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
public class Herbivore extends Animal {
    
    /**
     * The days without eating an Herbivore can exist for.
     */
    private static final int HUNGER_COUNTER_DEFUALT = 10;

    /**
     * Constructs an Herbivore object.
     * @param location
     *          The initial location the Herbivore exists in.
     */
    public Herbivore(Cell location) {
        super(location);
        setColor(Color.yellow);
        setHunger(HUNGER_COUNTER_DEFUALT);
        setType(ContentType.HERBIVORE);
    }

    @Override
    public void init() {
        getLocation().setColor(Color.yellow);

    }

    @Override
    public void takeAction() {
        if (getHunger() <= 0) {
            die();
            return;
        }
        move();
        setActionTaken(true);
    }

    /**
     * Moves the Herbivore into an empty neighbouring Cell, preferring Cells
     *      with food (plants), and if there is a plant in the destination Cell
     *      will kill that object. 
     */
    private void move() {
        Cell currentLocation = getLocation();
        Cell destination = findFood(currentLocation.getNeighbours());

        currentLocation.setInhabitant(new Blank(currentLocation));
        setLocation(destination);
        destination.setInhabitant(this);
        destination.init();
    }

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
            setHunger(HUNGER_COUNTER_DEFUALT);
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

}
