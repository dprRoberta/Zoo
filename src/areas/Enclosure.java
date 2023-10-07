package areas;

import animals.*;

public class Enclosure extends Habitats implements IArea{

    public Enclosure(int capacity) {
        maxCapacity = capacity;
    }


    public boolean isCorrectHabitat(Animal animal) {
        if (animal instanceof Lion || animal instanceof Gazelle || animal instanceof Zebra) {
            return true;
        }
        else {
            return false;
        }
    }

}
