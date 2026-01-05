package ru.xrfoxyxr.lab2;

public class Bell {
    private boolean dingTurn = true;

    public void sound() {
        System.out.println(dingTurn ? "ding" : "dong");
        dingTurn = !dingTurn;
    }
}

