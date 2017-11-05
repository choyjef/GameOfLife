package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Plant object that exists only in a Cell, and seeds new Cells given certain
 * conditions.
 * 
 * @author Jeffrey
 * @version 2017-11-04
 */
public class Plant extends Content implements Organism {
    
    /**
     * The requisite amount of surrounding partners for seeding.
     */
    private static final int PARTNER_REQ = 2;
    
    /**
     * The requisite amount of surround empty Cells for seeding.
     */
    private static final int EMPTY_REQ = 3;

    /**
     * Constructs a Plant object.
     * 
     * @param location
     *              The initial Cell location the Plant exists in.
     */
    public Plant(Cell location) {
        super(location);
        setColor(Color.green);
        setType(ContentType.PLANT);
    }

    @Override
    public void init() {
        getLocation().setColor(Color.green);

    }

    @Override
    public void takeAction() {

        if (crossPollinate(getLocation().getNeighbours())) {
            seed();
        }
        setActionTaken(true);

    }

    /**
     * Checks whether the Plants surroundings allow for it to seed a new Plant.
     * 
     * @param searchArea
     *          The area to check for seeding conditions (usually neighbours)
     * @return
     *          True if the searchArea contains the right conditions for seeding
     */
    protected boolean crossPollinate(Cell[] searchArea) {

        int partners = 0;
        int seedLocationCount = 0;

        for (int i = 0; i < searchArea.length; i++) {
            if (getType() == searchArea[i].getInhabitant().getType()) {
                partners++;
            } else if (searchArea[i].isEmpty()) {
                seedLocationCount++;
            }
        }

        return (partners >= PARTNER_REQ && seedLocationCount >= EMPTY_REQ);

    }

    /**
     * Creates a new plant object in a randomly selected empty Cell.
     */
    protected void seed() {
        Cell seedDestination = seedDestination(getLocation().getNeighbours());

        seedDestination.setInhabitant(new Plant(seedDestination));
        seedDestination.init();
        seedDestination.getInhabitant().setActionTaken(true);

    }

    /**
     * Randomly selects an Empty Cell from the provided Cell array
     *      into which the Plant will seed (create a new Plant).
     *      
     * @param searchArea
     *      the collection of Cells to randomly select from
     * @return
     *      a randomly selected empty Cell location.
     */
    protected Cell seedDestination(Cell[] searchArea) {

        Random numberGenerator = new Random();
        ArrayList<Cell> destinations = new ArrayList<Cell>();
        int randNum;

        for (int i = 0; i < searchArea.length; i++) {
            if (searchArea[i].getInhabitant().getType() == ContentType.BLANK) {
                destinations.add(searchArea[i]);
            }
        }

        randNum = numberGenerator.nextInt(destinations.size());
        return destinations.get(randNum);

    }

    @Override
    public boolean isEdible(Herbivore herbivore) {
        return true;
    }

    @Override
    public boolean isEdible(Plant plant) {
        return false;
    }
}
