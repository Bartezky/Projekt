package agh.ics.oop.Utilities.Gui;

import agh.ics.oop.Animals.Animal;
import agh.ics.oop.Plants.Plant;
import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.Utilities.Vector2D;
import agh.ics.oop.WorldMap;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Set;

public class WorldMapVisualiser
{
    private final WorldMap map;
    private final int minimumX;
    private final int maximumX;
    private final int minimumY;
    private final int maximumY;
    private final int cellWidth;
    private final int cellHeight;
    private final Configuration configuration;

    public WorldMapVisualiser(WorldMap map, Configuration configuration)
    {
        this.map = map;

        minimumX = 0;
        minimumY = 0;

        maximumX = map.getWidth() - 1;
        maximumY = map.getHeight() - 1;

        cellWidth = configuration.getWindowWidth() / map.getWidth();
        cellHeight = configuration.getWindowHeight() / map.getHeight();

        this.configuration = configuration;
    }

    public Scene drawMap()
    {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        for (int i = minimumX; i <= maximumX; ++i)
        {
            gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            gridPane.getRowConstraints().add(new RowConstraints(cellHeight));

            for (int j = minimumY; j <= maximumY; ++j)
            {
                Vector2D coordinates = new Vector2D(i, j);
                Set<Animal> animalSet = map.getAnimalsOnMapManager().getAnimalsOnMap().get(coordinates);
                Plant plant = map.getPlantsOnMapManager().getPlantsOnMap().get(coordinates);
                VBox vBox = new VBox();

                addBackground(vBox, plant);
                addAnimal(vBox, animalSet);

                GridPane.setHalignment(vBox, HPos.CENTER);
                gridPane.add(vBox, i - minimumX, maximumY - j);
            }
        }

        return new Scene(gridPane, configuration.getWindowWidth(), configuration.getWindowHeight());
    }

    private void addBackground(VBox vBox, Plant plant)
    {
        if (plant == null)
        {
            vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else
        {
            vBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    private void addAnimal(VBox vBox, Set<Animal> set)
    {
        if (set != null)
        {
            Circle animalShape = new Circle();
            animalShape.setRadius(cellWidth / 2);

            if (set.size() > 1)
            {
                animalShape.setFill(Color.CYAN);
            }
            else
            {
                Animal animal = set.iterator().next();

                if (animal.getEnergy() <= 10)
                {
                    animalShape.setFill(Color.RED);
                }
                else if (animal.getEnergy() <= 20)
                {
                    animalShape.setFill(Color.YELLOW);
                }
                else
                {
                    animalShape.setFill(Color.BLUE);
                }
            }

            vBox.getChildren().add(animalShape);
        }
    }
}
