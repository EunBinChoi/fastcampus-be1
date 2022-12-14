package me.day14.practice.practice04;


import me.day14.practice.practice06.GeometricController;

import java.util.Objects;

public abstract class Shape implements GeometricController {

    protected Point centerPoint;

    public Shape() {}

    public Shape(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public abstract void draw();

    public abstract double calculateArea();

    @Override
    public void translate(int dx, int dy) {
        centerPoint.setX(centerPoint.getX() + dx);
        centerPoint.setY(centerPoint.getY() + dy);
    }

    @Override
    public abstract void scale(int offset);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(centerPoint, shape.centerPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centerPoint);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "centerPoint=" + centerPoint +
                '}';
    }
}
