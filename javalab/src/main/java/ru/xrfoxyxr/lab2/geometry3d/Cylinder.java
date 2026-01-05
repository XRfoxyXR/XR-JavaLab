package ru.xrfoxyxr.lab2.geometry3d;

import ru.xrfoxyxr.lab2.exceptions.InvalidShapeException;
import ru.xrfoxyxr.lab2.exceptions.NegativeValueException;
import ru.xrfoxyxr.lab2.geometry2d.Figure;

public class Cylinder {
    private final Figure base;
    private final double height;

    public Cylinder(Figure base, double height) {
        if (base == null) {
            throw new InvalidShapeException("Базовая цифра не должна быть нулевой");
        }
        if (height <= 0) {
            throw new NegativeValueException("Высота должна быть > 0, получено: " + height);
        }
        this.base = base;
        this.height = height;
    }

    public Figure getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public double volume() {
        return base.area() * height;
    }

    @Override
    public String toString() {
        return "Цилинд{база=" + base + ", высота=" + height + ", объем=" + volume() + "}";
    }
}

