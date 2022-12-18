package agh.ics.oop.Plants;

import agh.ics.oop.Utilities.Vector2D;
import agh.ics.oop.WorldMap;

public class Plant
{
    private final WorldMap map;
    private final Vector2D vector2D;

    public Plant(WorldMap map, Vector2D vector2D)
    {
        this.map = map;
        this.vector2D = vector2D;
    }

    public Vector2D getVector2D()
    {
        return vector2D;
    }
}
