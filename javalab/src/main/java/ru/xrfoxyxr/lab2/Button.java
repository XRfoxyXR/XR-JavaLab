package ru.xrfoxyxr.lab2;

public class Button {
    private int clicks = 0;

    public void click() {
        clicks++;
        System.out.println(clicks);
    }
}

