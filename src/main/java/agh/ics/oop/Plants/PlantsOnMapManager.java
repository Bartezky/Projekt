package agh.ics.oop.Plants;

import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.Utilities.Vector2D;
import agh.ics.oop.WorldMap;

import java.util.*;

public abstract class PlantsOnMapManager
{
    private final Map<Vector2D, Plant> plantsOnMap;
    private final int initialPlantCount;
    private final int singlePlantEnergy;
    private final int plantGrowthRate;

    protected final WorldMap map;
    protected final Set<Vector2D> allPositions;
    protected final Random random;

    public PlantsOnMapManager(WorldMap map, Configuration configuration)
    {
        this.map = map;
        this.initialPlantCount = configuration.getInitialPlantsCount();
        this.singlePlantEnergy = configuration.getSinglePlantEnergy();
        this.plantGrowthRate = configuration.getPlantGrowthRate();

        this.allPositions = new HashSet<>();
        this.random = new Random();
        this.plantsOnMap = new HashMap<>();

        initializeAllPositions();
    }

    public abstract void addNewPlants(int count);

    private void initializeAllPositions()
    {
        for(int i = 0; i < map.getWidth(); ++i)
        {
            for (int j = 0; j < map.getHeight(); ++j)
            {
                allPositions.add(new Vector2D(i, j));
            }
        }
    }

    protected Vector2D randomPosition(Set<Vector2D> positions)
    {
        Vector2D[] pos = positions.toArray(new Vector2D[positions.size()]);
        return pos[random.nextInt(pos.length)];
    }

    public boolean isFull()
    {
        return allPositions.size() == plantsOnMap.size();
    }

    public Map<Vector2D, Plant> getPlantsOnMap()
    {
        return plantsOnMap;
    }

    protected void placePlant(Plant plant)
    {
        plantsOnMap.put(plant.getVector2D(), plant);
    }

    public void deletePlant(Vector2D vector2D)
    {
        plantsOnMap.remove(vector2D);
    }

    public int getInitialPlantCount()
    {
        return initialPlantCount;
    }

    public int getSinglePlantEnergy()
    {
        return singlePlantEnergy;
    }

    public int getPlantGrowthRate()
    {
        return plantGrowthRate;
    }

    public Set<Vector2D> getAllPositionsCopy()
    {
        return new HashSet<>(allPositions);
    }
}
