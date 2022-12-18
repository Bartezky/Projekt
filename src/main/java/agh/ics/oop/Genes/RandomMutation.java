package agh.ics.oop.Genes;

import agh.ics.oop.Genes.Mutation;

public class RandomMutation extends Mutation {

    protected RandomMutation(int numberOfGenes, int minimumNumberOfMutation, int maximumNumberOfMutation) {
        super(numberOfGenes, minimumNumberOfMutation, maximumNumberOfMutation);
    }

    @Override
    protected int mutateGene(int gene) {
        int newGene = gene;
        while (newGene == gene) {
            newGene = random.nextInt(8);
        }
        return newGene;
    }
}
