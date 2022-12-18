package agh.ics.oop.Animals;

import agh.ics.oop.Utilities.Position;

public class Animal
{
    private Position position;
    private int energy;
    private final Genotype genotype;
    private final Behavior behavior;

    public Animal(Position position, int energy, Genotype genotype, Behavior behavior)
    {
        this.position = position;
        this.energy = energy;
        this.genotype = genotype;
        this.behavior = behavior;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void changeEnergy(int value)
    {
        this.energy += value;
    }

    public int getEnergy()
    {
        return energy;
    }

    public boolean isDead()
    {
        return energy <= 0;
    }
}
