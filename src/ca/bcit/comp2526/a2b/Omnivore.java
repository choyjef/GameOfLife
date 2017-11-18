package ca.bcit.comp2526.a2b;

import java.awt.Color;

public class Omnivore extends Lifeform {
    
    /**
     * The days without eating an Omnivore can exist for.
     */
    private static final int HUNGER_COUNTER_OMNIVORE = 10;

    public Omnivore(Cell location) {
        super(location);
        setColor(Color.blue);
        setHunger(HUNGER_COUNTER_OMNIVORE);

    }

    @Override
    public boolean isEdible(Herbivore herbivore) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEdible(Plant plant) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    void init() {
        getLocation().setColor(this.getColor());

    }

    @Override
    void takeAction() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isEdible(Omnivore omnivore) {
        return false;
    }

    @Override
    public boolean isEdible(Carnivore carnivore) {
        return true;
    }

    @Override
    public boolean isEdible(Lifeform lifeform) {
        return false;
    }

}
