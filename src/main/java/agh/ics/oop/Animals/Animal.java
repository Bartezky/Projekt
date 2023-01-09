package agh.ics.oop.Animals;

import agh.ics.oop.Genes.Genotype;
import agh.ics.oop.Utilities.Position;
import agh.ics.oop.WorldMap;

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

    public void die() {
        deathDate = worldMap.getCurrentDay();
    }

    public int getDeathDate() {
        return deathDate;
    }

    public void hasNewChild() {
        changeEnergy(-worldMap.getConfiguration().getAnimalEnergyUsedToReproduce());
        childrenCount++;
    }
}
