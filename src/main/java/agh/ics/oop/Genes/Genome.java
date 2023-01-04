package agh.ics.oop.Genes;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Genome {
    private static final Random random = new Random();
    private final int[] genes;
    private final int numberOfGenes;


    public Genome(int numberOfGenes) {
        this.numberOfGenes = numberOfGenes;
        genes = new int[numberOfGenes];
        randomGenes(numberOfGenes);
    }

    public Genome(Genome left, Genome right) {
        this.numberOfGenes = left.numberOfGenes + right.numberOfGenes;
        genes = new int[numberOfGenes];
        genesByMerge(left, right);
    }

    public Genome(int[] genes) {
        this.numberOfGenes = genes.length;
        this.genes = new int[numberOfGenes];
        System.arraycopy(genes, 0, this.genes, 0, numberOfGenes);
    }

    private void genesByMerge(Genome left, Genome right) {
        if (left.numberOfGenes >= 0) System.arraycopy(left.genes, 0, genes, 0, left.numberOfGenes);
        if (right.numberOfGenes >= 0)
            System.arraycopy(right.genes, 0, genes, left.numberOfGenes, right.numberOfGenes);
    }


    private void randomGenes(int n) {
        for (int i = 0; i < numberOfGenes; i++) {
            genes[i] = random.nextInt(8);
        }
    }

    public int get(int i) {
        return genes[i];
    }

    public Genome getLeftSlice(int n) {
        Genome genome = new Genome(n);
        if (n >= 0) System.arraycopy(genes, 0, genome.genes, 0, n);
        return genome;
    }

    public Genome getRightSlice(int n) {
        Genome genome = new Genome(n);
        if (numberOfGenes - (numberOfGenes - n) >= 0)
            System.arraycopy(genes, numberOfGenes - n, genome.genes, 0, n);
        return genome;
    }

    public int[] getGenes() {
        int[] copy = new int[numberOfGenes];
        System.arraycopy(genes, 0, copy, 0, numberOfGenes);
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genome genome = (Genome) o;
        return numberOfGenes == genome.numberOfGenes && Arrays.equals(genes, genome.genes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfGenes);
        result = 31 * result + Arrays.hashCode(genes);
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(genes);
    }
}
