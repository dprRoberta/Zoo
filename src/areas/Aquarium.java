package areas;

import animals.*;

public class Aquarium extends Habitats implements IArea{

    public Aquarium(int capacity) {
        maxCapacity = capacity;
    }

    public boolean isCorrectHabitat(Animal animal) {
        if (animal instanceof Shark || animal instanceof Seal || animal instanceof Starfish) {
            return true;
        } else {
            return false;
        }
    }
}

