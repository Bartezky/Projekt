package agh.ics.oop;

import agh.ics.oop.Animals.AnimalsOnMapManager;
import agh.ics.oop.MapBorders.Globe;
import agh.ics.oop.MapBorders.HellPortal;
import agh.ics.oop.MapBorders.MapBordersManager;
import agh.ics.oop.Plants.PlantsOnMapManager;
import agh.ics.oop.Plants.ToxicBodies;
import agh.ics.oop.Plants.WoodedEquator;
import agh.ics.oop.Utilities.Configuration;

public class WorldMap {
    private final int width;
    private final int height;
    private final AnimalsOnMapManager animalsOnMapManager;
    private final MapBordersManager mapBordersManager;
    private final PlantsOnMapManager plantsOnMapManager;
    private final Configuration configuration;
    private int currentDay;

    public WorldMap(Configuration configuration) {
        this.configuration = configuration;
        width = configuration.getMapWidth();
        height = configuration.getMapHeight();
        currentDay = 0;
        animalsOnMapManager = new AnimalsOnMapManager(this, configuration);

        switch (configuration.getMapVariant()) {
            case 0 -> mapBordersManager = new Globe(this);
            case 1 -> mapBordersManager = new HellPortal(this);
            default -> throw new IllegalArgumentException("Illegal map variant");
        }

        switch (configuration.getPlantGrowthVariant()) {
            case 0 -> plantsOnMapManager = new WoodedEquator(this, configuration);
            case 1 -> plantsOnMapManager = new ToxicBodies(this, configuration);
            default -> throw new IllegalArgumentException("Illegal plant growth variant");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public AnimalsOnMapManager getAnimalsOnMapManager() {
        return animalsOnMapManager;
    }

    public MapBordersManager getMapBordersManager() {
        return mapBordersManager;
    }

    public PlantsOnMapManager getPlantsOnMapManager() {
        return plantsOnMapManager;
    }

    public int getCurrentDay() {
        return this.currentDay;
    }

    public void nextDay() {
        animalsOnMapManager.deleteDeadAnimalsFromMap();
        animalsOnMapManager.moveAnimals();
        animalsOnMapManager.eatPlants();
        animalsOnMapManager.reproduceAnimals();
        plantsOnMapManager.addNewPlants(plantsOnMapManager.getPlantGrowthRate());
    }

    public boolean isDead() {
        return animalsOnMapManager.isEmpty() && plantsOnMapManager.isFull();
    }
}
