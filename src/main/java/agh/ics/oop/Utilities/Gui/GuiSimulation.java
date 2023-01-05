package agh.ics.oop.Utilities.Gui;

import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.Utilities.Simulation;

public class GuiSimulation extends Simulation
{
    private SimulationStages simulationStages;
    public GuiSimulation(Configuration configuration, SimulationStages simulationStages)
    {
        super(configuration);
        this.simulationStages = simulationStages;
    }

    @Override
    protected void performNextDay()
    {
        super.performNextDay();
        simulationStages.notifyStages();
    }
}
