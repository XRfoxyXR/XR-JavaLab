package ru.xrfoxyxr.lab1;

import java.util.Scanner;

public class Lab1 {

    public void run(Scanner sc) {
        while (true) {
            System.out.println("\nЛаба 1: выбор задания");
            System.out.println("1) Задание 1");
            System.out.println("2) Задание 2");
            System.out.println("3) Задание 3");
            System.out.println("4) Задание 4");
            System.out.println("5) Задание 5");
            System.out.println("0) Назад");
            System.out.print("Выбор: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    task1();
                    pause(sc);
                }
                case "2" -> {
                    task2();
                    pause(sc);
                }
                case "3" -> {
                    task3(sc);
                    pause(sc);
                }
                case "4" -> {
                    task4();
                    pause(sc);
                }
                case "5" -> {
                    task5(sc);
                    pause(sc);
                }
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private void pause(Scanner sc) {
        System.out.print("Нажмите Enter, чтобы продолжить...");
        sc.nextLine();
    }

    // Задание 1
    public static void task1() {
        System.out.println("\nЗадание 1:");
        for (int i = 1; i <= 500; i++) {
            if (i % 35 == 0) {
                System.out.println("fizzbuzz");
            } else if (i % 5 == 0) {
                System.out.println("fizz");
            } else if (i % 7 == 0) {
                System.out.println("buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    // Задание 2
    public static String task2() {
        System.out.println("\nЗадание 2:");

        String str = "make install";
        StringBuilder sb = new StringBuilder(str);
        String reversed = sb.reverse().toString();

        System.out.println(reversed);
        return reversed;
    }

    // Задание 3
    public static double[] task3(Scanner sc) {
        System.out.println("\nЗадание 3:");
        double a = readDouble(sc, "Введите a: ");
        double b = readDouble(sc, "Введите b: ");
        double c = readDouble(sc, "Введите c: ");

        if (a == 0) {
            System.out.println("Это не квадратное уравнение (a = 0).");
            return new double[0];
        }

        double d = b * b - 4 * a * c;

        if (d < 0) {
            System.out.println("Нет вещественных корней");
            return new double[0];
        }

        double x1 = (-b + Math.sqrt(d)) / (2 * a);
        double x2 = (-b - Math.sqrt(d)) / (2 * a);

        if (d == 0) {
            System.out.println("Один корень: " + x1);
            return new double[]{x1};
        }

        System.out.println("Корни: " + x1 + " и " + x2);
        return new double[]{x1, x2};
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Введите число.");
            }
        }
    }

    // Задание 4
    public static double task4() {
        System.out.println("\nЗадание 4:");

        double sum = 0.0;
        int n = 2;
        double term;

        do {
            term = 1.0 / (n * n + n - 2);
            sum += term;
            n++;
        } while (term >= 1e-6);

        System.out.println("Сумма ряда: " + sum);
        return sum;
    }

    // Задание 5
    public static boolean task5(Scanner sc) {
        System.out.println("\nЗадание 5:");
        System.out.print("Введите строку: ");
        String s = sc.nextLine();

        String clean = s.replaceAll("\\s+", "").toLowerCase();

        String reversed = new StringBuilder(clean).reverse().toString();

        boolean result = clean.equals(reversed);
        System.out.println("Палиндром: " + result);
        return result;
    }
}

