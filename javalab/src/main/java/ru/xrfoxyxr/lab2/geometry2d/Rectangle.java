package ru.xrfoxyxr.lab2.geometry2d;

import ru.xrfoxyxr.lab2.exceptions.NegativeValueException;

public class Rectangle implements Figure {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        if (width <= 0) {
            throw new NegativeValueException("Ширина должна быть > 0, получается: " + width);
        }
        if (height <= 0) {
            throw new NegativeValueException("Высота должна быть > 0, получено: " + height);
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Прямоугольник{ширина=" + width + ", высота=" + height + ", область=" + area() + ", периметр=" + perimeter() + "}";
    }
}
