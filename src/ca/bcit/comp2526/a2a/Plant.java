package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Plant extends Content {

    public Plant(Cell location) {
        super(location);
        setColor(Color.green);
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public void takeAction() {
        // TODO Auto-generated method stub

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
