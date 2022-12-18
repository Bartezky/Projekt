package agh.ics.oop.Animals;

public class Genotype
{
    private final int[] genes;
    private int currentGene;

    public Genotype(int[] genes)
    {
        this.genes = genes;
        currentGene = 0;
    }
}
