package ru.xrfoxyxr.lab2.geometry2d;

import ru.xrfoxyxr.lab2.exceptions.NegativeValueException;

public class Circle implements Figure {
    private final double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new NegativeValueException("Радиус должен быть > 0, получается: " + radius);
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Круг{r=" + radius + ", область=" + area() + ", периметр=" + perimeter() + "}";
    }
}

