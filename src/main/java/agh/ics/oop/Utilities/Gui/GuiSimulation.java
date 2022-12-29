package agh.ics.oop.Utilities.Gui;

import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.Utilities.Simulation;

public class GuiSimulation extends Simulation
{
    private Window window;
    public GuiSimulation(Configuration configuration, Window window)
    {
        super(configuration);
        this.window = window;
    }

    @Override
    protected void performNextDay()
    {
        super.performNextDay();
        window.notifyWindow();
    }
}
