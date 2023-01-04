package agh.ics.oop.Utilities;

import agh.ics.oop.Animals.Animal;
import agh.ics.oop.Genes.Genome;
import agh.ics.oop.WorldMap;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Statistics
{
    private WorldMap map;
    private int animalsCount;
    private int plantsCount;
    private int freePositionsCount;
    private Genome mostPopularGenome;
    private int averageLivingAnimalsEnergy;
    private int averageLifeLength;
    private int averageChildrenCount;
    private final boolean saveStatisticsToFile;
    private String filePath;

    public Statistics(WorldMap map)
    {
        this.map = map;

        if (map.getConfiguration().getSaveStatistics() == 0)
        {
            saveStatisticsToFile = false;
        }
        else
        {
            saveStatisticsToFile = true;
        }

        if (saveStatisticsToFile)
        {
            filePath = "Simulation" + map.getId() + "stats.csv";
        }

        updateStatistics();
    }

    public void updateStatistics()
    {
        calculateAnimalsCount();
        calculatePlantsCount();
        calculateFreePositions();
        calculateMostPopularGenome();
        calculateAverageLivingAnimalsEnergy();
        calculateAverageLifeLength();
        calculateAverageChildrenCount();

        if (saveStatisticsToFile)
        {
            saveToFile();
        }
    }

    private void calculateAnimalsCount()
    {
        animalsCount = map.getAnimalsOnMapManager().getAnimalsOnMap().size();
    }

    private void calculatePlantsCount()
    {
        plantsCount = map.getPlantsOnMapManager().getPlantsOnMap().size();
    }

    private void calculateFreePositions()
    {
        Set<Vector2D> positions = map.getPlantsOnMapManager().getAllPositionsCopy();
        positions.removeAll(map.getAnimalsOnMapManager().getAnimalsOnMap().keySet());
        positions.removeAll(map.getPlantsOnMapManager().getPlantsOnMap().keySet());

        freePositionsCount = positions.size();
    }

    private void calculateMostPopularGenome()
    {
        Map<Genome, Integer> genomeMap = new HashMap<>();

        for (Set<Animal> animalSet : map.getAnimalsOnMapManager().getAnimalsOnMap().values())
        {
            for (Animal animal : animalSet)
            {
                Genome genome = animal.getGenotype().getGenome();

                if (!genomeMap.containsKey(genome))
                {
                    genomeMap.put(genome, 0);
                }

                genomeMap.put(genome, genomeMap.get(genome) + 1);
            }
        }

        Genome mostPopular = null;
        int bestValue = 0;

        for (Map.Entry<Genome, Integer> entry : genomeMap.entrySet())
        {
            Genome genome = entry.getKey();
            int value = entry.getValue();

            if (value > bestValue)
            {
                bestValue = value;
                mostPopular = genome;
            }
        }

        mostPopularGenome = mostPopular;
    }

    private void calculateAverageLivingAnimalsEnergy()
    {
        int energy = 0;
        int animals = 0;

        for (Set<Animal> animalSet : map.getAnimalsOnMapManager().getAnimalsOnMap().values())
        {
            for (Animal animal : animalSet)
            {
                energy += animal.getEnergy();
                ++animals;
            }
        }

        if (animals == 0)
        {
            averageLivingAnimalsEnergy = 0;
        }
        else
        {
            averageLivingAnimalsEnergy = energy / animals;
        }
    }

    private void calculateAverageLifeLength()
    {
        int length = 0;
        int animals = 0;

        for (Set<Animal> animalSet : map.getAnimalsOnMapManager().getDeadAnimalsOnMap().values())
        {
            for (Animal animal : animalSet)
            {
                length += animal.getDeathDate() - animal.getBirthDate();
                ++animals;
            }
        }

        if (animals == 0)
        {
            averageLifeLength = 0;
        }
        else
        {
            averageLifeLength = length / animals;
        }
    }

    private void calculateAverageChildrenCount()
    {
        int children = 0;
        int animals = 0;

        for (Set<Animal> animalSet : map.getAnimalsOnMapManager().getAnimalsOnMap().values())
        {
            for (Animal animal : animalSet)
            {
                children += animal.getChildrenCount();
                ++animals;
            }
        }

        if (animals == 0)
        {
            averageChildrenCount = 0;
        }
        else
        {
            averageChildrenCount = children / animals;
        }
    }

    private void saveToFile()
    {
        try (FileWriter fileWriter = new FileWriter(filePath, true))
        {
            String line = animalsCount + ";" + plantsCount + ";" + freePositionsCount + ";" + mostPopularGenome.toString()
                    + ";" + averageLivingAnimalsEnergy + ";" + averageLifeLength + ";" + averageChildrenCount + "\n";
            fileWriter.append(line);
        }
        catch (IOException error)
        {
            System.out.println("Cannot save statistics");
        }
    }

    public int getAnimalsCount() {
        return animalsCount;
    }

    public int getPlantsCount() {
        return plantsCount;
    }

    public int getFreePositionsCount() {
        return freePositionsCount;
    }

    public Genome getMostPopularGenome() {
        return mostPopularGenome;
    }

    public int getAverageLivingAnimalsEnergy() {
        return averageLivingAnimalsEnergy;
    }

    public int getAverageLifeLength() {
        return averageLifeLength;
    }

    public int getAverageChildrenCount() {
        return averageChildrenCount;
    }
}
