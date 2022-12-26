package agh.ics.oop.Utilities;

import agh.ics.oop.WorldMap;

public class Simulation implements Runnable
{
    private final WorldMap map;
    private final Configuration configuration;
    private final int moveDelay;

    public Simulation(Configuration configuration)
    {
        this.configuration = configuration;
        this.map = new WorldMap(configuration);
        moveDelay = 1000;
    }

    @Override
    public void run()
    {
        while (!map.isDead())
        {
            try
            {
                Thread.sleep(moveDelay);
            }
            catch (InterruptedException error)
            {
                System.out.println(error.getMessage());
                System.exit(2);
            }

            map.nextDay();
        }
    }

    public WorldMap getMap()
    {
        return this.map;
    }

    public Configuration getConfiguration()
    {
        return this.configuration;
    }
}
