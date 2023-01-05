package agh.ics.oop.Utilities;

import java.io.*;

public class Configuration
{
    private int mapWidth;
    private int mapHeight;
    private int mapVariant;
    private int initialPlantsCount;
    private int singlePlantEnergy;
    private int plantGrowthRate;
    private int plantGrowthVariant;
    private int initialAnimalsCount;
    private int initialAnimalEnergy;
    private int requiredAnimalEnergyToReproduce;
    private int animalEnergyUsedToReproduce;
    private int minimumNumberOfMutations;
    private int maximumNumberOfMutations;
    private int mutationVariant;
    private int animalGenomeLength;
    private int animalBehaviorVariant;
    private int refreshRate;
    private int windowWidth;
    private int windowHeight;
    private int saveStatistics;
    private final String configFilePath;

    public Configuration(String configFilePath)
    {
        this.configFilePath = configFilePath;

        try
        {
            readParameters();
            validateInput();
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
            System.exit(1);
        }
    }

    private void validateInput()
    {
        if (mapWidth < 1 || mapHeight < 1 || initialPlantsCount < 0 || initialAnimalsCount > (mapWidth * mapHeight) ||
                singlePlantEnergy < 1 || plantGrowthRate < 0 || initialAnimalsCount < 1 || initialAnimalEnergy < 0 ||
                requiredAnimalEnergyToReproduce < animalEnergyUsedToReproduce || animalEnergyUsedToReproduce < 1 ||
                minimumNumberOfMutations < 0 || minimumNumberOfMutations > maximumNumberOfMutations ||
                animalGenomeLength < 1 || refreshRate < 1 || windowWidth < 1 || windowHeight < 1)
        {
            throw new IllegalArgumentException("Wrong values of simulation");
        }
    }

    private void readParameters() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(configFilePath)))
        {
            String singleLine;

            while ((singleLine = bufferedReader.readLine()) != null)
            {
                if (!singleLine.startsWith("#") && !singleLine.isEmpty())
                {
                    String strings[] = singleLine.split(":");
                    String key = strings[0].trim();
                    int value;
                    try
                    {
                        value = Integer.valueOf(strings[1].trim());
                    }
                    catch (NumberFormatException error)
                    {
                        throw new IllegalArgumentException("Wrong parameter value: " + key + " - " + strings[1]);
                    }


                    switch(key)
                    {
                        case "mapWidth" -> mapWidth = value;
                        case "mapHeight" -> mapHeight = value;
                        case "mapVariant" -> mapVariant = value;
                        case "initialPlantsCount" -> initialPlantsCount = value;
                        case "singlePlantEnergy" -> singlePlantEnergy = value;
                        case "plantGrowthRate" -> plantGrowthRate = value;
                        case "plantGrowthVariant" -> plantGrowthVariant = value;
                        case "initialAnimalsCount" -> initialAnimalsCount = value;
                        case "initialAnimalEnergy" -> initialAnimalEnergy = value;
                        case "requiredAnimalEnergyToReproduce" -> requiredAnimalEnergyToReproduce = value;
                        case "animalEnergyUsedToReproduce" -> animalEnergyUsedToReproduce = value;
                        case "minimumNumberOfMutations" -> minimumNumberOfMutations = value;
                        case "maximumNumberOfMutations" -> maximumNumberOfMutations = value;
                        case "mutationVariant" -> mutationVariant = value;
                        case "animalGenomeLength" -> animalGenomeLength = value;
                        case "animalBehaviorVariant" -> animalBehaviorVariant = value;
                        case "refreshRate" -> refreshRate = value;
                        case "windowWidth" -> windowWidth = value;
                        case "windowHeight" -> windowHeight = value;
                        case "saveStatistics" -> saveStatistics = value;
                        default -> throw new IllegalArgumentException("Cannot resolve variable: " + key);
                    }
                }
            }
        }
        catch(IOException error)
        {
            throw new IOException("Cannot access file: " + configFilePath);
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapVariant() {
        return mapVariant;
    }

    public int getInitialPlantsCount() {
        return initialPlantsCount;
    }

    public int getSinglePlantEnergy() {
        return singlePlantEnergy;
    }

    public int getPlantGrowthRate() {
        return plantGrowthRate;
    }

    public int getPlantGrowthVariant() {
        return plantGrowthVariant;
    }

    public int getInitialAnimalsCount() {
        return initialAnimalsCount;
    }

    public int getInitialAnimalEnergy() {
        return initialAnimalEnergy;
    }

    public int getRequiredAnimalEnergyToReproduce() {
        return requiredAnimalEnergyToReproduce;
    }

    public int getAnimalEnergyUsedToReproduce() {
        return animalEnergyUsedToReproduce;
    }

    public int getMinimumNumberOfMutations() {
        return minimumNumberOfMutations;
    }

    public int getMaximumNumberOfMutations() {
        return maximumNumberOfMutations;
    }

    public int getMutationVariant() {
        return mutationVariant;
    }

    public int getAnimalGenomeLength() {
        return animalGenomeLength;
    }

    public int getAnimalBehaviorVariant() {
        return animalBehaviorVariant;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getSaveStatistics() {
        return saveStatistics;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }
}
