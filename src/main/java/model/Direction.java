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

    public Direction reverse() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                throw new IllegalStateException("Unknown direction " + this.toString());
        }
    }

    public static Direction reverse(Direction direction) {
        return direction.reverse();
    }
}
