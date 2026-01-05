package ru.xrfoxyxr.lab3;

import java.util.Objects;

public class Human implements Comparable<Human> {
    private final String firstName;
    private final String lastName;
    private final int age;

    public Human(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }

    @Override
    public int compareTo(Human o) {
        int cmp = this.lastName.compareToIgnoreCase(o.lastName);
        if (cmp != 0) return cmp;

        cmp = this.firstName.compareToIgnoreCase(o.firstName);
        if (cmp != 0) return cmp;

        return Integer.compare(this.age, o.age);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Human h)) return false;
        return age == h.age &&
                Objects.equals(firstName, h.firstName) &&
                Objects.equals(lastName, h.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " (" + age + ")";
    }
}

