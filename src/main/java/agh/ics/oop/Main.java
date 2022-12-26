package agh.ics.oop;

import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.Utilities.Simulation;

public class Main {
    public static void main(String[] args)
    {
        Configuration conf = new Configuration();

        Simulation simulation = new Simulation(conf);

        simulation.run();
    }
}