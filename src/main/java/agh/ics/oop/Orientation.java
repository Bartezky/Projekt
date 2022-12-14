package agh.ics.oop;

public class Orientation {
    private final int direction;

    public Orientation(int direction) {
        this.direction = direction;
    }

    public Orientation add(Orientation other) {
        return new Orientation((direction + other.direction) % 8);
    }

    public Orientation opposite() {
        return new Orientation((direction + 4) % 8);
    }

    public Vector2D toVector() {
        return switch (direction) {
            case 0 -> new Vector2D(0, 1);
            case 1 -> new Vector2D(1, 1);
            case 2 -> new Vector2D(1, 0);
            case 3 -> new Vector2D(1, -1);
            case 4 -> new Vector2D(0, -1);
            case 5 -> new Vector2D(-1, -1);
            case 6 -> new Vector2D(-1, 0);
            case 7 -> new Vector2D(-1, 1);
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

}
