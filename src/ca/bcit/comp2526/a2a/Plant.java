package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Jeffrey
 * @version 2017-11-04
 */
public class Plant extends Content implements Organism {

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
    
    protected boolean crossPollinate(Cell[] searchArea) {
        
        int partners = 0;
        int seedLocationCount = 0;
        
        
        for (int i = 0; i < searchArea.length; i++) {
            if (getType() == searchArea[i].getInhabitant().getType()) {
                partners++;
            } else if (searchArea[i].isEmpty()){
                seedLocationCount++;
            }
        }
        
        return (partners >= 2 && seedLocationCount >=3);
           
        
    }
    
    protected void seed() {
        Cell seedDestination = seedDestination(getLocation().getNeighbours());
        
        seedDestination.setInhabitant(new Plant(seedDestination));
        seedDestination.init();
        seedDestination.getInhabitant().setActionTaken(true);
        
    }
    
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
