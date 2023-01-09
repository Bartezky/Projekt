package agh.ics.oop.Animals;

import agh.ics.oop.Genes.*;
import agh.ics.oop.Utilities.Configuration;
import agh.ics.oop.Utilities.Orientation;
import agh.ics.oop.Utilities.Position;
import agh.ics.oop.WorldMap;

import java.util.Random;

public class Reproduction {

    private final Configuration configuration;
    private final Mutation mutation;
    private final WorldMap worldMap;
    private final Random random;

    public Reproduction(Configuration configuration, WorldMap worldMap, Mutation mutation) {
        this.configuration = configuration;
        this.mutation = mutation;
        this.worldMap = worldMap;
        random = new Random();
    }

    public void reproduce(Animal stronger, Animal weaker) {
        {
            if (stronger.isDead() || weaker.isDead()) {
                return;
            }

            Genotype newGenotype = createGenotype(stronger, weaker);

            stronger.hasNewChild();
            weaker.hasNewChild();

            Animal newborn = new Animal(new Position(stronger.getPosition().getVector2D(), new Orientation()), 2 * configuration.getAnimalEnergyUsedToReproduce(), newGenotype, worldMap.getCurrentDay(), worldMap);
            worldMap.getAnimalsOnMapManager().placeAnimal(newborn.getPosition(), newborn);
        }
    }

    private Genotype createGenotype(Animal stronger, Animal weaker) {
        int numberOfGenesFromStronger = configuration.getAnimalGenomeLength() * stronger.getEnergy() / (stronger.getEnergy() + weaker.getEnergy());
        int numberOfGenesFromWeaker = configuration.getAnimalGenomeLength() - numberOfGenesFromStronger;

        Genome genome;
        if (random.nextBoolean()) {
            genome = new Genome(stronger.getGenotype().getGenome().getLeftSlice(numberOfGenesFromStronger), weaker.getGenotype().getGenome().getRightSlice(numberOfGenesFromWeaker));
        } else {
            genome = new Genome(weaker.getGenotype().getGenome().getLeftSlice(numberOfGenesFromWeaker), stronger.getGenotype().getGenome().getRightSlice(numberOfGenesFromStronger));
        }

        int genePointerType = configuration.getAnimalBehaviorVariant();
        GenePointer genePointer;
        if (genePointerType == 0) {
            genePointer = new BitMadness(configuration.getAnimalGenomeLength());
        } else {
            genePointer = new FullPredistination(configuration.getAnimalGenomeLength());
        }

        return new Genotype(genePointer, mutation.mutate(genome));
    }

}
