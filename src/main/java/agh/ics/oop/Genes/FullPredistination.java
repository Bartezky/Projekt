package agh.ics.oop.Genes;

public class FullPredistination extends GenePointer {

    public FullPredistination(int numberOfGenes) {
        super(numberOfGenes);
    }

    @Override
    protected int point() {
        pointer = (pointer + 1) % numberOfGenes;
        return pointer;
    }
}
