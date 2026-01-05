package ru.xrfoxyxr;

import java.util.Scanner;

import ru.xrfoxyxr.lab1.Lab1;
import ru.xrfoxyxr.lab2.Lab2;
import ru.xrfoxyxr.lab3.Lab3;
import ru.xrfoxyxr.lab4.Lab4;
import ru.xrfoxyxr.lab5.Lab5;
// import ru.xrfoxyxr.lab6.Lab6; не готов

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыбор лабораторной работы:");
            System.out.println("1) Лабораторная 1");
            System.out.println("2) Лабораторная 2");
            System.out.println("3) Лабораторная 3");
            System.out.println("4) Лабораторная 4");
            System.out.println("5) Лабораторная 5");
            System.out.println("6) Лабораторная 6");
            System.out.println("0) Выход");
            System.out.print("Выбор: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> new Lab1().run(sc);
                case "2" -> new Lab2().run(sc);
                case "3" -> new Lab3().run(sc);
                case "4" -> new Lab4().run(sc);
                case "5" -> Lab5.main(new String[0]);
                //case "6" -> Lab6.main(new String[0]); не готов
                case "0" -> {
                    sc.close();
                    System.out.println("Выход.");
                    return;
                }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }
}

