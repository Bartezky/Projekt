package agh.ics.oop.Animals;

import agh.ics.oop.Genes.Genotype;
import agh.ics.oop.Utilities.Position;

public class Animal
{
    private Position position;
    private int energy;
    private final Genotype genotype;
    private int birthDate;
    private int childrenCount;

    public Animal(Position position, int energy, Genotype genotype, int date)
    {
        this.position = position;
        this.energy = energy;
        this.genotype = genotype;
        birthDate = date;
        childrenCount = 0;
    }

    public void reproduce(Animal animal)
    {
        //Implementation
        //Current animal reproduce with the animal given as parameter
    }

    public void move()
    {
        //Implementation
        //The animal performs its next move. Moving reduces its energy
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

    public int getBirthDate() {
        return birthDate;
    }

    public int getChildrenCount() {
        return childrenCount;
    }
}
