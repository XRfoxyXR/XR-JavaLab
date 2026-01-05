package ru.xrfoxyxr.lab2;

import java.util.ArrayList;
import java.util.List;

public class OddEvenSeparator {
    private final List<Integer> evens = new ArrayList<>();
    private final List<Integer> odds = new ArrayList<>();

    public void addNumber(int n) {
        if (n % 2 == 0) evens.add(n);
        else odds.add(n);
    }

    public void even() {
        System.out.println(evens);
    }

    public void odd() {
        System.out.println(odds);
    }
}

