package agh.ics.oop.Animals;

import agh.ics.oop.Genes.*;
import agh.ics.oop.Utilities.Orientation;
import agh.ics.oop.Utilities.Position;
import agh.ics.oop.WorldMap;

import java.util.Random;

public class Animal {
    private final Genotype genotype;
    private final WorldMap worldMap;
    private final int birthDate;
    private int deathDate;
    private Position position;
    private int energy;
    private int childrenCount;

    public Animal(Position position, int energy, Genotype genotype, int date, WorldMap worldMap) {
        this.position = position;
        this.energy = energy;
        this.genotype = genotype;
        birthDate = date;
        childrenCount = 0;
        this.worldMap = worldMap;
        deathDate = -1;
    }

    public void reproduce(Animal animal, Mutation mutation) {
        if (this.isDead() || animal.isDead())
        {
            return;
        }

        int numberOfGenesFromStronger = worldMap.getConfiguration().getAnimalGenomeLength() * this.energy / (this.energy + animal.energy);
        int numberOfGenesFromWeaker = worldMap.getConfiguration().getAnimalGenomeLength() - numberOfGenesFromStronger;

        Random random = new Random();
        Genome genome;
        if (random.nextBoolean()) {
            genome = new Genome(this.genotype.getGenome().getLeftSlice(numberOfGenesFromStronger), animal.genotype.getGenome().getRightSlice(numberOfGenesFromWeaker));
        } else {
            genome = new Genome(this.genotype.getGenome().getLeftSlice(numberOfGenesFromWeaker), animal.genotype.getGenome().getRightSlice(numberOfGenesFromStronger));
        }

        int genePointerType = worldMap.getConfiguration().getAnimalBehaviorVariant();
        GenePointer genePointer;
        if (genePointerType == 0) {
            genePointer = new BitMadness(worldMap.getConfiguration().getAnimalGenomeLength());
        } else {
            genePointer = new FullPredistination(worldMap.getConfiguration().getAnimalGenomeLength());
        }

        Genotype newGenotype = new Genotype(genePointer, mutation.mutate(genome));

        this.changeEnergy(-worldMap.getConfiguration().getAnimalEnergyUsedToReproduce());
        animal.changeEnergy(-worldMap.getConfiguration().getAnimalEnergyUsedToReproduce());

        this.childrenCount += 1;
        animal.childrenCount += 1;

        Animal newborn = new Animal(new Position(position.getVector2D(), new Orientation()), 2 * worldMap.getConfiguration().getAnimalEnergyUsedToReproduce(), newGenotype, worldMap.getCurrentDay(), worldMap);
        worldMap.getAnimalsOnMapManager().placeAnimal(position, newborn);

    }

    public void move() {
        position = position.turn(genotype.getGene());
        worldMap.getMapBordersManager().moveAnimal(position.forwardPosition(), this);
        changeEnergy(-1);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void changeEnergy(int value) {
        this.energy += value;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isDead() {
        return energy <= 0;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public void die()
    {
        deathDate = worldMap.getCurrentDay();
    }

    public int getDeathDate() {
        return deathDate;
    }
}
