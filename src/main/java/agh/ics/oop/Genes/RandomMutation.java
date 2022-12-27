package agh.ics.oop.Genes;

import agh.ics.oop.Utilities.Configuration;

public class RandomMutation extends Mutation {

    public RandomMutation(Configuration configuration) {
        super(configuration);
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
