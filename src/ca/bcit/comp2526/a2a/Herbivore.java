package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Herbivore extends Animal {

    public Herbivore(Cell location) {
        super(location);
        setColor(Color.yellow);
        setHunger(10);
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        
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
    
    private void move() {
        Cell currentLocation = getLocation();
        Cell destination = findFood(currentLocation.getNeighbours());
        
        currentLocation.setInhabitant(new Blank(currentLocation));
        setLocation(destination);
        destination.setInhabitant(this);
        destination.init();
    }
    
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
            setHunger(10);
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
