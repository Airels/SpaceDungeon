package model;

public class Coordinates {
    private double x, y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDistance(Coordinates coords) {
        double x2 = coords.getX();
        double y2 = coords.getY();

        return Math.sqrt(
                Math.pow(x2 - x, 2)
                + Math.pow(y2 - y, 2)
        );
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
