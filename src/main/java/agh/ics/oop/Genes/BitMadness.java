package agh.ics.oop.Genes;

public class BitMadness extends GenePointer {

    public BitMadness(int numberOfGenes) {
        super(numberOfGenes);
    }

    @Override
    protected int point() {
        int result = random.nextInt(100);
        if (result < 80) {
            pointer = (pointer + 1) % numberOfGenes;
        } else {
            pointer = random.nextInt(numberOfGenes);
        }
        return pointer;
    }
}
