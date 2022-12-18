package agh.ics.oop;

public class Animal implements IMapElement {

    private final AbstractWorldMap map;
    private Location location;
    private Genome genome;
    private int energy;
    private int dayOfBirth;
    private int dayOfDeath;
    private int numberOfChild;
    private boolean isAlive;
    private int eatenPlants;


    public Animal(AbstractWorldMap map, Location location, Genome genome, int dayOfBirth, int energy) {
        this.map = map;
        this.location = location;
        this.genome = genome;
        this.dayOfBirth = dayOfBirth;
        this.energy = energy;
        this.numberOfChild = 0;
        this.isAlive = true;
        this.eatenPlants = 0;
    }

    @Override
    public Vector2D getVector2D() {
        return null;
    }

    public boolean checkIsAlive() {
        return energy > 0;
    }

    public void die(int dayOfDeath) {
        this.dayOfDeath = dayOfDeath;
        this.isAlive = false;
    }

    public void turn() {
        location.turn(new Orientation(genome.getGene()));
    }

    public void move() {
        int energy = map.move(location);
        this.energy -= energy;
    }

    public boolean hasPriority(Animal other) {
        if (this.energy > other.energy) return true;
        else if (this.energy < other.energy) return false;
        else if (this.dayOfBirth < other.dayOfBirth) return true;
        else if (this.dayOfBirth > other.dayOfBirth) return false;
        else if (this.numberOfChild > other.numberOfChild) return true;
        else if (this.numberOfChild < other.numberOfChild) return false;
        else return false;
    }

    public void eat(int energy) {
        this.energy += energy;
        eatenPlants++;
    }

    public boolean hasEnoughEnergy(int neededEnergy) {
        return this.energy >= neededEnergy;
    }

    public void reproduce() {
        int energy = map.reproduce(genome.getGenes());
        this.energy -= energy;
        numberOfChild++;
    }

}
