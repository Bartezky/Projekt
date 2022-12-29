package agh.ics.oop.Utilities;

import java.util.Random;

public class Orientation {
    private final int orientation;

    public Orientation(int orientation) {
        this.orientation = orientation;
    }

    public Orientation() {
        Random random = new Random();
        this.orientation = random.nextInt(8);
    }

    public Orientation opposite() {
        return new Orientation((orientation + 4) % 8);
    }

    public Orientation add(int ratio) {
        return new Orientation((8 + orientation + ratio) % 8);
    }

    public Vector2D toVector() {
        return switch (orientation) {
            case 0 -> new Vector2D(0, 1);
            case 1 -> new Vector2D(1, 1);
            case 2 -> new Vector2D(1, 0);
            case 3 -> new Vector2D(1, -1);
            case 4 -> new Vector2D(0, -1);
            case 5 -> new Vector2D(-1, -1);
            case 6 -> new Vector2D(-1, 0);
            case 7 -> new Vector2D(-1, 1);
            default -> throw new IllegalStateException("Unexpected value: " + orientation);
        };
    }

}
