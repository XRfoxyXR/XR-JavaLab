package ru.xrfoxyxr.lab4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.xrfoxyxr.lab4.model.Book;
import ru.xrfoxyxr.lab4.model.Sms;
import ru.xrfoxyxr.lab4.model.Visitor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lab4 {

    public void run(Scanner sc) {
        List<Visitor> visitors = loadVisitorsFromResources("books.json");
        if (visitors == null) return;

        while (true) {
            System.out.println("\nЛабораторная работа №4");
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
                case "1" -> { task1(visitors); pause(sc); }
                case "2" -> { task2(visitors); pause(sc); }
                case "3" -> { task3(visitors); pause(sc); }
                case "4" -> { task4(visitors); pause(sc); }
                case "5" -> { task5(visitors); pause(sc); }
                case "6" -> { task6(visitors); pause(sc); }
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    //Загрузка JSON из resources
    private List<Visitor> loadVisitorsFromResources(String resourceName) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (is == null) {
                System.out.println("Не найден ресурс: " + resourceName);
                System.out.println("Проверь, что файл лежит в: src/main/resources/" + resourceName);
                return null;
            }
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
            Type type = new TypeToken<List<Visitor>>() {}.getType();
            return new Gson().fromJson(reader, type);
        } catch (Exception e) {
            System.out.println("Ошибка чтения/парсинга JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private void pause(Scanner sc) {
        System.out.print("Нажмите Enter, чтобы продолжить...");
        sc.nextLine();
    }

    //Задание 1
    private void task1(List<Visitor> visitors) {
        System.out.println("\nЗадание 1");
        visitors.forEach(v -> System.out.println(v.getName() + " " + v.getSurname() + " (" + v.getPhone() + ")"));
        System.out.println("Количество посетителей: " + visitors.size());
    }

    //Задание 2
    private void task2(List<Visitor> visitors) {
        System.out.println("\nЗадание 2");

        // уникальность лучше считать по ISBN (это идентификатор книги)
        List<Book> uniqueBooks = visitors.stream()
                .flatMap(v -> safeBooks(v).stream())
                .collect(Collectors.toMap(
                        Book::getIsbn,
                        Function.identity(),
                        (a, b) -> a,            // если ISBN повторился — оставим первую
                        LinkedHashMap::new      // сохраним порядок появления
                ))
                .values().stream()
                .toList();

        uniqueBooks.forEach(b ->
                System.out.println(b.getPublishYear() + " — " + b.getAuthor() + " — " + b.getTitle() + " (ISBN: " + b.getIsbn() + ")")
        );

        System.out.println("Количество уникальных книг: " + uniqueBooks.size());
    }

    //Задание 3
    private void task3(List<Visitor> visitors) {
        System.out.println("\nЗадание 3");

        List<Book> sorted = visitors.stream()
                .flatMap(v -> safeBooks(v).stream())
                .sorted(Comparator.comparingInt(Book::getPublishYear))
                .toList();

        sorted.forEach(b ->
                System.out.println(b.getPublishYear() + " — " + b.getAuthor() + " — " + b.getTitle())
        );
        System.out.println("Всего книг: " + sorted.size());
    }

    //Задание 4
    private void task4(List<Visitor> visitors) {
        System.out.println("\nЗадание 4 \"Jane Austen\"?");

        boolean exists = visitors.stream()
                .flatMap(v -> safeBooks(v).stream())
                .anyMatch(b -> "Jane Austen".equalsIgnoreCase(b.getAuthor()));

        System.out.println("Результат: " + exists);
    }

    //Задание 5
    private void task5(List<Visitor> visitors) {
        System.out.println("\nЗадание 5");

        int max = visitors.stream()
                .mapToInt(v -> safeBooks(v).size())
                .max()
                .orElse(0);

        System.out.println("Максимум избранных книг у одного посетителя: " + max);
    }

    //Задание 6
    private void task6(List<Visitor> visitors) {
        System.out.println("\nЗадание 6");

        // среднее по всем посетителям
        double avg = visitors.stream()
                .mapToInt(v -> safeBooks(v).size())
                .average()
                .orElse(0.0);

        // Берём только подписчиков и превращаем в sms
        List<Sms> smsList = visitors.stream()
                .filter(Visitor::isSubscribed)
                .map(v -> {
                    int favCount = safeBooks(v).size();
                    String msg;
                    if (favCount > avg) msg = "you are a bookworm";
                    else if (favCount < avg) msg = "read more";
                    else msg = "fine";
                    return new Sms(v.getPhone(), msg);
                })
                .toList();

        System.out.println("Среднее количество избранных книг: " + avg);

        smsList.forEach(s -> System.out.println(s.getPhone() + " -> " + s.getMessage()));
        System.out.println("SMS всего: " + smsList.size());
    }

    private List<Book> safeBooks(Visitor v) {
        return v.getFavoriteBooks() == null ? List.of() : v.getFavoriteBooks();
    }
}


