package agh.ics.oop.Plants;

import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.Utilities.Vector2D;
import agh.ics.oop.WorldMap;
import java.util.HashSet;
import java.util.Set;

public class WoodedEquator extends PlantsOnMapManager
{
    private final int lowerPreferredRow;
    private final int upperPreferredRow;
    private final Set<Vector2D> preferredPositions;

    public WoodedEquator(WorldMap map, Configuration configuration)
    {
        super(map, configuration);

        lowerPreferredRow = calculateLowerPreferredRow();
        upperPreferredRow = calculateUpperPreferredRow();
        preferredPositions = new HashSet<>();

        initialisePreferredPositions();
    }

    @Override
    public void addNewPlants(int count)
    {
        Set<Vector2D> preferredPositions = getFreePreferredPositions();
        Set<Vector2D> nonPreferredPositions = getFreeNonPreferredPositions();

        for (int i = 0; i < count; ++i)
        {
            if (preferredPositions.size() == 0 && nonPreferredPositions.size() == 0)
            {
                return;
            }

            int result = random.nextInt(100);

            Vector2D vector2D;

            if (result < 80)
            {
                if (preferredPositions.size() > 0)
                {
                    vector2D = randomPosition(preferredPositions);
                    preferredPositions.remove(vector2D);
                }
                else
                {
                    vector2D = randomPosition(nonPreferredPositions);
                    nonPreferredPositions.remove(vector2D);
                }
            }
            else
            {
                if (nonPreferredPositions.size() > 0)
                {
                    vector2D = randomPosition(nonPreferredPositions);
                    nonPreferredPositions.remove(vector2D);
                }
                else
                {
                    vector2D = randomPosition(preferredPositions);
                    preferredPositions.remove(vector2D);
                }
            }

            placePlant(new Plant(map, vector2D));
        }
    }

    private int calculateLowerPreferredRow()
    {
        int maxY = map.getHeight();
        int numberOfPreferredRows = (maxY / 5) + 1;
        int numberOfRowsToOmit = (maxY - numberOfPreferredRows) / 2;
        return numberOfRowsToOmit;
    }

    private int calculateUpperPreferredRow()
    {
        int maxY = map.getHeight();
        int numberOfPreferredRows = (maxY / 5) + 1;

        return lowerPreferredRow + numberOfPreferredRows;
    }

    private void initialisePreferredPositions()
    {
        for (int i = 0; i < map.getWidth(); ++i)
        {
            for (int j = lowerPreferredRow; j <= upperPreferredRow; ++j)
            {
                preferredPositions.add(new Vector2D(i, j));
            }
        }
    }

    private Set<Vector2D> getFreePreferredPositions()
    {
        Set<Vector2D> resultSet = new HashSet<>(preferredPositions);
        Set<Vector2D> takenSet = new HashSet<>(this.getPlantsOnMap().keySet());
        resultSet.removeAll(takenSet);

        return resultSet;
    }

    private Set<Vector2D> getFreeNonPreferredPositions()
    {
        Set<Vector2D> resultSet = new HashSet<>(allPositions);
        resultSet.removeAll(preferredPositions);
        resultSet.removeAll(this.getPlantsOnMap().keySet());

        return resultSet;
    }
}
