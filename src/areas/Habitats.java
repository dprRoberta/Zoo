package areas;

import animals.*;

import java.util.ArrayList;

public abstract class Habitats implements IArea{

    public int maxCapacity;
    public ArrayList<Integer> adjacentAreas = new ArrayList<>();

    // A method to check if the animal being added to the habitat can be in that habitat
    public abstract boolean isCorrectHabitat(Animal animal);

    // A method to check if the habitat is not full
    public boolean isNotFull(int currentCapacity) {
        if (currentCapacity < maxCapacity) {
            return true;
        }
        else {
            return false;
        }
    }


    public ArrayList<Integer> getAdjacentAreas() {
        return adjacentAreas;
    }

    public void addAdjacentArea(int areaId) {
        adjacentAreas.add(areaId);
    }

    public void removeAdjacentArea(int areaId) {

    }


}
