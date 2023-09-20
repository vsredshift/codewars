package com.vsredshift.main.kyu6;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class FindOdd {
    public static void main(String[] args) {
        System.out.println("Found it");
        int[] arrayIn = {12, 2, 4, 6, 8, 8, 10, 10, 2, 4, 12};
        System.out.println(findItXorVersion(arrayIn));

    }

    // My Submitted Solution
    private static int findIt(int[] a) {
        Map<Integer, Integer> entries = new HashMap<>();
        for (int number : a) {
            entries.put(number, entries.get(number) != null ? entries.get(number) + 1 : 1);
        }
        Integer oddValue = entries.values().stream().filter(n -> n % 2 == 1).findFirst().get();
        for (Map.Entry<Integer, Integer> entry : entries.entrySet()) {
            if (entry.getValue().equals(oddValue)) {
                return entry.getKey();
            }
        }
        return -1;
    }


    //____________________________________________________________________________
    //  ALTERNATE SOULTIONS
    //____________________________________________________________________________

    private static int findItStreamVersion(int[] a) {
        return stream(a)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() % 2 == 1)
                .mapToInt(Map.Entry::getKey)
                .findFirst()
                .getAsInt();
    }

    private static int findItReduceVersion(int[] a) {
        return stream(a).reduce(0, (x, y) -> x ^ y);
    }

    private static int findItXorVersion(int[] A) {
        int xor = 0;
        for (int j : A) {
            xor ^= j;
        }
        return xor;
    }

}
