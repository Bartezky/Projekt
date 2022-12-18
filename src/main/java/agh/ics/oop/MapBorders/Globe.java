package agh.ics.oop.MapBorders;

import agh.ics.oop.Animals.Animal;
import agh.ics.oop.Utilities.Orientation;
import agh.ics.oop.Utilities.Position;
import agh.ics.oop.Utilities.Vector2D;
import agh.ics.oop.WorldMap;

import java.util.Map;
import java.util.Set;

public class Globe extends MapBordersManager
{
    public Globe(WorldMap map)
    {
        super(map);
    }

    @Override
    public void moveAnimal(Position wantedPosition, Animal animal)
    {
        int mapWidth = map.getWidth();
        int mapHeight = map.getHeight();

        Vector2D oldVector2D = animal.getPosition().getVector2D();
        Vector2D vector2D = wantedPosition.getVector2D();
        Orientation orientation = wantedPosition.getOrientation();

        Position newPosition;

        if (vector2D.getY() >= mapHeight || vector2D.getY() < 0)
        {
            newPosition = new Position(animal.getPosition().getVector2D(), orientation.opposite());
            animal.setPosition(newPosition);
        }
        else
        {
            Vector2D newVector2D = new Vector2D((mapWidth + vector2D.getX()) % mapWidth, vector2D.getY());
            newPosition = new Position(newVector2D, orientation);
            animal.setPosition(newPosition);
        }

        Map<Vector2D, Set<Animal>> animalsOnMap = map.getAnimalsOnMapManager().getAnimalsOnMap();

        placeAnimal(animalsOnMap, oldVector2D, vector2D, animal);
    }
}
