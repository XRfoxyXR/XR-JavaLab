package ru.xrfoxyxr.lab2;

import java.util.Scanner;

import ru.xrfoxyxr.lab2.exceptions.InvalidShapeException;
import ru.xrfoxyxr.lab2.exceptions.NegativeValueException;
import ru.xrfoxyxr.lab2.geometry2d.Circle;
import ru.xrfoxyxr.lab2.geometry2d.Figure;
import ru.xrfoxyxr.lab2.geometry2d.Rectangle;
import ru.xrfoxyxr.lab2.geometry3d.Cylinder;

public class Lab2 {
    public void run(Scanner sc) {
        while (true) {
            System.out.println("\nЛабораторная работа №2");
            System.out.println("1) Задание 1");
            System.out.println("2) Задание 2");
            System.out.println("3) Задание 3");
            System.out.println("4) Задание 4");
            System.out.println("5) Задание 5");
            System.out.println("6) Задание 6");
            System.out.println("0) Назад");
            System.out.print("Выбор: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> task1(sc);
                case "2" -> task2(sc);
                case "3" -> task3(sc);
                case "4" -> task4(sc);
                case "5" -> task5(sc);
                case "6" -> task6(sc);
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    // №1 Button
    private static void task1(Scanner sc) {
        Button btn = new Button();

        while (true) {
            System.out.println("\n№1 Button");
            System.out.println("1) Нажать кнопку (click)");
            System.out.println("0) Назад");
            System.out.print("Выбор: ");

            String c = sc.nextLine().trim();

            switch (c) {
                case "1" -> btn.click();
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    // №2 Balance
    private static void task2(Scanner sc) {
        Balance bal = new Balance();

        while (true) {
            System.out.println("\n№2 Balance");
            System.out.println("1) Добавить груз СЛЕВА");
            System.out.println("2) Добавить груз СПРАВА");
            System.out.println("3) Показать результат");
            System.out.println("0) Назад");
            System.out.print("Выбор: ");

            String c = sc.nextLine().trim();

            switch (c) {
                case "1" -> {
                    int w = readInt(sc, "Введите вес слева (целое): ");
                    bal.addLeft(w);
                    System.out.println("Ок.");
                }
                case "2" -> {
                    int w = readInt(sc, "Введите вес справа (целое): ");
                    bal.addRight(w);
                    System.out.println("Ок.");
                }
                case "3" -> {
                    System.out.print("Положение чаш: ");
                    bal.result(); // печатает L/R/=
                }
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    // №3 Bell
    private static void task3(Scanner sc) {
        Bell bell = new Bell();

        while (true) {
            System.out.println("\n№3 Bell");
            System.out.println("1) Звонок");
            System.out.println("0) Назад");
            System.out.print("Выбор: ");

            String c = sc.nextLine().trim();

            switch (c) {
                case "1" -> bell.sound();
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    // №4 OddEvenSeparator
    private static void task4(Scanner sc) {
        OddEvenSeparator sep = new OddEvenSeparator();

        while (true) {
            System.out.println("\n№4 OddEvenSeparator");
            System.out.println("1) Добавить число");
            System.out.println("2) Показать чётные");
            System.out.println("3) Показать нечётные");
            System.out.println("0) Назад");
            System.out.print("Выбор: ");

            String c = sc.nextLine().trim();

            switch (c) {
                case "1" -> {
                    int n = readInt(sc, "Введите число (целое): ");
                    sep.addNumber(n);
                    System.out.println("Добавлено.");
                }
                case "2" -> {
                    System.out.print("Чётные: ");
                    sep.even();
                }
                case "3" -> {
                    System.out.print("Нечётные: ");
                    sep.odd();
                }
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    // №5 Table
    private static void task5(Scanner sc) {
        int rows = readInt(sc, "Введите количество строк: ");
        int cols = readInt(sc, "Введите количество столбцов: ");
        Table table = new Table(rows, cols);

        while (true) {
            System.out.println("\n№5 Table");
            System.out.println("1) setValue(row, col, value)");
            System.out.println("2) getValue(row, col)");
            System.out.println("3) Показать таблицу");
            System.out.println("4) rows() и cols()");
            System.out.println("5) average()");
            System.out.println("0) Назад");
            System.out.print("Выбор: ");

            String c = sc.nextLine().trim();

            switch (c) {
                case "1" -> {
                    int r = readInt(sc, "row: ");
                    int co = readInt(sc, "col: ");
                    int v = readInt(sc, "value: ");
                    table.setValue(r, co, v);
                    System.out.println("Записано.");
                }
                case "2" -> {
                    int r = readInt(sc, "row: ");
                    int co = readInt(sc, "col: ");
                    System.out.println("Значение: " + table.getValue(r, co));
                }
                case "3" -> System.out.println(table);
                case "4" -> System.out.println("rows=" + table.rows() + ", cols=" + table.cols());
                case "5" -> System.out.println("average=" + table.average());
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    // №5 Geometry
    private static void task6(Scanner sc) {
        while (true) {
            System.out.println("\nGeometry");
            System.out.println("1) Круг");
            System.out.println("2) Прямоугольник");
            System.out.println("3) Цилиндр (основание круг)");
            System.out.println("4) Цилиндр (основание прямоугольник)");
            System.out.println("0) Назад");
            System.out.print("Выбор: ");

            String c = sc.nextLine().trim();

            try {
                switch (c) {
                    case "1" -> {
                        double r = readDouble(sc, "Радиус: ");
                        Figure fig = new Circle(r);
                        System.out.println(fig);
                    }
                    case "2" -> {
                        double w = readDouble(sc, "Ширина: ");
                        double h = readDouble(sc, "Высота: ");
                        Figure fig = new Rectangle(w, h);
                        System.out.println(fig);
                    }
                    case "3" -> {
                        double r = readDouble(sc, "Радиус: ");
                        double h = readDouble(sc, "Высота: ");
                        Cylinder cyl = new Cylinder(new Circle(r), h);
                        System.out.println(cyl);
                    }
                    case "4" -> {
                        double w = readDouble(sc, "Ширина: ");
                        double h = readDouble(sc, "Высота: ");
                        double ch = readDouble(sc, "Высота цилиндра: ");
                        Cylinder cyl = new Cylinder(new Rectangle(w, h), ch);
                        System.out.println(cyl);
                    }
                    case "0" -> { return; }
                    default -> System.out.println("Неверный выбор.");
                }
            } catch (NegativeValueException | InvalidShapeException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }
}



