package agh.ics.oop.Utilities.Gui;

import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.Utilities.Simulation;
import agh.ics.oop.WorldMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window extends Application
{
    private WorldMap map;
    private GuiSimulation simulation;
    private WorldMapVisualiser visualiser;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        stage = primaryStage;
        Scene scene = visualiser.drawMap();
        primaryStage.setScene(scene);
        primaryStage.show();

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

        visualiser = new WorldMapVisualiser(map, conf);
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
                        Scene scene = visualiser.drawMap();
                        stage.setScene(scene);
                    }
                }
        );
    }
}
