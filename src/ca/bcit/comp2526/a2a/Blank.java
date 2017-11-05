package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Blank extends Content {

    public Blank(Cell location) {
        super(location);
        setColor(Color.white);
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public void takeAction() {
        // Blank Cells do nothing for their action.
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
