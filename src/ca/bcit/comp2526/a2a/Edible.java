package ca.bcit.comp2526.a2a;

public interface Edible {
    
    //Returns true if this object is edible by a herbivore
    public boolean isEdible(Herbivore herbivore);

    //Returns true if object is edible by a plant
    public boolean isEdible(Plant plant);
}
