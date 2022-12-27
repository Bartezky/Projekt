package agh.ics.oop.Genes;

import agh.ics.oop.Utilities.Configuration;

public class SlightMutation extends Mutation {

    public SlightMutation(Configuration configuration) {
        super(configuration);
    }

    @Override
    protected int mutateGene(int gene) {
        if (random.nextBoolean()) {
            return (gene + 1) % 8;
        }
        return (gene - 1) % 8;
    }
}
