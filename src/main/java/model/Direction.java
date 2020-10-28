package model;

public enum Direction {

    UP(0, -20),
    DOWN(0, 20),
    RIGHT(20, 0),
    LEFT(-20, 0);

    private final Coordinates coordinates;

    Direction(double x, double y) {
        coordinates = new Coordinates(x, y);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public double getX() {
        return coordinates.getX();
    }

    public double getY() {
        return coordinates.getY();
    }
}
