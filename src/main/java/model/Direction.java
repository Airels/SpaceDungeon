package model;

import controller.App;

public enum Direction {

    UP(0, -App.MOVE_STEP_SIZE),
    DOWN(0, App.MOVE_STEP_SIZE),
    RIGHT(App.MOVE_STEP_SIZE, 0),
    LEFT(-App.MOVE_STEP_SIZE, 0);

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
