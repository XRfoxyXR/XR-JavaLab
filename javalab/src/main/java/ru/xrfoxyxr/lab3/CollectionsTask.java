package ru.xrfoxyxr.lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class CollectionsTask {
    public static void run(int n) {
        Random rnd = new Random();

        // массив N случайных 0..100
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(101);

        System.out.println("\n(a) Массив:");
        System.out.println(Arrays.toString(arr));

        // список List на основе массива
        List<Integer> list = new ArrayList<>();
        for (int v : arr) list.add(v);

        System.out.println("\n(b) List:");
        System.out.println(list);

        // сортировка по возрастанию
        Collections.sort(list);
        System.out.println("\n(c) Sorted ASC:");
        System.out.println(list);

        //сортировка по убыванию
        list.sort(Collections.reverseOrder());
        System.out.println("\n(d) Sorted DESC:");
        System.out.println(list);

        //перемешать
        Collections.shuffle(list);
        System.out.println("\n(e) Shuffled:");
        System.out.println(list);

        //циклический сдвиг на 1 элемент
        Collections.rotate(list, 1);
        System.out.println("\n(f) Rotated by +1:");
        System.out.println(list);

        //оставить только уникальные значения
        List<Integer> unique = new ArrayList<>(new LinkedHashSet<>(list));
        System.out.println("\n(g) Unique elements:");
        System.out.println(unique);

        //оставить только дублирующиеся
        List<Integer> duplicates = keepDuplicates(list);
        System.out.println("\n(h) Duplicate values (appear >=2):");
        System.out.println(duplicates);

        //получить массив
        int[] arr2 = listToArray(list);
        System.out.println("\n(i) Array from List:");
        System.out.println(Arrays.toString(arr2));

        //частота каждого числа в массиве
        Map<Integer, Integer> freq = countFrequencies(arr2);
        System.out.println("\n(j) Frequencies (number -> count):");
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    private static int[] listToArray(List<Integer> list) {
        int[] out = new int[list.size()];
        for (int i = 0; i < list.size(); i++) out[i] = list.get(i);
        return out;
    }

    private static Map<Integer, Integer> countFrequencies(int[] arr) {
        Map<Integer, Integer> freq = new TreeMap<>();
        for (int v : arr) freq.put(v, freq.getOrDefault(v, 0) + 1);
        return freq;
    }

    private static List<Integer> keepDuplicates(List<Integer> list) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int v : list) counts.put(v, counts.getOrDefault(v, 0) + 1);

        List<Integer> dup = new ArrayList<>();
        Set<Integer> added = new HashSet<>();
        for (int v : list) {
            if (counts.get(v) >= 2 && !added.contains(v)) {
                dup.add(v);
                added.add(v);
            }
        }
        return dup;
    }
}

