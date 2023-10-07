package animals;

public class Gazelle extends Animal{
    public Gazelle(String name) {
        animalName = name;
    }

    public boolean isCompatibleWith(Animal animal) {
        if (animal instanceof Zebra || animal instanceof Gazelle) {
            return true;
        }
        else {
            return false;
        }
    }
}
