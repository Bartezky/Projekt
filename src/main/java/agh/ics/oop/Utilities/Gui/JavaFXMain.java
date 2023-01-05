package agh.ics.oop.Utilities.Gui;

import agh.ics.oop.Utilities.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXMain extends Application
{
    private Configuration[] configurations;
    private SimulationStages[] simulationsStages;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        String[] args = getParameters().getRaw().toArray(String[]::new);
        configurations = new Configuration[args.length];
        simulationsStages = new SimulationStages[args.length];

        for (int i = 0; i < args.length; ++i)
        {
            configurations[i] = new Configuration(args[i]);
            simulationsStages[i] = new SimulationStages(configurations[i]);
        }
    }
}
