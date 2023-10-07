package animals;

public class Starfish extends Animal{
    public Starfish(String name) {
        animalName = name;
    }

    public boolean isCompatibleWith(Animal animal) {
        if (animal instanceof Seal || animal instanceof Starfish || animal instanceof Shark) {
            return true;
        }
        else {
            return false;
        }
    }
}
