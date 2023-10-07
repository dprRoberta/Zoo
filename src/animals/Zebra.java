package animals;

public class Zebra extends Animal{
    public Zebra(String name) {
        animalName = name;
    }

    public boolean isCompatibleWith(Animal animal) {
        if (animal instanceof Gazelle || animal instanceof Zebra) {
            return true;
        }
        else {
            return false;
        }
    }
}
