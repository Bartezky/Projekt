package agh.ics.oop.Genes;

import agh.ics.oop.Genes.GenePointer;
import agh.ics.oop.Genes.Genome;

public class Genotype {
    private final GenePointer pointer;
    private final Genome genome;

    public Genotype(GenePointer pointer, Genome genome) {
        this.pointer = pointer;
        this.genome = genome;
    }

    public int getGene() {
        return genome.get(pointer.point());
    }

}
