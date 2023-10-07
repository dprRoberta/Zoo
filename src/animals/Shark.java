package animals;

public class Shark extends Animal{
    public Shark(String name) {
        animalName = name;
    }

    public boolean isCompatibleWith(Animal animal) {
        if (animal instanceof Shark || animal instanceof Starfish) {
            return true;
        }
        else {
            return false;
        }
    }

}
