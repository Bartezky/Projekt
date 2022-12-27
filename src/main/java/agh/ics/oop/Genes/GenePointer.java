package agh.ics.oop.Genes;

import java.util.Random;

public abstract class GenePointer {

    protected final int numberOfGenes;
    protected int pointer;
    protected Random random = new Random();

    protected GenePointer(int numberOfGenes) {
        this.numberOfGenes = numberOfGenes;
        pointer = random.nextInt(numberOfGenes);
    }

    protected abstract int point();
}
