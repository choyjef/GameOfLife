package ca.bcit.comp2526.a2b;

import java.awt.Color;

public class Carnivore extends Lifeform {
    
    /**
     * The days without eating a Carnivore can exist for.
     */
    private static final int HUNGER_COUNTER_CARNIVORE = 10;

    public Carnivore(Cell location) {
        super(location);
        setColor(Color.magenta);
        setHunger(HUNGER_COUNTER_CARNIVORE);
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
    void init() {
        getLocation().setColor(this.getColor());

    }

    @Override
    public boolean isEdible(Omnivore omnivore) {
        return true;
    }

    @Override
    public boolean isEdible(Carnivore carnivore) {
        return false;
    }

    @Override
    public boolean isEdible(Lifeform lifeform) {
        return false;
    }

    @Override
    void takeAction() {
        // TODO Auto-generated method stub

    }

}
