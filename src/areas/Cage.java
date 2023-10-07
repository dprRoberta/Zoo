package areas;

import animals.*;

public class Cage extends Habitats implements IArea{


    public Cage(int capacity) {
        maxCapacity = capacity;
    }

    public boolean isCorrectHabitat(Animal animal) {
        if (animal instanceof Buzzard || animal instanceof Parrot) {
            return true;
        }
        else {
            return false;
        }
    }
}
