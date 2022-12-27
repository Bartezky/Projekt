package agh.ics.oop.Utilities;

public class Position {
    private final Vector2D vector2D;
    private final Orientation orientation;

    public Position(Vector2D vector2D, int orientation) {
        this(vector2D, new Orientation(orientation));
    }

    public Position(Vector2D vector2D, Orientation orientation) {
        this.vector2D = vector2D;
        this.orientation = orientation;
    }

    public Vector2D getVector2D() {
        return vector2D;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Position turn(int rotation) {
        return new Position(this.vector2D, orientation.add(rotation));
    }

    public Position forwardPosition() {
        return new Position(vector2D.add(orientation.toVector()), orientation);
    }

}
