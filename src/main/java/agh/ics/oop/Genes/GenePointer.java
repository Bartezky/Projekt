package agh.ics.oop.Genes;

import java.util.Random;

public abstract class GenePointer {

    protected int pointer;
    protected final int numberOfGenes;
    protected Random random = new Random();

    public GenePointer(int numberOfGenes) {
        this.numberOfGenes = numberOfGenes;
        pointer = random.nextInt(numberOfGenes);
    }

    protected abstract int point();
}
