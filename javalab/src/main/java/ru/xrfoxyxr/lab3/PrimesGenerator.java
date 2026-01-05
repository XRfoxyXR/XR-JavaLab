package ru.xrfoxyxr.lab3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimesGenerator implements Iterable<Integer> {

    private final int n;

    public PrimesGenerator(int n) {
        this.n = n;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int generated = 0;
            private int current = 1;

            @Override
            public boolean hasNext() {
                return generated < n;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                int x = current + 1;
                while (!isPrime(x)) {
                    x++;
                }
                current = x;
                generated++;
                return current;
            }
        };
    }

    private static boolean isPrime(int x) {
        if (x < 2) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;
        for (int d = 3; d * d <= x; d += 2) {
            if (x % d == 0) return false;
        }
        return true;
    }
}

