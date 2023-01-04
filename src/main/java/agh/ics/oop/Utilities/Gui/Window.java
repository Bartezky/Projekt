package agh.ics.oop.Utilities.Gui;

import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.WorldMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window extends Application
{
    private WorldMap map;
    private GuiSimulation simulation;
    private WorldMapVisualiser mapVisualiser;
    private MapStatisticsVisualiser statisticsVisualiser;
    private Stage mainStage;
    private Stage statisticsStage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setOnCloseRequest(
                event -> {
                    Platform.exit();
                    System.exit(0);
                }
        );
        mainStage = primaryStage;
        mainStage.setTitle("Simulation " + map.getId());
        Scene scene = mapVisualiser.drawMap();
        primaryStage.setScene(scene);
        primaryStage.show();

        statisticsStage = new Stage();
        Scene stats = statisticsVisualiser.drawStatistics();
        statisticsStage.setScene(stats);
        statisticsStage.setTitle("Simulation " + map.getId() + " statistics");
        statisticsStage.show();

        Thread engine = new Thread(simulation);
        engine.start();
    }

    @Override
    public void init() throws Exception
    {
        super.init();

        Configuration conf = new Configuration();
        simulation = new GuiSimulation(conf, this);
        map = simulation.getMap();

        mapVisualiser = new WorldMapVisualiser(map, conf);
        statisticsVisualiser = new MapStatisticsVisualiser(map);
    }

    public void notifyWindow()
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
                        Scene mapScene = mapVisualiser.drawMap();
                        Scene statisticsScene = statisticsVisualiser.drawStatistics();
                        mainStage.setScene(mapScene);
                        statisticsStage.setScene(statisticsScene);
                    }
                }
        );
    }
}
