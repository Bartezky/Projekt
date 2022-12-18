package agh.ics.oop.Animals;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal>
{
    @Override
    public int compare(Animal o1, Animal o2)
    {
        if (o1.getEnergy() < o2.getEnergy())
        {
            return -1;
        }
        else if (o1.getEnergy() > o2.getEnergy())
        {
            return 1;
        }

        if (o1.getBirthDate() < o2.getBirthDate())
        {
            return 1;
        }
        else if (o1.getBirthDate() > o2.getBirthDate())
        {
            return -1;
        }

        if (o1.getChildrenCount() < o2.getChildrenCount())
        {
            return -1;
        }
        else if (o1.getChildrenCount() > o2.getChildrenCount())
        {
            return 1;
        }

        return -1;
    }
}
