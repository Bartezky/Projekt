package agh.ics.oop.Utilities;

public class Orientation
{
    private final int orientation;

    public Orientation(int orientation)
    {
        this.orientation = orientation;
    }

    public Orientation opposite()
    {
        return new Orientation((orientation + 4) % 8);
    }
}
