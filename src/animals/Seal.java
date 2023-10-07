package animals;

public class Seal extends Animal{
    public Seal(String name) {
        animalName = name;
    }

    public boolean isCompatibleWith(Animal animal) {
        if (animal instanceof Seal || animal instanceof Starfish) {
            return true;
        }
        else {
            return false;
        }
    }
}
