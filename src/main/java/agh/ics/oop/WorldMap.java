package agh.ics.oop;

import agh.ics.oop.Animals.AnimalsOnMapManager;
import agh.ics.oop.MapBorders.MapBordersManager;
import agh.ics.oop.Plants.PlantsOnMapManager;

public class WorldMap
{
    private final int width;
    private final int height;
    private final AnimalsOnMapManager animalsOnMapManager;
    private final MapBordersManager mapBordersManager;
    private final PlantsOnMapManager plantsOnMapManager;

    public WorldMap(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public AnimalsOnMapManager getAnimalsOnMapManager()
    {
        return animalsOnMapManager;
    }

    public MapBordersManager getMapBordersManager()
    {
        return mapBordersManager;
    }

    public PlantsOnMapManager getPlantsOnMapManager()
    {
        return plantsOnMapManager;
    }
}
