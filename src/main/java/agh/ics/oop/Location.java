package agh.ics.oop;

public class Location {
    private Vector2D position;
    private Orientation orientation;

    public Location(Vector2D position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public void turn(Orientation rotation) {
        orientation.add(rotation);
    }

    public void moveForward() {
        position = position.add(orientation.toVector());
    }

    public void turnBack() {
        orientation = orientation.opposite();
    }
}
