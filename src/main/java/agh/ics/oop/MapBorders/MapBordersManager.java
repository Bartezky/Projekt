package agh.ics.oop.MapBorders;

import agh.ics.oop.Animals.Animal;
import agh.ics.oop.Utilities.Position;
import agh.ics.oop.Utilities.Vector2D;
import agh.ics.oop.WorldMap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class MapBordersManager {
    protected WorldMap map;

    public MapBordersManager(WorldMap map) {
        this.map = map;
    }

    public abstract void moveAnimal(Position wantedPosition, Animal animal);

    protected void placeAnimal(Vector2D oldVector2D, Vector2D newVector2D, Animal animal) {
        Map<Vector2D, Set<Animal>> animalsOnMap = map.getAnimalsOnMapManager().getAnimalsOnMap();

        animalsOnMap.get(oldVector2D).remove(animal);

        if (animalsOnMap.get(oldVector2D).size() == 0) {
            animalsOnMap.remove(oldVector2D);
        }

        if (!animalsOnMap.containsKey(newVector2D)) {
            animalsOnMap.put(newVector2D, new HashSet<>());
        }

        animalsOnMap.get(newVector2D).add(animal);
    }
}
