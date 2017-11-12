package ca.bcit.comp2526.a2b;

import java.awt.Color;

public class Carnivore extends Animal {
    
    /**
     * The days without eating a Carnivore can exist for.
     */
    private static final int HUNGER_COUNTER_CARNIVORE = 10;

    public Carnivore(Cell location) {
        super(location);
        setColor(Color.magenta);
        setHunger(HUNGER_COUNTER_CARNIVORE);
        setType(ContentType.HERBIVORE);
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

}
