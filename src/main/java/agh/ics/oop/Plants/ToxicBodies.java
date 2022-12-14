package agh.ics.oop.Plants;

import agh.ics.oop.*;
import agh.ics.oop.Animals.Animal;
import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.Utilities.Vector2D;

import java.util.*;

public class ToxicBodies extends PlantsOnMapManager
{
    public ToxicBodies(WorldMap map, Configuration configuration)
    {
        super(map, configuration);

        addNewPlants(super.getInitialPlantCount());
    }

    @Override
    public void addNewPlants(int count)
    {
        TreeMap<Integer, Set<Vector2D>> freePositions = getFreePositions();

        for (int i = 0; i < count; ++i)
        {
            if (freePositions.size() == 0)
            {
                return;
            }

            int result = random.nextInt(100);

            Integer preferredValue;

            if (result < 80)
            {
                preferredValue = freePositions.firstKey();
            }
            else
            {
                preferredValue = freePositions.lastKey();
            }

            Vector2D vector2D = randomPosition(freePositions.get(preferredValue));
            freePositions.get(preferredValue).remove(vector2D);

            if (freePositions.get(preferredValue).size() == 0)
            {
                freePositions.remove(preferredValue);
            }

            placePlant(new Plant(map, vector2D));
        }
    }

    private TreeMap<Integer, Set<Vector2D>> getFreePositions()
    {
        TreeMap<Integer, Set<Vector2D>> resultMap = new TreeMap<>();
        Map<Vector2D, Set<Animal>> deadAnimals = map.getAnimalsOnMapManager().getDeadAnimalsOnMap();

        for (Map.Entry<Vector2D, Set<Animal>> entry : deadAnimals.entrySet())
        {
            Vector2D vector2D = entry.getKey();
            int value = entry.getValue().size();

            if  (!resultMap.containsKey(value))
            {
                resultMap.put(value, new HashSet<>());
            }

            resultMap.get(value).add(vector2D);
        }

        Set<Vector2D> fieldsWithoutDeaths = new HashSet<>(allPositions);
        fieldsWithoutDeaths.removeAll(deadAnimals.keySet());

        for (Vector2D vector2D : fieldsWithoutDeaths)
        {
            if (!resultMap.containsKey(0))
            {
                resultMap.put(0, new HashSet<>());
            }

            resultMap.get(0).add(vector2D);
        }

        return resultMap;
    }
}
