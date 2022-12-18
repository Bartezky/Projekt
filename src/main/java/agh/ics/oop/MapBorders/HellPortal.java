package agh.ics.oop.MapBorders;

import agh.ics.oop.Animals.Animal;
import agh.ics.oop.Utilities.Orientation;
import agh.ics.oop.Utilities.Position;
import agh.ics.oop.Utilities.Vector2D;
import agh.ics.oop.WorldMap;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class HellPortal extends MapBordersManager
{
    public HellPortal(WorldMap map)
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

        if (vector2D.getX() < 0 || vector2D.getX() >= mapWidth || vector2D.getY() < 0 || vector2D.getY() >= mapHeight)
        {
            Random random = new Random();
            int x = random.nextInt(mapWidth);
            int y = random.nextInt(mapHeight);

            vector2D = new Vector2D(x, y);

            Position newPosition = new Position(vector2D, orientation);

            animal.setPosition(newPosition);
            animal.changeEnergy(-map.getAnimalsOnMapManager().getEnergyUsedToReproduce());

        }
        else
        {
            animal.setPosition(wantedPosition);
        }

        Map<Vector2D, Set<Animal>> animalsOnMap = map.getAnimalsOnMapManager().getAnimalsOnMap();
        placeAnimal(animalsOnMap, oldVector2D, vector2D, animal);
    }
}
