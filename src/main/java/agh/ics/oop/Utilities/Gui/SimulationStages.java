package agh.ics.oop.Utilities.Gui;

import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.WorldMap;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulationStages
{
    private final WorldMap map;
    private final GuiSimulation simulation;
    private final WorldMapVisualiser worldMapVisualiser;
    private final MapStatisticsVisualiser mapStatisticsVisualiser;

    private Stage mapStage;
    private Stage statisticsStage;
    private Thread engineThread;

    public SimulationStages(Configuration configuration)
    {
        simulation = new GuiSimulation(configuration, this);
        map = simulation.getMap();
        worldMapVisualiser = new WorldMapVisualiser(map, configuration);
        mapStatisticsVisualiser = new MapStatisticsVisualiser(map);

        mapStage = new Stage();
        mapStage.setTitle("Simulation " + map.getId());
        mapStage.setOnCloseRequest(
                event -> {
                    engineThread.stop();
                }
        );

        statisticsStage = new Stage();
        statisticsStage.setTitle("Simulation " + map.getId() + " statistics");

        draw();

        mapStage.show();
        statisticsStage.show();

        engineThread = new Thread(simulation);
        engineThread.start();
    }

    public void notifyStages()
    {
        draw();
    }

    private void draw()
    {
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run()
                    {
                        Scene mapScene = worldMapVisualiser.drawMap();
                        Scene statisticsScene = mapStatisticsVisualiser.drawStatistics();
                        mapStage.setScene(mapScene);
                        statisticsStage.setScene(statisticsScene);
                    }
                }
        );
    }
}
