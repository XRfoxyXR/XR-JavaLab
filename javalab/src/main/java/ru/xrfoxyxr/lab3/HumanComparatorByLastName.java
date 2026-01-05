package ru.xrfoxyxr.lab3;

import java.util.Comparator;

public class HumanComparatorByLastName implements Comparator<Human> {
    @Override
    public int compare(Human a, Human b) {
        int cmp = a.getLastName().compareToIgnoreCase(b.getLastName());
        if (cmp != 0) return cmp;

        cmp = a.getFirstName().compareToIgnoreCase(b.getFirstName());
        if (cmp != 0) return cmp;

        return Integer.compare(a.getAge(), b.getAge());
    }
}

