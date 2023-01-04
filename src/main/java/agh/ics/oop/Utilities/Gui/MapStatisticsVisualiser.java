package agh.ics.oop.Utilities.Gui;

import agh.ics.oop.Utilities.Statistics;
import agh.ics.oop.WorldMap;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class MapStatisticsVisualiser
{
    private final WorldMap map;
    private final Statistics statistics;

    public MapStatisticsVisualiser(WorldMap map)
    {
        this.map = map;
        statistics = map.getStatistics();
    }

    public Scene drawStatistics()
    {
        GridPane gridPane = new GridPane();

        gridPane.add(getAnimalsCount(), 0, 0);
        gridPane.add(getPlantsCount(), 0, 1);
        gridPane.add(getFreePositionsCount(), 0, 2);
        gridPane.add(getMostPopularGenome(), 0, 3);
        gridPane.add(getAverageAnimalEnergy(), 0, 4);
        gridPane.add(getAverageLifeLength(), 0, 5);
        gridPane.add(getAverageChildrenCount(), 0, 6);

        return new Scene(gridPane);
    }

    private Label getAnimalsCount()
    {
        return new Label("Living animals count: " + statistics.getAnimalsCount());
    }

    private Label getPlantsCount()
    {
        return new Label("Plants count: " + statistics.getPlantsCount());
    }

    private Label getFreePositionsCount()
    {
        return new Label("Free positions count: " + statistics.getFreePositionsCount());
    }

    private Label getMostPopularGenome()
    {
        return new Label("Most popular genome among living animals: " + statistics.getMostPopularGenome().toString());
    }

    private Label getAverageAnimalEnergy()
    {
        return new Label("Average living animals' energy: " + statistics.getAverageLivingAnimalsEnergy());
    }

    private Label getAverageLifeLength()
    {
        return new Label("Average dead animals' life length: " + statistics.getAverageLifeLength());
    }

    private Label getAverageChildrenCount()
    {
        return new Label("Average living animals' children count: " + statistics.getAverageChildrenCount());
    }
}
