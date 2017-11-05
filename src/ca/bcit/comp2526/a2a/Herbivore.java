package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
        if (getHunger() >= 0) {
            die();
        }
        
        
    }
    
    private Cell move() {
        
    }
    
    private Cell findFood(Cell[] searchArea) {
        
        for (int i = 0; i < searchArea.length; i++) {
            
        }
        
    }
    
}
