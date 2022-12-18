package agh.ics.oop.Genes;

import agh.ics.oop.Genes.Mutation;

public class SlightMutation extends Mutation {

    protected SlightMutation(int numberOfGenes, int minimumNumberOfMutation, int maximumNumberOfMutation) {
        super(numberOfGenes, minimumNumberOfMutation, maximumNumberOfMutation);
    }

    @Override
    protected int mutateGene(int gene) {
        if (random.nextBoolean()) {
            return (gene + 1) % 8;
        }
        return (gene - 1) % 8;
    }
}
