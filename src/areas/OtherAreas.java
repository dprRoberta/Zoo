package areas;

import java.util.ArrayList;

public abstract class OtherAreas implements IArea{

    public ArrayList<Integer> adjacentAreas = new ArrayList<>();

    public ArrayList<Integer> getAdjacentAreas() {
        return adjacentAreas;
    }

    public void addAdjacentArea(int areaId) {
        adjacentAreas.add(areaId);
    }

    public void removeAdjacentArea(int areaId) {
    }
}
