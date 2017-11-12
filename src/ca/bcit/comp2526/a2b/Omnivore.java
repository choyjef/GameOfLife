package ca.bcit.comp2526.a2b;

import java.awt.Color;

public class Omnivore extends Animal {
    
    /**
     * The days without eating an Omnivore can exist for.
     */
    private static final int HUNGER_COUNTER_OMNIVORE = 10;

    public Omnivore(Cell location) {
        super(location);
        setColor(Color.blue);
        setHunger(HUNGER_COUNTER_OMNIVORE);
        setType(ContentType.HERBIVORE);
        // TODO Auto-generated constructor stub
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
