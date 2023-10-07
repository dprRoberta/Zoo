package animals;

public class Buzzard extends Animal{
    public Buzzard(String name) {
        animalName = name;
    }

    public boolean isCompatibleWith(Animal animal) {
        if (animal instanceof Buzzard) {
            return true;
        }
        else {
            return false;
        }
    }
}
