package agh.ics.oop.Genes;

import agh.ics.oop.Genes.Genome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Mutation {

    private final int minimumNumberOfMutation;
    private final int maximumNumberOfMutation;
    private final List<Integer> randomIndexes;
    protected Random random = new Random();


    protected Mutation(int numberOfGenes, int minimumNumberOfMutation, int maximumNumberOfMutation) {
        this.minimumNumberOfMutation = minimumNumberOfMutation;
        this.maximumNumberOfMutation = maximumNumberOfMutation;
        this.randomIndexes = new ArrayList<>();
        for (int i = 0; i < numberOfGenes; i++) {
            randomIndexes.add(i);
        }
    }

    public Genome mutate(Genome genome) {
        int numberOfMutation = random.nextInt(maximumNumberOfMutation - minimumNumberOfMutation) - minimumNumberOfMutation;
        Collections.shuffle(randomIndexes);
        int [] newGenes = genome.getGenes();
        for (int i = 0; i < numberOfMutation; i++) {
            newGenes[randomIndexes.get(i)] = mutateGene(newGenes[randomIndexes.get(i)]);
        }
        return new Genome(newGenes);
    }

    protected abstract int mutateGene(int gene);

}
