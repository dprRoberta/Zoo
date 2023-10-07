package animals;

public class Lion extends Animal{
    public Lion(String name) {
        animalName = name;
    }

    public boolean isCompatibleWith(Animal animal) {
        if (animal instanceof Lion) {
            return true;
        }
        else {
            return false;
        }
    }
}
