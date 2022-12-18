package agh.ics.oop.Animals;

import agh.ics.oop.Genes.*;
import agh.ics.oop.Utilities.Position;
import agh.ics.oop.Utilities.Vector2D;
import agh.ics.oop.WorldMap;

import java.util.*;

public class AnimalsOnMapManager
{
    private final WorldMap map;
    private final Map<Vector2D, Set<Animal>> animalsOnMap;
    private final Map<Vector2D, Set<Animal>> deadAnimalsOnMap;
    private final int initialAnimalCount;
    private final int initialAnimalEnergy;
    private final int minimumEnergyToReproduce;
    private final int energyUsedToReproduce;
    private final Mutation mutation;
    private final int genotypeLength;
    private final Behavior behavior;
    private final Comparator<Animal> comparator;
    private final int genePointerType;

    public AnimalsOnMapManager(WorldMap map, int initialAnimalCount, int initialAnimalEnergy, int minimumEnergyToReproduce, int energyUsedToReproduce, Mutation mutation, int genotypeLength, Behavior behavior, int genePointerType)
    {
        this.map = map;
        this.initialAnimalCount = initialAnimalCount;
        this.initialAnimalEnergy = initialAnimalEnergy;
        this.minimumEnergyToReproduce = minimumEnergyToReproduce;
        this.energyUsedToReproduce = energyUsedToReproduce;
        this.mutation = mutation;
        this.genotypeLength = genotypeLength;
        this.behavior = behavior;
        this.genePointerType = genePointerType;
        comparator = new AnimalComparator();

        animalsOnMap = new HashMap<>();
        deadAnimalsOnMap = new HashMap<>();

        addInitialAnimals();
    }
    private void placeAnimal(Position position, Animal animal)
    {
        Vector2D vector2D = position.getVector2D();

        if (!animalsOnMap.containsKey(vector2D))
        {
            animalsOnMap.put(vector2D, new HashSet<>());
        }

        animalsOnMap.get(vector2D).add(animal);
    }

    private void addInitialAnimals()
    {
        Random random = new Random();

        for (int i = 0; i < initialAnimalCount; ++i)
        {
            int x = random.nextInt(map.getWidth());
            int y = random.nextInt(map.getHeight());

            int orientation = random.nextInt(8);

            Genome genome = new Genome(genotypeLength);
            GenePointer genePointer;
            if (genePointerType == 0) {
                genePointer = new BitMadness(genotypeLength);
            }
            else {
                genePointer = new FullPredistination(genotypeLength);
            }

            Position position = new Position(new Vector2D(x, y), orientation);
            Animal animal = new Animal(position, initialAnimalEnergy, new Genotype(genePointer, genome), behavior, 0);

            placeAnimal(position, animal);
        }
    }

    private Animal[] sortAnimals(Set<Animal> animals)
    {
        Animal[] animalsArray = (Animal[]) animals.toArray();
        Arrays.sort(animalsArray, comparator);

        return animalsArray;
    }

    public void eatPlants()
    {
        Set<Vector2D> plantsPositions = map.getPlantsOnMapManager().getPlantsOnMap().keySet();
        Set<Vector2D> eatenPlants = new HashSet<>();

        for (Vector2D vector2D : plantsPositions)
        {
            Set<Animal> animals = animalsOnMap.get(vector2D);

            if (animals.size() > 0)
            {
                Animal[] animalsArray = sortAnimals(animals);
                Animal strongestAnimal = animalsArray[animalsArray.length - 1];
                strongestAnimal.changeEnergy(map.getPlantsOnMapManager().getSinglePlantEnergy());
                eatenPlants.add(vector2D);
            }
        }

        for (Vector2D vector2D : eatenPlants)
        {
            map.getPlantsOnMapManager().deletePlant(vector2D);
        }
    }

    public void deleteDeadAnimalsFromMap()
    {
        for (Map.Entry<Vector2D, Set<Animal>> entry : animalsOnMap.entrySet())
        {
            Vector2D vector2D = entry.getKey();
            Set<Animal> animalSet = entry.getValue();

            for (Animal animal : animalSet)
            {
                if (animal.isDead())
                {
                    if (!deadAnimalsOnMap.containsKey(vector2D))
                    {
                        deadAnimalsOnMap.put(vector2D, new HashSet<>());
                        deadAnimalsOnMap.get(vector2D).add(animal);
                    }

                    animalsOnMap.get(vector2D).remove(animal);

                    if (animalsOnMap.get(vector2D).size() == 0)
                    {
                        animalsOnMap.remove(vector2D);
                    }
                }
            }
        }
    }

    public Map<Vector2D, Set<Animal>> getAnimalsOnMap() {
        return animalsOnMap;
    }

    public int getInitialAnimalCount() {
        return initialAnimalCount;
    }

    public int getInitialAnimalEnergy() {
        return initialAnimalEnergy;
    }

    public int getMinimumEnergyToReproduce() {
        return minimumEnergyToReproduce;
    }

    public int getEnergyUsedToReproduce() {
        return energyUsedToReproduce;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public int getGenotypeLength() {
        return genotypeLength;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public Map<Vector2D, Set<Animal>> getDeadAnimalsOnMap()
    {
        return deadAnimalsOnMap;
    }
}
