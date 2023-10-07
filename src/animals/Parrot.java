package animals;

public class Parrot extends Animal{
    public Parrot(String name) {
        animalName = name;
    }

    public boolean isCompatibleWith(Animal animal) {
        if (animal instanceof Parrot) {
            return true;
        }
        else {
            return false;
        }
    }
}
