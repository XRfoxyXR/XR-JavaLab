package ru.xrfoxyxr.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab3 {
    public void run(Scanner sc) {
        while (true) {
            System.out.println("\nЛаба 3: Коллекции");
            System.out.println("1) Collections");
            System.out.println("2) PrimesGenerator");
            System.out.println("3) Human");
            System.out.println("4) HashMap");
            System.out.println("5) Map");
            System.out.println("0) Назад");
            System.out.print("Выбор: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> { task1(sc); pause(sc); }
                case "2" -> { task2(sc); pause(sc); }
                case "3" -> { task3(); pause(sc); }
                case "4" -> { task4(sc); pause(sc); }
                case "5" -> { task5(sc); pause(sc); }
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }

    }

    private static void pause(Scanner sc) {
        System.out.print("\nНажмите Enter, чтобы продолжить...");
        sc.nextLine();
    }
    //№1 Collections
    private static void task1(Scanner sc) {
        int n = readInt(sc, "Введите размер массива: ");
        CollectionsTask.run(n);
    }
    
    //№2 PrimesGenerator
    private static void task2(Scanner sc) {
        int n = readInt(sc, "Введите сколько простых чисел: ");
        PrimesGenerator gen = new PrimesGenerator(n);

        System.out.println("\nПервые N простых прямого порядка:");
        Iterator<Integer> it = gen.iterator();
        List<Integer> store = new ArrayList<>();
        while (it.hasNext()) {
            int p = it.next();
            store.add(p);
            System.out.print(p + " ");
        }
        System.out.println();

        System.out.println("\nПервые N простых обратного порядка:");
        for (int i = store.size() - 1; i >= 0; i--) {
            System.out.print(store.get(i) + " ");
        }
        System.out.println();
    }

    //№3 Human
    private static void task3() {
        List<Human> people = new ArrayList<>();
        people.add(new Human("Ivan", "Petrov", 18));
        people.add(new Human("Anna", "Ivanova", 19));
        people.add(new Human("Petr", "Sidorov", 17));
        people.add(new Human("Olga", "Petrova", 20));
        people.add(new Human("Ivan", "Petrov", 18));

        System.out.println("\nСписок (List):");
        System.out.println(people);

        // a) HashSet
        Set<Human> hash = new HashSet<>(people);
        System.out.println("\n(a) HashSet:");
        System.out.println(hash);

        // b) LinkedHashSet
        Set<Human> linked = new LinkedHashSet<>(people);
        System.out.println("\n(b) LinkedHashSet:");
        System.out.println(linked);

        // c) TreeSet (Comparable)
        Set<Human> tree = new TreeSet<>(people);
        System.out.println("\n(c) Comparable по lastName->firstName->age:");
        System.out.println(tree);

        // d) TreeSet с компаратором по фамилии
        Set<Human> treeByLastName = new TreeSet<>(new HumanComparatorByLastName());
        treeByLastName.addAll(people);
        System.out.println("\n(d) Comparator только по фамилии:");
        System.out.println(treeByLastName);

        // e) TreeSet с анонимным компаратором по возрасту
        Set<Human> treeByAge = new TreeSet<>((h1, h2) -> {
            int cmp = Integer.compare(h1.getAge(), h2.getAge());
            if (cmp != 0) return cmp;
            // чтобы TreeSet не “съел” людей с одинаковым возрастом — добавим tie-breaker
            cmp = h1.getLastName().compareToIgnoreCase(h2.getLastName());
            if (cmp != 0) return cmp;
            cmp = h1.getFirstName().compareToIgnoreCase(h2.getFirstName());
            if (cmp != 0) return cmp;
            return 0;
        });
        treeByAge.addAll(people);

        System.out.println("\n(e) анонимный Comparator по возрасту):");
        System.out.println(treeByAge);

        /*
         HashSet:
           - не хранит порядок
           - порядок зависит от хешей объектов
           - быстрый поиск/вставка

         LinkedHashSet:
           - сохраняет порядок добавления
           - чуть больше памяти, чем HashSet

         TreeSet:
           - хранит элементы отсортированными
           - использует Comparable или Comparator
           - если сравнение вернуло 0 -> элемент считается “уже существующим” и не добавляется
        */
    }
    //№4 HashMap
    private static void task4(Scanner sc) {
        System.out.println("Введите английский текст:");
        String text = sc.nextLine();

        Pattern p = Pattern.compile("[A-Za-z]+");
        Matcher m = p.matcher(text);

        Map<String, Integer> freq = new HashMap<>();
        while (m.find()) {
            String w = m.group().toLowerCase();
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        System.out.println("\nРазличные слова и частоты:");
        // сортировка вывода по слову для удобства
        List<String> keys = new ArrayList<>(freq.keySet());
        Collections.sort(keys);
        for (String k : keys) {
            System.out.println(k + " -> " + freq.get(k));
        }
    }
    //№5 Map
     private static void task5(Scanner sc) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        System.out.println("\nИсходная Map: " + map);

        Map<Integer, String> swapped = swapMap(map);
        System.out.println("Swapped Map: " + swapped);
    }

    public static <K, V> Map<V, K> swapMap(Map<K, V> input) {
        Map<V, K> out = new LinkedHashMap<>();
        for (Map.Entry<K, V> e : input.entrySet()) {
            out.put(e.getValue(), e.getKey());
        }
        return out;
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно целое число.");
            }
        }
    }
}
