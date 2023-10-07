package zoo;

import animals.*;
import areas.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Zoo implements IZoo{

    // ---------------- VARIABLES ----------------

    // Using a hashmap to store the different areas as values and their unique IDs as keys
    public HashMap<Integer, IArea> areas = new HashMap<>();
    // Using a hashmap to store the animals in each area in an array list as the values with their unique IDs as the keys
    public HashMap<Integer, ArrayList<Animal>> animals = new HashMap<>();
    // Using a hashmap to store the area IDs as keys, and an array list of the areas it's connected to as the values
    public HashMap<Integer, ArrayList<Integer>> connectedAreas = new HashMap<>();
    // An entrance area is always present
    public final Entrance entrance = new Entrance();
    // Used to generate a new ID for an area
    public int counter = 1;

    // ---------------- HELPER FUNCTIONS ----------------

    // Class constructor, which ensures that there is always an entrance with id = 0,
    // when an object of type zoo is created
    public Zoo() {
        areas.put(0, entrance);
        animals.put(0, new ArrayList<>());
        connectedAreas.put(0, new ArrayList<>());
    }


    // Generates a new ID for each added area
    public int generateIDs() {
        int newID = counter;
        counter += 1;
        return newID;
    }

    // Checks if an area is a habitat
    public boolean isHabitat(IArea area) {
        if (area instanceof Habitats) {
            return true;
        }
        else {
            return false;
        }
    }


    // ---------------- BASIC ----------------

    /**
     * Adds the given area to the zoo, unless it is already part of the zoo
     * or is a second entrance (which is not allowed).
     * @param area The area to be added.
     * @return An ID by which the added area can be uniquely identified or -1
     * if the area cannot be added.
     */
    public int addArea(IArea area) {
        for (IArea i : areas.values()) {
            if (area == i) {
                return -1;
            }
            else if (i instanceof Entrance && area instanceof Entrance) {
                return -1;
            }
        }
        int areaID = generateIDs();

        areas.put(areaID, area);
        animals.put(areaID, new ArrayList<>());
        connectedAreas.put(areaID, new ArrayList<>());

        return areaID;
    }

    /**
     * Removes the specified area from the zoo.
     * @param areaId The ID of the area to be removed.
     * @return Returns true if (and only if) an area was successfully removed.
     */
    public boolean removeArea(int areaId) {
        if (areaId == 0) {
            return false;
        }
        for (int i : areas.keySet()) {
            if (i == areaId) {
                areas.remove(areaId);
                animals.remove(areaId);
                connectedAreas.remove(areaId);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the area associated with the given ID.
     * @param areaId The ID of the area to be fetched.
     * @return The area corresponding to the given ID.
     */
    public IArea getArea(int areaId) {
        for (int i : areas.keySet()) {
            if (areaId == i) {
                return areas.get(areaId);
            }
        }
        return null;
    }

    /**
     * Tries to add the given animal to the specified area
     * @param areaId The ID of the area the animal is to be added to.
     * @param animal The animal to be added.
     * @return Returns a code indicating success or failure. See {@link Codes}.
     */
    public byte addAnimal(int areaId, Animal animal) {
        IArea area = areas.get(areaId);
        for (int i : animals.keySet()) {
            if (i == areaId) {
                if (isHabitat(area)) {
                    Habitats habitat = (Habitats) area;
                    if (habitat.isCorrectHabitat(animal)) {
                        if (habitat.isNotFull(animals.get(areaId).size())) {
                            for (int j = 0; j < animals.get(areaId).size(); j++) {
                                if (!animal.isCompatibleWith(animals.get(areaId).get(j))) {
                                    return Codes.INCOMPATIBLE_INHABITANTS;
                                }
                            }
                            animals.get(areaId).add(animal);
                            return Codes.ANIMAL_ADDED;
                        }
                        return Codes.HABITAT_FULL;
                    }
                    return Codes.WRONG_HABITAT;
                }
                return Codes.NOT_A_HABITAT;
            }
        }
        System.err.println("Area ID doesn't exist");
        return Codes.NOT_A_HABITAT;
    }

    // ---------------- INTERMEDIATE ----------------

    /**
     * Allows visitors to move between areas in the given direction (from -> to).
     * @param fromAreaId The ID of the area from which the destination is to be accessible.
     * @param toAreaId The ID of the destination area.
     */

    public void connectAreas(int fromAreaId, int toAreaId) {
        for (int i : connectedAreas.keySet()) {
            if (fromAreaId == toAreaId) {
                break;
            }
            else if (connectedAreas.get(fromAreaId).contains(toAreaId)) {
                break;
            }
            else if (fromAreaId == i) {
                connectedAreas.get(fromAreaId).add(toAreaId);
                areas.get(fromAreaId).addAdjacentArea(toAreaId);
            }
        }
    }

    /**
     * Checks if the given path obeys the one-way system.
     * @param areaIds The path is provided as a list of area IDs. It starts with the area ID at index 0.
     * @return Returns true iff visitors are allowed to visit the areas in the order given by the passed in list.
     */

    public boolean isPathAllowed(ArrayList<Integer> areaIds) {
        // Temporary variable that will help check if the ArrayList is not empty
        int allowed = 0;
        if (areaIds.size() == 1) {
            return true;
        }
        for (int i = 0; i < areaIds.size(); i++) {
            for (int j : connectedAreas.keySet()) {
                // Checks if for every key value in the connectedAreas Hashmap, the value is in the input path
                if (areaIds.get(i) == j) {
                    int getValue = i + 1;
                    if (connectedAreas.get(j).contains(areaIds.get(getValue))) {
                        allowed++;
                        if (allowed == (areaIds.size() - 1)) {
                            return true;
                        }
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Visits the areas in the specified order and records the names of all animals seen.
     * @param areaIdsVisited Areas IDs in the order they were visited.
     * @return Returns a list of the names of all animals seen during the visit in the order they were seen.
     */
    public ArrayList<String> visit(ArrayList<Integer> areaIdsVisited) {
        ArrayList<String> animalsEncountered = new ArrayList<>();
        if (!isPathAllowed(areaIdsVisited)) {
            return null;
        }
        for(int i : areaIdsVisited) {
            for (Animal j : animals.get(i)) {
                animalsEncountered.add(j.getNickname());
            }
        }
        return animalsEncountered;
    }

    /**
     * Finds all areas that cannot be reached from the entrance of the zoo.
     * @return The IDs of all inaccessible areas (unordered).
     */

    public Set<Integer> findUnreachableAreas() {
        // Two HashMaps, one stores all the areas you can reach, the other - all the ones you can't
        Set<Integer> unreachableAreas = areas.keySet();
        // Since we start at the entrance, all the areas connected to the entrance will always be accessible
        ArrayList<Integer> accessibleAreas = connectedAreas.get(0);
        // Since we start at the entrance, it will always be reachable
        unreachableAreas.remove(0);

        // First remove any of the areas connected to the entrance from the list of unreachable areas
        for(int i : connectedAreas.get(0)) {
            unreachableAreas.remove(i);
        }
        // Then loop over the all the keyset values and check if they are in the accessible list
        for (int j = 1; j < connectedAreas.keySet().size(); j++) {
            // If they are, remove all those areas from the inaccessible list, and add them to the accessible list
            if (accessibleAreas.contains(j)) {
                for (int k : connectedAreas.get(j)) {
                    accessibleAreas.add(k);
                    unreachableAreas.remove(k);
                }
            }
        }
        return unreachableAreas;
    }
}
